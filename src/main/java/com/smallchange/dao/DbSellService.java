package com.smallchange.dao;

import com.smallchange.connectionfactory.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DbSellService {

    Connection connection;

    public DbSellService() throws ClassNotFoundException {
        this.connection = ConnectionFactory.getConnection();
    }


   public double getQuantity(String ticker) {
       String sql = "SELECT * from PORTFOLIO WHERE TICKER=?";
       PreparedStatement preparedStatement = null;
       Double qua = 0.0;

       try {
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1, ticker);

           ResultSet resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
               qua = resultSet.getDouble(3);
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
       return qua;
   }

   public int updateQua(Double qua,String ticker){
       int count=0;
       String sql = "UPDATE PORTFOLIO SET QUANTITY=? WHERE TICKER=?";
       PreparedStatement preparedStatement = null;

       try {
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setDouble(1, qua);
           preparedStatement.setString(2, ticker);
            count = preparedStatement.executeUpdate();

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
        return count;
   }


    public int deleteSec(String ticker){
        int count=0;
        String sql = "DELETE FROM PORTFOLIO WHERE TICKER=?";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ticker);
            count = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }
}
