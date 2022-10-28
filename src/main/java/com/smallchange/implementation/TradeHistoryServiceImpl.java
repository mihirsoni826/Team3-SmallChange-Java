package com.smallchange.implementation;

import com.smallchange.dao.PortfolioDaoImpl;
import com.smallchange.dao.TradeHistoryDaoImpl;
import com.smallchange.entities.TradeHistoryEntity;
import com.smallchange.services.TradeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class TradeHistoryServiceImpl implements TradeHistoryService {

    @Autowired
    TradeHistoryDaoImpl tradeHistoryDao;

    @Override
    public List<TradeHistoryEntity> getTradeHistory() throws SQLException {
        return tradeHistoryDao.getTradeHistory();
    }
}
