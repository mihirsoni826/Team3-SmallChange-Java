package com.smallchange.services;

import com.smallchange.entities.Portfolio;
import com.smallchange.entities.TradeHistory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TradeHistoryService {
    Optional<List<TradeHistory>> getTradeHistoryData(String email);
}
