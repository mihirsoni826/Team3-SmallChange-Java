package com.smallchange.controller;

import com.smallchange.buytrade.sellTradeServiceImp;
import com.smallchange.entities.Portfolio;
import com.smallchange.entities.sellModel;
import com.smallchange.implementation.BuyTradeServiceImpl;
import com.smallchange.entities.BuyRequest;
import com.smallchange.services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
class SmallChangeController {

    @Autowired
    BuyTradeServiceImpl buyTradeSvc;
    @Autowired
    sellTradeServiceImp sellTradeSvc;

    @Autowired
    IPortfolioService portfolioService;

    @RequestMapping(value = "/buy-trade", method = RequestMethod.POST, consumes="application/json")
    public boolean postBuyTrade(@RequestBody BuyRequest reqBody) {
        return buyTradeSvc.postBuyTrade(reqBody);
    }


    @RequestMapping(value = "/sell-trade", method = RequestMethod.POST, consumes="application/json")
    public String sell(@RequestBody sellModel reqBody) {
        return sellTradeSvc.sellTrade(reqBody);
    }

    @RequestMapping(value = "/portfolio/{email}", method = RequestMethod.GET)
    public Optional<List<Portfolio>> getAllPortfolio(@PathVariable String email) throws SQLException {
        return portfolioService.getPortfolioData(email);
    }

}
