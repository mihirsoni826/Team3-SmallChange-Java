package com.smallchange.db;

import com.smallchange.entities.BuyReqEntity;

import java.sql.SQLException;

public interface IDbService {
    boolean registerBuyTrade(BuyReqEntity buyReq);

    double getAccountBalance(int accountNumber) throws SQLException;

    boolean updateAccountBalance(boolean buy, double amount, int accountNumber) throws SQLException;
}
