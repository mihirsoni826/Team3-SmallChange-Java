package com.smallchange.implementation;

import com.smallchange.entities.Portfolio;
import com.smallchange.repository.PortfolioRepository;
import com.smallchange.services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioServiceImpl implements IPortfolioService {

    @Autowired
    private PortfolioRepository repository;
    @Override
    public Optional<List<Portfolio>> getPortfolioData(String email){
        return repository.findAllByEmail(email);
    }
}
