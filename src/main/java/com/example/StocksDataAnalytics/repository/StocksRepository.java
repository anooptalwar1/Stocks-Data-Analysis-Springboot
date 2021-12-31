package com.example.StocksDataAnalytics.repository;

import com.example.StocksDataAnalytics.model.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface StocksRepository extends JpaRepository<Stocks, Integer>, JpaSpecificationExecutor<Stocks> {
}