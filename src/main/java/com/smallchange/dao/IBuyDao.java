package com.smallchange.dao;

import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.Security;

import java.sql.SQLException;

public interface IBuyDao {
    boolean registerBuyTrade(BuyReqEntity buyReq);

    double getAccountBalance(String accountNumber) throws SQLException;

    boolean updateAccountBalance(boolean buy, double amount, String accountNumber) throws SQLException;

    Security getSecurityEntity(String ticker) throws SQLException;
}
