package com.smallchange.controller;

import com.smallchange.buytrade.sellTradeServiceImp;
import com.smallchange.implementation.TradeHistoryServiceImpl;
import com.smallchange.entities.PortfolioEntity;
import com.smallchange.entities.TradeHistoryEntity;
import com.smallchange.entities.sellModel;
import com.smallchange.implementation.BuyTradeServiceImpl;
import com.smallchange.entities.BuyReqEntity;
import com.smallchange.implementation.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
class SmallChangeController {

    @Autowired
    BuyTradeServiceImpl buyTradeSvc;
    @Autowired
    sellTradeServiceImp sellTradeSvc;

    @Autowired
    PortfolioServiceImpl portfolioService;

    @Autowired
    TradeHistoryServiceImpl tradeHistoryService;

    @RequestMapping(value = "/buy-trade", method = RequestMethod.POST, consumes="application/json")
    public boolean postBuyTrade(@RequestBody BuyReqEntity reqBody) {
        return buyTradeSvc.postBuyTrade(reqBody);
    }


    @RequestMapping(value = "/sell-trade", method = RequestMethod.POST, consumes="application/json")
    public String sell(@RequestBody sellModel reqBody) {
        return sellTradeSvc.sellTrade(reqBody);
    }

    @GetMapping(value = "/portfolio/Equity")
    public List<PortfolioEntity> getAllEquity() throws SQLException {
        return portfolioService.getPortfolioData("Equity");
    }

    @GetMapping(value = "/portfolio/Mutual-funds")
    public List<PortfolioEntity> getAllMutualFunds() throws SQLException {
        return portfolioService.getPortfolioData("Mutual Funds");
    }

    @GetMapping(value = "/tradehistory")
    public List<TradeHistoryEntity> getAllTradeHistory() throws SQLException{
        return tradeHistoryService.getTradeHistory();
    }
}
