package com.smallchange.implementation;

import com.smallchange.entities.TradeHistory;
import com.smallchange.repository.TradeHistoryRepository;
import com.smallchange.services.TradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeHistoryServiceImpl implements TradeHistoryService {

    @Autowired
    private TradeHistoryRepository tradeHistoryRepository;

    @Override
    public Optional<List<TradeHistory>> getTradeHistoryData(String email) {
        return tradeHistoryRepository.findAllByEmail(email);
    }
}
