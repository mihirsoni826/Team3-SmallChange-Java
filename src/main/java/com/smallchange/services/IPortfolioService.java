package com.smallchange.services;

import com.smallchange.entities.Portfolio;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public interface IPortfolioService {
    Optional<List<Portfolio>> getPortfolioData(String email);
}
