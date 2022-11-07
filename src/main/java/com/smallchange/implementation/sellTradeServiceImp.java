package com.smallchange.implementation;

import com.smallchange.dao.DbSellService;
import com.smallchange.entities.*;
import com.smallchange.implementation.PortfolioServiceImpl;
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
    private TradeHistoryRepository tradeRepo;

    @Override
    public String sellTrade(sellModel reqBody) {

        String ticker=reqBody.getSecurity().getTicker();

        Portfolio portObj;
        try {

            portObj=repository.findSec(ticker);

            if(portObj==null)
                return "Stock not present in portfolio";

            int res= portObj.getQuantity() - reqBody.getQuantity();
            TradeHistory tradeHistory = new TradeHistory();

            tradeHistory.setTicker(ticker);
            tradeHistory.setSecurityName("CRM");
            tradeHistory.setAccountType("BRokerage");
            tradeHistory.setTransactionDate(new Timestamp(reqBody.getTimeMilli()));
            tradeHistory.setTradeType(sellModel.SELL);
            tradeHistory.setAssetClass("index");
            tradeHistory.setTradePrice(100);
            tradeHistory.setQuantity(portObj.getQuantity());
            tradeHistory.setEmail(portObj.getUser().getEmail());

            tradeRepo.save(tradeHistory);

            if(res<0)
            {
               return (" quantity not available");
            }
            else if(res > 0)
            {

                portObj.setQuantity(res);
                portObj=service.savePortfolio(portObj);
                if(portObj.getQuantity()==res)
                {
                   return ("Sell Succesfull");
                }
            }
            else
            {
                repository.deleteById(portObj.getId());
                   return ("Sell Succesfull");

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



        return "Failed";
    }
}
