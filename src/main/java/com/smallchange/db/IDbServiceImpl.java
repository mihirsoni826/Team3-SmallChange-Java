package com.smallchange.db;

import com.smallchange.connectionfactory.ConnectionFactory;
import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.SecurityEntity;
import oracle.jdbc.proxy.annotation.Pre;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class IDbServiceImpl implements IDbService {

    Connection connection;

    public IDbServiceImpl() throws ClassNotFoundException {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public boolean registerBuyTrade(BuyReqEntity buyReq) {
//        writeToTradeHistory(buyReq);
        System.out.println("Trade bought");
        return true;
    }

    private boolean writeToTradeHistory(BuyReqEntity buyReq) throws SQLException {
        String sql = "INSERT INTO trade_history VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        return true;
    }

    @Override
    public double getAccountBalance(String accountNumber) throws SQLException {
        String sql = "SELECT balance from bank_account_details WHERE account_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,accountNumber);

        ResultSet resultSet = preparedStatement.executeQuery();

        double balance = 0;

        while(resultSet.next()) {
            balance = Double.parseDouble(resultSet.getString(1));
        }
        return balance;
    }

    @Override
    public boolean updateAccountBalance(boolean buy, double amount, String accountNumber) throws SQLException {
        double balance = getAccountBalance(accountNumber);
        if(buy) {
            balance -= amount;
        } else {
            balance += amount;
        }

        String sql = "UPDATE bank_account_details SET balance=? WHERE account_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDouble(1, balance);
        preparedStatement.setString(2, accountNumber);

        int count = preparedStatement.executeUpdate();

        return count > 1;
    }

    @Override
    public SecurityEntity getSecurityEntity(String ticker) throws SQLException {
        String sql = "SELECT * FROM securities WHERE ticker=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ticker);

        ResultSet resultSet = preparedStatement.executeQuery();
        SecurityEntity security = null;

        while(resultSet.next()) {
            String name = resultSet.getString(2);
            double marketPrice = resultSet.getDouble(3);
            String assetClass = resultSet.getString(4);
            String accountType = resultSet.getString(5);
            String subAccountType = resultSet.getString(6);

            security = new SecurityEntity(ticker, name, assetClass, accountType, subAccountType, marketPrice);
        }
        return security;
    }
}
