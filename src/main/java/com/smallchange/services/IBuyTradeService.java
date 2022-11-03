package com.smallchange.services;

import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.Security;

public interface IBuyTradeService {
    boolean postBuyTrade(BuyReqEntity reqBody);
    Security getSecurityDetails(String ticker);
}
