package com.smallchange.dao;

import com.smallchange.entities.TradeHistoryEntity;

import java.sql.SQLException;
import java.util.List;

public interface ITradeHistoryDao {
    List<TradeHistoryEntity> getTradeHistory() throws SQLException;
}
