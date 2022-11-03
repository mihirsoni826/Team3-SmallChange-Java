package com.smallchange.repository;

import com.smallchange.entities.Portfolio;
import com.smallchange.entities.TradeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeHistoryRepository extends JpaRepository<TradeHistory, Integer> {
    public Optional<List<TradeHistory>> findAllByEmail(String email);
}
