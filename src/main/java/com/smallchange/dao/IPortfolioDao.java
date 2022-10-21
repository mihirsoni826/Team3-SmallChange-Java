package com.smallchange.dao;

import com.smallchange.entities.PortfolioEntity;

import java.sql.SQLException;
import java.util.List;

public interface IPortfolioDao {
    List<PortfolioEntity> getPortfolioData(String brokerage_type) throws SQLException;
}
