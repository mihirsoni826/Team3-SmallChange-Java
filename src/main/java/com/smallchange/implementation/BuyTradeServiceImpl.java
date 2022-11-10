package com.smallchange.implementation;

import com.smallchange.entities.*;
import com.smallchange.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuyTradeServiceImpl implements IBuyTradeService {

    @Autowired IPortfolioService portfolioService;
    @Autowired ISecurityService securityService;
    @Autowired IUserService userService;
    @Autowired IBankAccountService bankAccountService;
    @Autowired TradeHistoryService tradeHistoryService;

    /**
     * Check if the current account has sufficient balance to execute the trade or not
     * @param accountNumber account number of the account to be checked
     * @param value total transaction value for the trade
     * @return true/false based on if the trade can be executed or not
     * @throws SQLException
     */
    @Override
    public boolean sufficientBalance(String accountNumber, double value) throws SQLException {
        BankAccount account = bankAccountService.getBankAccountByAccountNumber(accountNumber).orElse(null);
        assert account != null;
        double balance = account.getBalance();
        return balance >= value;
    }

    /**
     * Converts time in milliseconds to a Date object of java.util.Date format
     * @param timeMilli Long time in milliseconds
     * @return Date of java.util.Date format
     */
    public Date convertMilliToDate(Long timeMilli) {
        return new Date(timeMilli);
    }

    /**
     * Takes in the payload from the request and performs data fill up and checks to execute the transaction
     * @param reqBody the payload received from the request
     * @return true/false based on if the purchase can be executed or not
     */
    @Override
    public boolean postBuyTrade(BuyRequest reqBody) {
        Date date = convertMilliToDate(reqBody.getTimeInMilliseconds());
        reqBody.setDateOfPurchase(date.toString());

        Security populatedSecurityObj = getSecurityDetails((reqBody.getSecurity().getTicker()));
        reqBody = populateSecurityInBuyRequest(reqBody, populatedSecurityObj);

        Users populatedUserObj = getUserDetails(reqBody.getUser().getEmail());
        reqBody = populateUserInBuyRequest(reqBody, populatedUserObj);

        System.err.println(reqBody);
        double transactionValue = reqBody.getQuantity() * reqBody.getSecurity().getMarketPrice();

        if(reqBody.isFeeApplicable())
            transactionValue += 0.0025 * transactionValue;

        double value = transactionValue;
        System.err.println(value);

        try {
            if(sufficientBalance(reqBody.getAccountNumber(), value)) {

                updateAccountBalance(reqBody.getAccountNumber(), value);

                insertIntoTradeHistory(reqBody);

                Map<String, Portfolio> resultMap = existInPortfolio(reqBody);
                boolean tickerPresentInPortfolio = resultMap.containsKey("true");

                if(tickerPresentInPortfolio) {
                    updatePortfolio(resultMap.get("true"), reqBody);
                }
                else {
                    addPortfolio(reqBody);
                }
            }
            else {
                System.out.println("Insufficient funds");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Inserts a record in the trade_history table
     * @param buyRequest BuyRequest entity from which data is extracted for the TradeHistory entity
     */
    public void insertIntoTradeHistory(BuyRequest buyRequest) {
        TradeHistory tradeHistory = new TradeHistory();
        Security security = buyRequest.getSecurity();

        tradeHistory.setTicker(security.getTicker());
        tradeHistory.setSecurityName(security.getSecurityName());
        tradeHistory.setAccountType(security.getAccountType());
        tradeHistory.setTransactionDate(new Timestamp(buyRequest.getTimeInMilliseconds()));
        tradeHistory.setTradeType(BuyRequest.BUY);
        tradeHistory.setAssetClass(security.getAssetClass());
        tradeHistory.setTradePrice(security.getMarketPrice() * buyRequest.getQuantity());
        tradeHistory.setQuantity(buyRequest.getQuantity());
        tradeHistory.setEmail(buyRequest.getUser().getEmail());

        tradeHistoryService.saveTradeHistory(tradeHistory);
    }

    /**
     * Populates Security property in the BuyRequest entity by getting values from the entity already populated by the DB
     * @param reqBody BuyRequest entity with empty Security property
     * @param populatedSecurityObj Security entity with all properties filled
     * @return BuyRequest entity with data-filled Security property
     */
    public BuyRequest populateSecurityInBuyRequest(BuyRequest reqBody, Security populatedSecurityObj) {
        reqBody.getSecurity().setAccountType(populatedSecurityObj.getAccountType());
        reqBody.getSecurity().setSecurityName(populatedSecurityObj.getSecurityName());
        reqBody.getSecurity().setMarketPrice(populatedSecurityObj.getMarketPrice());
        reqBody.getSecurity().setAssetClass(populatedSecurityObj.getAssetClass());
        reqBody.getSecurity().setSubAccountType(populatedSecurityObj.getSubAccountType());

        return reqBody;
    }

    /**
     * Populates Users property in the BuyRequest entity by getting values from the entity already populated by the DB
     * @param reqBody BuyRequest entity with empty Users property
     * @param populatedUserObj Users entity with all properties filled
     * @return BuyRequest entity with data-filled Users property
     */
    public BuyRequest populateUserInBuyRequest(BuyRequest reqBody, Users populatedUserObj) {
        reqBody.getUser().setDob(populatedUserObj.getDob());
        reqBody.getUser().setPhone(populatedUserObj.getPhone());
        reqBody.getUser().setPassword(populatedUserObj.getPassword());
        reqBody.getUser().setLastName(populatedUserObj.getLastName());
        reqBody.getUser().setFirstName(populatedUserObj.getFirstName());
        reqBody.getUser().setRiskAppetite(populatedUserObj.getRiskAppetite());

        return reqBody;
    }

    /**
     * Updates the account balance after a transaction has been completed
     * @param accountNumber account number of the account whose balance has to be updated
     * @param value the transaction value with which the amount has to be updated
     */
    @Override
    public void updateAccountBalance(String accountNumber, double value) {
        BankAccount account = bankAccountService.getBankAccountByAccountNumber(accountNumber).orElse(null);
        assert account != null;
        account.setBalance(account.getBalance() - value);
        bankAccountService.updateBankAccountDetails(account);
    }

    /**
     * Creates a Portfolio entity by extracting data from the BuyRequest payload and inserts a record in the portfolio table
     * @param buyRequest BuyRequest payload received from the request
     */
    @Override
    public void addPortfolio(BuyRequest buyRequest) {
        Portfolio portfolio = new Portfolio();
        portfolio.setSecurity(buyRequest.getSecurity());
        portfolio.setUser(buyRequest.getUser());
        portfolio.setAvg_buy_price(buyRequest.getSecurity().getMarketPrice());
        portfolio.setQuantity(buyRequest.getQuantity());

        portfolioService.savePortfolio(portfolio);
    }

    /**
     * Updates the portfolio table in case the entity for which the transaction is being performed, already exists in the portfolio table for the specific user
     * @param updatePortfolio The updated Portfolio entity
     * @param buyRequest BuyRequest payload received from the request
     */
    @Override
    public void updatePortfolio(Portfolio updatePortfolio, BuyRequest buyRequest) {
        updatePortfolio.setQuantity(updatePortfolio.getQuantity() + buyRequest.getQuantity());
        double avgBuyPrice = (updatePortfolio.getAvg_buy_price() + buyRequest.getSecurity().getMarketPrice()) / 2;
        updatePortfolio.setAvg_buy_price(avgBuyPrice);

        // TODO: check if object got saved successfully
        portfolioService.savePortfolio(updatePortfolio);
    }

    /**
     * Gets a populated Security entity from the DB and returns it to be used in the BuyRequest entity
     * @param ticker ticker of the security for which data has to be fetched
     * @return Populated Security entity
     */
    @Override
    public Security getSecurityDetails(String ticker) {
        return securityService.getSecurityByTicker(ticker).orElse(null);
    }

    /**
     * Gets a populated Users entity from the DB and returns it to be used in the BuyRequest entity
     * @param email email of the user for which data has to be fetched
     * @return Populated Users entity
     */
    @Override
    public Users getUserDetails(String email) {
        return userService.getUserByEmail(email).orElse(null);
    }

    /**
     * Checks if a ticker already exists in a user's portfolio
     * @param list list of portfolio objects denoting the entire portfolio of a user
     * @param ticker ticker to be checked
     * @return Returns a Map< String, Portfolio > which can have {"true": PortfolioObject} or {"false": null} based on if the ticker exists or not
     */
    public Map<String, Portfolio> tickerExists(final List<Portfolio> list, final String ticker){
        Map<String, Portfolio> result = new HashMap<>();
        for(Portfolio p: list) {
            if(p.getSecurity().getTicker().equals(ticker)) {
                result.put("true", p);
            }
        }
        result.put("false", null);
        return result;
    }

    /**
     * Checks if a ticker exists for a user in the portfolio table
     * @param buyRequest BuyRequest payload received from the request
     * @return Returns a Map {"false": null} is the user does not have the security in the portfolio, or {"true": PortfolioObjectToUpdate} if the security already exists in the user's portfolio
     */
    public Map<String, Portfolio> existInPortfolio(BuyRequest buyRequest) {
        List<Portfolio> portfolioData = portfolioService.getPortfolioData(buyRequest.getUser().getEmail()).orElse(null);
        Map<String, Portfolio> existInPortfolioResultMap = new HashMap<>();

        if(portfolioData == null) {
            System.err.println("New user entry");
            existInPortfolioResultMap.put("false", null);
            return existInPortfolioResultMap;
        }

        Map<String, Portfolio> resultMap = tickerExists(portfolioData, buyRequest.getSecurity().getTicker());
        if(resultMap.containsKey("true")) {
            System.err.println("Existing ticker");
            Portfolio updatePortfolio = resultMap.get("true");
            existInPortfolioResultMap.put("true", updatePortfolio);
        }
        else {
            System.err.println("New ticker");
            existInPortfolioResultMap.put("false", null);
        }

        return existInPortfolioResultMap;
    }

}
