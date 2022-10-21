package com.smallchange.services;

import com.smallchange.entities.BuyReqEntity;
import com.smallchange.entities.SecurityEntity;

import java.util.List;

public interface IBuyTradeService {
    boolean postBuyTrade(BuyReqEntity reqBody);
    SecurityEntity getSecurityDetails(String ticker);
}
