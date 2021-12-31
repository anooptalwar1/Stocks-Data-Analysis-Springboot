package com.example.StocksDataAnalytics.repository;

import com.example.StocksDataAnalytics.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByUserName(String userName);
}