package com.smallchange.controller;

import com.smallchange.buytrade.BuyTradeServiceImpl;
import com.smallchange.buytrade.sellTradeServiceImp;
import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.sellModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
class SellTradeController {

        @Autowired
        sellTradeServiceImp sellTradeSvc;

        @GetMapping("/")
        public String landingPage() {
            return "Working";
        }

        @RequestMapping(value = "/sell-trade", method = RequestMethod.POST, consumes="application/json")
        public String sell(@RequestBody sellModel reqBody) {
            return sellTradeSvc.sellTrade(reqBody);
        }
    }