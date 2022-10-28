package com.smallchange.services;

import com.smallchange.entities.TradeHistoryEntity;

import java.sql.SQLException;
import java.util.List;

public interface TradeHistoryService {
    List<TradeHistoryEntity> getTradeHistory() throws SQLException;
}
