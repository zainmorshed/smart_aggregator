package com.smartaggregator.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartaggregator.entity.Portfolio;


public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Optional<Portfolio> findByUserId(String userId);
    
}
