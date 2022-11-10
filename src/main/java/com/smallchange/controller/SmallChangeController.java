package com.smallchange.controller;


import com.smallchange.entities.*;
import com.smallchange.implementation.BuyTradeServiceImpl;
import com.smallchange.implementation.sellTradeServiceImp;
import com.smallchange.payload.UserEmailPayload;
import com.smallchange.services.*;
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

    public String randomAccountNumber() {
        String numeric = "0123456789";
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = (int)(numeric.length()* Math.random());
            sb.append(numeric.charAt(index));
        }
        return sb.toString();
    }

    public String randomBankName() {
        String[] banks = {"Kotak Mahindra", "ICICI", "HDFC", "IDFC", "HSBC", "Indusind", "SBI"};
        int index = (int) (Math.random() * banks.length);
        return banks[index];
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> postSignUp(@RequestBody Users user) {
        System.err.println(user);
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/create-bank-account", method = RequestMethod.POST, consumes = "application/json")
    public void createAccountPostSignUp(@RequestBody LoginPayload loginPayload) {
        BankAccount newBankAccount = new BankAccount(randomAccountNumber(),  randomBankName() +" Bank", 10000, loginPayload.getEmail());
        System.err.println(newBankAccount);
        bankAccountService.createBankAccount(newBankAccount);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/signin", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<?> postSignIn(@RequestBody LoginPayload loginPayload) {
        return userService.authenticateUser(loginPayload);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes="application/json")
    public Users postSignIn(@RequestBody Users user) {
//        System.out.println(user);
        return userService.getUserByEmail(user.getEmail()).orElse(null);
    }


    @RequestMapping(value = "/buy-trade", method = RequestMethod.POST, consumes="application/json")
    public boolean postBuyTrade(@RequestBody BuyRequest reqBody) {
        return buyTradeSvc.postBuyTrade(reqBody);
    }

    @RequestMapping(value = "/sell-trade", method = RequestMethod.POST, consumes="application/json")
    public boolean sell(@RequestBody sellModel reqBody) {
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
    public List<BankAccount> getBankAccountDetails(@RequestBody UserEmailPayload payload) {
        return bankAccountService.getBankDetailsForUser(payload.getEmail());
    }

}