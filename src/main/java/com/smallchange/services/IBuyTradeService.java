package com.smallchange.services;

import com.smallchange.entities.BuyRequest;
import com.smallchange.entities.Portfolio;
import com.smallchange.entities.Security;
import com.smallchange.entities.Users;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public interface IBuyTradeService {
    boolean postBuyTrade(BuyRequest reqBody);
    Security getSecurityDetails(String ticker);
    Users getUserDetails(String email);
    boolean sufficientBalance(String accountNumber, double value) throws SQLException;
    void updateAccountBalance(String accountNumber, double value);
    void addPortfolio(BuyRequest buyRequest);
    void updatePortfolio(Portfolio updatePortfolio, BuyRequest buyRequest);
}
