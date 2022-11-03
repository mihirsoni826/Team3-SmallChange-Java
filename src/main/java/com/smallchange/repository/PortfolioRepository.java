package com.smallchange.repository;

import com.smallchange.entities.Portfolio;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    //@EntityGraph(value = "Portfolio.user", type = EntityGraph.EntityGraphType.FETCH)
    @Query("from Portfolio p inner join fetch p.user where p.user.email = :email")
    public Optional<List<Portfolio>> findAllByEmail(String email);

}
