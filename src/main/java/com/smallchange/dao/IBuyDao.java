package com.smallchange.dao;

import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.SecurityEntity;

import java.sql.SQLException;

public interface IBuyDao {
    boolean registerBuyTrade(BuyReqEntity buyReq);

    double getAccountBalance(String accountNumber) throws SQLException;

    boolean updateAccountBalance(boolean buy, double amount, String accountNumber) throws SQLException;

    SecurityEntity getSecurityEntity(String ticker) throws SQLException;
}