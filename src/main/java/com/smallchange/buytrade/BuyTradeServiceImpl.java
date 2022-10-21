package com.smallchange.buytrade;

import com.smallchange.db.IDbServiceImpl;
import com.smallchange.entities.BuyReqEntity;
import com.smallchange.services.IBuyTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;

@Component
public class BuyTradeServiceImpl implements IBuyTradeService {

    @Autowired
    IDbServiceImpl db;

    public boolean sufficientBalance(int accountNumber, double value) throws SQLException {
        double balance = db.getAccountBalance(accountNumber);
        return balance >= value;
    }

    public Date convertMilliToDate(Long timeMilli) {
        return new Date(timeMilli);
    }

    @Override
    public boolean postBuyTrade(BuyReqEntity reqBody) {
        Date date = convertMilliToDate(reqBody.getTimeMilli());
        reqBody.setDateOfPurchase(date.toString());
        System.out.println(reqBody);
        double value = reqBody.getQuantity() * reqBody.getSecurityPrice();

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
}
