package com.smallchange.repository;

import com.smallchange.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
//    @Query(value = "SELECT p FROM Portfolio p " +
//       "LEFT JOIN fetch p.securities where p.sub_account_type=?1", nativeQuery = true)
//    List<Portfolio> fetchPortfolio(String brokerage_type);
}
