package com.smallchange.dao;

import com.smallchange.connectionfactory.ConnectionFactory;
import com.smallchange.dao.IPortfolioDao;
import com.smallchange.entities.PortfolioEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PortfolioDaoImpl implements IPortfolioDao {

    Connection connection;

    public PortfolioDaoImpl() throws ClassNotFoundException {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public List<PortfolioEntity> getPortfolioData(String brokerage_type) throws SQLException {
        String sql = "SELECT p.ticker,avg_buy_price,quantity,current_market_price FROM Portfolio p \n" +
                "LEFT JOIN Securities s on p.ticker=s.ticker where s.sub_account_type=?";

        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, brokerage_type);
        ResultSet rs = stmt.executeQuery();

        List<PortfolioEntity> portfolioData = new ArrayList<>();
        while (rs.next()) {
            String ticker = rs.getString("ticker");
            double avg_buy_price = rs.getDouble("avg_buy_price");
            int quantity = rs.getInt("quantity");
            double ltp = rs.getDouble("current_market_price");

            PortfolioEntity p = new PortfolioEntity(ticker, quantity, avg_buy_price,ltp);
            portfolioData.add(p);
            System.out.println(p);
        }
        return portfolioData;
    }
}
