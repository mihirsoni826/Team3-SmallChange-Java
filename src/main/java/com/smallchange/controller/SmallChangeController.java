package com.smallchange.controller;

import com.smallchange.buytrade.sellTradeServiceImp;
import com.smallchange.entities.LoginPayload;
import com.smallchange.entities.Portfolio;
import com.smallchange.entities.sellModel;
import com.smallchange.implementation.BuyTradeServiceImpl;
import com.smallchange.entities.BuyReqEntity;
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
    public boolean postBuyTrade(@RequestBody BuyReqEntity reqBody) {
        return buyTradeSvc.postBuyTrade(reqBody);
    }


    @RequestMapping(value = "/sell-trade", method = RequestMethod.POST, consumes="application/json")
    public String sell(@RequestBody sellModel reqBody) {
        return sellTradeSvc.sellTrade(reqBody);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/portfolio", method = RequestMethod.POST, consumes="application/json")
    public Optional<List<Portfolio>> getAllPortfolio(@RequestBody LoginPayload loginPayload) {
        System.out.println(loginPayload);
        String email = loginPayload.getEmail();
        return portfolioService.getPortfolioData(email);
    }

}
