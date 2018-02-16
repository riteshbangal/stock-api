package com.stock.db.service.repository;

import com.stock.db.service.repository.entities.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Integer> {
    List<Quote> findByUserName(String username);
}
