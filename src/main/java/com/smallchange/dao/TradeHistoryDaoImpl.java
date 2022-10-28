package com.smallchange.dao;

import com.smallchange.connectionfactory.ConnectionFactory;
import com.smallchange.entities.PortfolioEntity;
import com.smallchange.entities.TradeHistoryEntity;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TradeHistoryDaoImpl {
    Connection connection;

    public TradeHistoryDaoImpl() throws ClassNotFoundException {
        this.connection = ConnectionFactory.getConnection();
    }


    public List<TradeHistoryEntity> getTradeHistory() throws SQLException {
        String sql = "SELECT t.transaction_id, t.ticker, t.security_name, t.account_type, t.transaction_date ," +
                "t.trade_type, t.asset_class, t.trade_price, t.quantity  FROM Trade_History t";



        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        List<TradeHistoryEntity> tradeHistoryEntityList = new ArrayList<>();
        while (rs.next()) {
            String transaction_id = rs.getString("transaction_id");
            String ticker = rs.getString("ticker");
            String security_name = rs.getString("security_name");
            String account_type = rs.getString("account_type");
            Timestamp transaction_date = rs.getTimestamp("transaction_date");
            String trade_type = rs.getString("trade_type");
            String asset_class = rs.getString("asset_class");
            double trade_price = rs.getDouble("trade_price");
            int quantity = rs.getInt("quantity");


            TradeHistoryEntity trade = new TradeHistoryEntity(transaction_id, ticker, security_name, account_type,
                    transaction_date, trade_type, asset_class, trade_price, quantity);
            tradeHistoryEntityList.add(trade);
            System.out.println(trade);
        }
        return tradeHistoryEntityList;
    }
}
