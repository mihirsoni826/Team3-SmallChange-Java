package com.smallchange.services;

import com.smallchange.entities.Portfolio;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface IPortfolioService {
    List<Portfolio> getPortfolioData(Long id);
}
