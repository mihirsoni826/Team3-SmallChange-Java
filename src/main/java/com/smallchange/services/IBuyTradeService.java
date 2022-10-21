package com.smallchange.services;

import com.smallchange.entities.BuyReqEntity;

public interface IBuyTradeService {
    boolean postBuyTrade(BuyReqEntity reqBody);
}
