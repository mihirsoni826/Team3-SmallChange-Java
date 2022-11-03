package com.smallchange.buytrade;

import com.smallchange.dao.DbSellService;
import com.smallchange.entities.Portfolio;
import com.smallchange.entities.sellModel;
import com.smallchange.implementation.PortfolioServiceImpl;
import com.smallchange.repository.PortfolioRepository;
import com.smallchange.services.IPortfolioService;
import com.smallchange.services.SellTradeService;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Port;

@Component
public class sellTradeServiceImp implements SellTradeService {

    @Autowired
    DbSellService db;

    @Autowired
    IPortfolioService service;

    @Autowired
    private PortfolioRepository repository;

    @Override
    public String sellTrade(sellModel reqBody) {

        String ticker=reqBody.getTicker();

        Portfolio portObj;
        try {

            portObj=repository.findSec(ticker);
            int res= portObj.getQuantity() - reqBody.getQuantity();

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
