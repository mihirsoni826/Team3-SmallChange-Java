package com.smallchange.implementation;

import com.smallchange.db.PortfolioDaoImpl;
import com.smallchange.entities.PortfolioEntity;
import com.smallchange.services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class PortfolioServiceImpl implements IPortfolioService {

    @Autowired
    PortfolioDaoImpl portfolioDao;

    @Override
    public List<PortfolioEntity> getPortfolioData(String brokerage_type) throws SQLException {
        return portfolioDao.getPortfolioData(brokerage_type);
    }
}
