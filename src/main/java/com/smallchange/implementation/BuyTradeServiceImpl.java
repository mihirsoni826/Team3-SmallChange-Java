package com.smallchange.implementation;

import com.smallchange.dao.BuyDaoImpl;
import com.smallchange.entities.BuyRequest;
import com.smallchange.entities.Portfolio;
import com.smallchange.entities.Security;
import com.smallchange.entities.Users;
import com.smallchange.repository.PortfolioRepository;
import com.smallchange.repository.SecurityRepository;
import com.smallchange.repository.UserRepository;
import com.smallchange.services.IBuyTradeService;
import com.smallchange.services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Port;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Component
public class BuyTradeServiceImpl implements IBuyTradeService {

    @Autowired
    BuyDaoImpl db;

    @Autowired
    IPortfolioService portfolioService;

    @Autowired
    SecurityRepository securityRepository;

    @Autowired
    UserRepository userRepository;

    public boolean sufficientBalance(String accountNumber, double value) throws SQLException {
        double balance = db.getAccountBalance(accountNumber);
        return balance >= value;
    }

    public Date convertMilliToDate(Long timeMilli) {
        return new Date(timeMilli);
    }

    @Override
    public boolean postBuyTrade(BuyRequest reqBody) {
        Date date = convertMilliToDate(reqBody.getTimeInMilliseconds());
        reqBody.setDateOfPurchase(date.toString());

        Security populatedSecurityObj = getSecurityDetails((reqBody.getSecurity().getTicker()));
        reqBody.getSecurity().setAccountType(populatedSecurityObj.getAccountType());
        reqBody.getSecurity().setSecurityName(populatedSecurityObj.getSecurityName());
        reqBody.getSecurity().setMarketPrice(populatedSecurityObj.getMarketPrice());
        reqBody.getSecurity().setAssetClass(populatedSecurityObj.getAssetClass());
        reqBody.getSecurity().setSubAccountType(populatedSecurityObj.getSubAccountType());

        Users populatedUerObj = getUserDetails(reqBody.getUser().getEmail());
        reqBody.getUser().setDob(populatedUerObj.getDob());
        reqBody.getUser().setPhone(populatedUerObj.getPhone());
        reqBody.getUser().setPassword(populatedUerObj.getPassword());
        reqBody.getUser().setLastName(populatedUerObj.getLastName());
        reqBody.getUser().setFirstName(populatedUerObj.getFirstName());
        reqBody.getUser().setRiskAppetite(populatedUerObj.getRiskAppetite());

        System.out.println(reqBody);
        double value = reqBody.getQuantity() * reqBody.getSecurity().getMarketPrice();

        try {
            if(sufficientBalance(reqBody.getAccountNumber(), value)) {
                boolean balanceUpdated = db.updateAccountBalance(true, value, reqBody.getAccountNumber());
                Map<String, Portfolio> resultMap = existInPortfolio(reqBody);
                boolean tickerPresentInPortfolio = resultMap.containsKey("true");
                if(tickerPresentInPortfolio) {
                    updatePortfolio(resultMap.get("true"), reqBody);
                } else {
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

    public void addPortfolio(BuyRequest buyRequest) {
        Portfolio portfolio = new Portfolio();
        portfolio.setSecurity(buyRequest.getSecurity());
        portfolio.setUser(buyRequest.getUser());
        portfolio.setAvg_buy_price(buyRequest.getSecurity().getMarketPrice());
        portfolio.setQuantity(buyRequest.getQuantity());

        portfolioService.savePortfolio(portfolio);
    }

    private void updatePortfolio(Portfolio updatePortfolio, BuyRequest buyRequest) {
        updatePortfolio.setQuantity(updatePortfolio.getQuantity() + buyRequest.getQuantity());
        double avgBuyPrice = (updatePortfolio.getAvg_buy_price() + buyRequest.getSecurity().getMarketPrice()) / 2;
        updatePortfolio.setAvg_buy_price(avgBuyPrice);

        // TODO: check if object got saved successfully
        portfolioService.savePortfolio(updatePortfolio);
    }

    @Override
    public Security getSecurityDetails(String ticker) {
        return securityRepository.findById(ticker).orElse(null);
    }

    public Users getUserDetails(String email) {
        return userRepository.findById(email).orElse(null);
    }

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

    public Map<String, Portfolio> existInPortfolio(BuyRequest buyRequest) {
        List<Portfolio> portfolioData = portfolioService.getPortfolioData(buyRequest.getUser().getEmail()).orElse(null);
        Map<String, Portfolio> existInPortfolioResultMap = new HashMap<>();

        if(portfolioData == null) {
            existInPortfolioResultMap.put("false", null);
            return existInPortfolioResultMap;
        }

        Map<String, Portfolio> resultMap = tickerExists(portfolioData, buyRequest.getSecurity().getTicker());
        if(resultMap.containsKey("true")) {
            Portfolio updatePortfolio = resultMap.get("true");

//            updatePortfolio.setQuantity(updatePortfolio.getQuantity() + buyRequest.getQuantity());
//            double avgBuyPrice = (updatePortfolio.getAvg_buy_price() + buyRequest.getSecurity().getMarketPrice()) / 2;
//            updatePortfolio.setAvg_buy_price(avgBuyPrice);
//            Portfolio obj = portfolioService.savePortfolio(updatePortfolio);

            existInPortfolioResultMap.put("true", updatePortfolio);
        }
        else {
            System.err.println("New ticker");
            existInPortfolioResultMap.put("false", null);
        }

        return existInPortfolioResultMap;
    }

    public boolean saveInPortfolio(BuyRequest buyRequest) {
        Portfolio portfolio = new Portfolio();
        portfolio.setQuantity(buyRequest.getQuantity());
        return true;
    }
}
