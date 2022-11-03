package com.smallchange.services;

import com.smallchange.entities.BuyRequest;
import com.smallchange.entities.Security;
import org.springframework.stereotype.Service;

@Service
public interface IBuyTradeService {
    boolean postBuyTrade(BuyRequest reqBody);
    Security getSecurityDetails(String ticker);
}
