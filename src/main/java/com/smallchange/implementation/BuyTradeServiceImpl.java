package com.smallchange.implementation;

import com.smallchange.dao.BuyDaoImpl;
import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.SecurityEntity;
import com.smallchange.services.IBuyTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;

@Component
public class BuyTradeServiceImpl implements IBuyTradeService {

    @Autowired
    BuyDaoImpl db;

    public boolean sufficientBalance(String accountNumber, double value) throws SQLException {
        double balance = db.getAccountBalance(accountNumber);
        return balance >= value;
    }

    public Date convertMilliToDate(Long timeMilli) {
        return new Date(timeMilli);
    }

    @Override
    public boolean postBuyTrade(BuyReqEntity reqBody) {
        Date date = convertMilliToDate(reqBody.getTimeInMilliseconds());
        reqBody.setDateOfPurchase(date.toString());

        SecurityEntity populatedSecurityObj = getSecurityDetails((reqBody.getSecurity().getTicker()));
        reqBody.getSecurity().setAccountType(populatedSecurityObj.getAccountType());
        reqBody.getSecurity().setSecurityName(populatedSecurityObj.getSecurityName());
        reqBody.getSecurity().setMarketPrice(populatedSecurityObj.getMarketPrice());
        reqBody.getSecurity().setAssetClass(populatedSecurityObj.getAssetClass());
        reqBody.getSecurity().setSubAccountType(populatedSecurityObj.getSubAccountType());

        System.out.println(reqBody);
        double value = reqBody.getQuantity() * reqBody.getSecurity().getMarketPrice();

        try {
            if(sufficientBalance(reqBody.getAccountNumber(), value)) {
                boolean balanceUpdated = db.updateAccountBalance(true, value, reqBody.getAccountNumber());
                return db.registerBuyTrade(reqBody);
            }
            else {
                System.out.println("Insufficient funds");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SecurityEntity getSecurityDetails(String ticker) {
        SecurityEntity security = null;
        try {
            security = db.getSecurityEntity(ticker);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return security;
    }
}
