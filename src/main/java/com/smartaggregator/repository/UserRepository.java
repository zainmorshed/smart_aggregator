package com.smartaggregator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartaggregator.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByName(String name);
}

