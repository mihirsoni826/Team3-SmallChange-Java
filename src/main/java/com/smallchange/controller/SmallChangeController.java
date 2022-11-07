package com.smallchange.controller;

import com.smallchange.buytrade.sellTradeServiceImp;
import com.smallchange.entities.*;
import com.smallchange.implementation.BuyTradeServiceImpl;

import com.smallchange.payload.UserEmailPayload;
import com.smallchange.services.IBankAccountService;
import com.smallchange.services.IPortfolioService;
import com.smallchange.services.ISecurityService;

import com.smallchange.services.IPortfolioService;
import com.smallchange.services.IUserService;

import com.smallchange.services.TradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
class SmallChangeController {

    @Autowired
    BuyTradeServiceImpl buyTradeSvc;
    @Autowired
    sellTradeServiceImp sellTradeSvc;

    @Autowired
    IPortfolioService portfolioService;

    @Autowired
    TradeHistoryService tradeHistoryService;

    @Autowired

    ISecurityService securityService;

    @Autowired
    IBankAccountService bankAccountService;

    @Autowired
    IUserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> postSignUp(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> postSignIn(@RequestBody LoginPayload loginPayload) {
        return userService.authenticateUser(loginPayload);
    }


    @RequestMapping(value = "/buy-trade", method = RequestMethod.POST, consumes="application/json")
    public boolean postBuyTrade(@RequestBody BuyRequest reqBody) {
        return buyTradeSvc.postBuyTrade(reqBody);
    }

    @RequestMapping(value = "/sell-trade", method = RequestMethod.POST, consumes="application/json")
    public String sell(@RequestBody sellModel reqBody) {
        return sellTradeSvc.sellTrade(reqBody);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/portfolio", method = RequestMethod.POST, consumes="application/json")
    public Optional<List<Portfolio>> getAllPortfolio(@RequestBody LoginPayload loginPayload) throws SQLException {
        return portfolioService.getPortfolioData(loginPayload.getEmail());
    }


    @RequestMapping(value = "/trade-history", method = RequestMethod.POST, consumes="application/json")
    public Optional<List<TradeHistory>> getAllTradeHistoryData(@RequestBody TradeHistory history) throws SQLException {
        return tradeHistoryService.getTradeHistoryData(history.getEmail());
    }

    @GetMapping("/securities")
    public List<Security> getSecurityDetails() {
        return securityService.getSecurityDetails();
    }

    @PostMapping("/bank-details")
    public BankAccount getBankAccountDetails(@RequestBody UserEmailPayload payload) {
        return bankAccountService.getBankDetailsForUser(payload.getEmail()).orElse(null);
    }

}