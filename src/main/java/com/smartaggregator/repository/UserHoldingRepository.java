package com.smartaggregator.repository;

import com.smartaggregator.entity.UserHolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserHoldingRepository extends JpaRepository<UserHolding, Long> {
    List<UserHolding> findByUsernameAndType(String username, String type);
}