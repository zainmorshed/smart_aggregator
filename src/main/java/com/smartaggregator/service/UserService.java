package com.smartaggregator.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.Collections;

import com.smartaggregator.entity.User;
import com.smartaggregator.repository.UserRepository;

@Service  
public class UserService {
    // without the db integration we can use a hashmap to store user info:

    // private static final Map<String, List<String>> STOCKS = new HashMap<>();
    // private static final Map<String, List<String>> CRYPTOS = new HashMap<>();
    // private static final Map<String, User> USER_MAP = new HashMap<>();


    // public List<String> getTrackedStocks(String username){
    //     return STOCKS.getOrDefault(username, List.of());
    // }

    // public List<String> getTrackedCryptos(String username) {
    //     return CRYPTOS.getOrDefault(username, List.of());
    // }


    // public void addUser(String username, List<String> stocks, List<String> cryptos) {
    //     STOCKS.put(username, stocks);
    //     CRYPTOS.put(username, cryptos);
    // }

    // public List<User> getAllUsers() {
    // return new ArrayList<>(USER_MAP.values());
    // }

    //persist data to db:

    @Autowired
    private UserRepository userRepo;

    public void addUser(String username, List<String> stocks, List<String> cryptos) {

        //check if the user already exists in db
        User user = userRepo.findByName(username).orElse(new User());

        user.setName(username);
        user.setStocks(stocks);
        user.setCryptos(cryptos);
        userRepo.save(user);

        // if(user != null) {
        //     user.setStocks(stocks);
        //     user.setCryptos(cryptos);
        // } else {
        //     String id = UUID.randomUUID().toString();
        //     user = new User(id, username, stocks, cryptos);
        // }
        // userRepo.save(user);


        //generate a UUID for id - creates a unique id independent of username
        // String id = UUID.randomUUID().toString();

        // User user = new User(
        //     id,
        //     username, 
        //     stocks,
        //     cryptos
        // );


    }


    // need to add these methods
    public List<String> getTrackedStocks(String username) {
        return userRepo.findByName(username)
                       .map(User::getStocks)
                       .orElse(Collections.emptyList());
    }

    public List<String> getTrackedCryptos(String username) {
        return userRepo.findByName(username)
                       .map(User::getCryptos)
                       .orElse(Collections.emptyList());
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
