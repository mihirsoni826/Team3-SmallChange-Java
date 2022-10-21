package com.smallchange.controller;

import com.smallchange.buytrade.BuyTradeServiceImpl;
import com.smallchange.entities.BuyReqEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
class BuyTradeController {

    @Autowired
    BuyTradeServiceImpl buyTradeSvc;


    @RequestMapping(value = "/buy-trade", method = RequestMethod.POST, consumes="application/json")
    public boolean postBuyTrade(@RequestBody BuyReqEntity reqBody) {
        return buyTradeSvc.postBuyTrade(reqBody);
    }
}
