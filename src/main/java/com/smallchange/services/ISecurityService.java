package com.smallchange.services;

import com.smallchange.entities.Security;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ISecurityService {
    Optional<Security> getSecurityByTicker(String ticker);
    List<Security> getSecurityDetails();
}
