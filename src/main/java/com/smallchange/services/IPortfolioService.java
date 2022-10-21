package com.smallchange.services;

import com.smallchange.entities.PortfolioEntity;

import java.sql.SQLException;
import java.util.List;

public interface IPortfolioService {
    List<PortfolioEntity> getPortfolioData(String brokerage_type) throws SQLException;
}
