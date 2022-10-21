package com.smallchange.controller;

import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.PortfolioEntity;
import com.smallchange.implementation.BuyTradeServiceImpl;
import com.smallchange.implementation.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class PortfolioController {

    @Autowired
    PortfolioServiceImpl portfolioService;

    @GetMapping(value = "/portfolio/Equity")
    public List<PortfolioEntity> getAllEquity() throws SQLException {
        return portfolioService.getPortfolioData("Equity");
    }

    @GetMapping(value = "/portfolio/Mutual-funds")
    public List<PortfolioEntity> getAllMutualFunds() throws SQLException {
        return portfolioService.getPortfolioData("Mutual Funds");
    }
}
