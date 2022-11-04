package com.smallchange.services;

import com.smallchange.entities.Security;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ISecurityService {
    Optional<Security> getSecurityByTicker(String ticker);
}
