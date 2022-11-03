package com.smallchange.dao;

import com.smallchange.entities.Portfolio;

import java.sql.SQLException;
import java.util.List;

public interface IPortfolioDao {
    List<Portfolio> getPortfolioData(String brokerage_type) throws SQLException;
}
