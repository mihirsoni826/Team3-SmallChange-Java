package com.smallchange.db;

import com.smallchange.connectionfactory.ConnectionFactory;
import com.smallchange.entities.BuyReqEntity;
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
    public double getAccountBalance(int accountNumber) throws SQLException {
        String sql = "SELECT balance from bank_account_details WHERE account_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, Integer.toString(accountNumber));

        ResultSet resultSet = preparedStatement.executeQuery();

        double balance = 0;

        while(resultSet.next()) {
            balance = Double.parseDouble(resultSet.getString(1));
        }

        System.err.println("Balance iDbServiceImpl.getAccountBalance(): " + balance);
        return balance;
    }

    @Override
    public boolean updateAccountBalance(boolean buy, double amount, int accountNumber) throws SQLException {
        double balance = getAccountBalance(accountNumber);
        if(buy) {
            balance -= amount;
        } else {
            balance += amount;
        }

        String sql = "UPDATE bank_account_details SET balance=? WHERE account_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setDouble(1, balance);
        preparedStatement.setString(2, Integer.toString(accountNumber));

        int count = preparedStatement.executeUpdate();

        return count > 1;
    }
}
