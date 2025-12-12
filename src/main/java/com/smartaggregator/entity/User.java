package com.smartaggregator.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user") // <-- safe name, not reserved
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // JPA generates the UUID
    private String id;
    private String name;

    @ElementCollection
    private List <String> stocks;
    @ElementCollection
    private List <String> cryptos;

    public User(String id, String name, List<String> stocks, List<String> cryptos){
        this.id = id;
        this.name = name;
        this.stocks = stocks;
        this.cryptos = cryptos;
    }
    
    public User() {

    }

    
    public String getId() {
        return id;
    }  
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getStocks() {
        return stocks;
    }
    public void setStocks(List<String> stocks) {
        this.stocks = stocks;
    }
    public List<String> getCryptos() {
        return cryptos;
    }
    public void setCryptos(List<String> cryptos) {
        this.cryptos = cryptos;
    }
    
    
}
