package com.smallchange.implementation;

import com.smallchange.entities.*;
import com.smallchange.repository.PortfolioRepository;
import com.smallchange.repository.TradeHistoryRepository;
import com.smallchange.services.IPortfolioService;
import com.smallchange.services.SellTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class sellTradeServiceImp implements SellTradeService {


    @Autowired
    IPortfolioService service;

    @Autowired
    private PortfolioRepository repository;


    @Autowired
    private BuyTradeServiceImpl buyImp;

    @Autowired
    private TradeHistoryRepository tradeRepo;

    @Override
    public boolean sellTrade(sellModel reqBody) {

        String ticker=reqBody.getSecurity().getTicker();

        Portfolio portObj;
        try {

            portObj=repository.findSec(ticker);
            Security populatedSecurityObj = buyImp.getSecurityDetails((reqBody.getSecurity().getTicker()));
            reqBody = populateSecurityInBuyRequest(reqBody, populatedSecurityObj);

            if(portObj==null)
                return false;

            int res= portObj.getQuantity() - reqBody.getQuantity();
            TradeHistory tradeHistory = new TradeHistory();
            Security security = reqBody.getSecurity();



            if(res<0)
            {
               return false;
            }
            else if(res > 0)
            {

                portObj.setQuantity(res);
                portObj=service.savePortfolio(portObj);
                if(portObj.getQuantity()==res)
                {
                    setTradeHistory(  tradeHistory, security, reqBody);
                   return true;
                }
            }
            else
            {
                repository.deleteById(portObj.getId());
                setTradeHistory(  tradeHistory, security, reqBody);
                   return true;

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        return false;
    }


    public void setTradeHistory( TradeHistory tradeHistory, Security security,sellModel reqBody){
        tradeHistory.setTicker(security.getTicker());
        tradeHistory.setSecurityName(security.getSecurityName());
        tradeHistory.setAccountType(security.getAccountType());
        tradeHistory.setTransactionDate(new Timestamp(reqBody.getTimeInMilliseconds()));
        tradeHistory.setTradeType(BuyRequest.BUY);
        tradeHistory.setAssetClass(security.getAssetClass());
        tradeHistory.setTradePrice(security.getMarketPrice() * reqBody.getQuantity());
        tradeHistory.setQuantity(reqBody.getQuantity());
        tradeHistory.setEmail(reqBody.getUser().getEmail());

        tradeRepo.save(tradeHistory);
    }


    public sellModel populateSecurityInBuyRequest(sellModel reqBody, Security populatedSecurityObj) {
        reqBody.getSecurity().setAccountType(populatedSecurityObj.getAccountType());
        reqBody.getSecurity().setSecurityName(populatedSecurityObj.getSecurityName());
        reqBody.getSecurity().setMarketPrice(populatedSecurityObj.getMarketPrice());
        reqBody.getSecurity().setAssetClass(populatedSecurityObj.getAssetClass());
        reqBody.getSecurity().setSubAccountType(populatedSecurityObj.getSubAccountType());

        return reqBody;
    }



}
