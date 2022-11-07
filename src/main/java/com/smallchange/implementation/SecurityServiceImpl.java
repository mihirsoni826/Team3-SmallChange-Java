package com.smallchange.implementation;

import com.smallchange.entities.Security;
import com.smallchange.repository.SecurityRepository;
import com.smallchange.services.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecurityServiceImpl implements ISecurityService {

    @Autowired
    SecurityRepository repository;

    @Override
    public Optional<Security> getSecurityByTicker(String ticker) {
        return repository.findById(ticker);
    }

    @Override
    public List<Security> getSecurityDetails() {
        return repository.findAll();
    }
}
