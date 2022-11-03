package com.smallchange.services;

import com.smallchange.entities.BuyRequest;
import com.smallchange.entities.Security;

public interface IBuyTradeService {
    boolean postBuyTrade(BuyRequest reqBody);
    Security getSecurityDetails(String ticker);
}
