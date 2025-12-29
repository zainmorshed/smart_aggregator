package com.smartaggregator.entity;

import jakarta.persistence.*;

@Entity
public class UserHolding {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String username;    //the user that owns this 
    private String symbol;      // ticker symbol
    private String type;        //"STOCK" or "CRYPTO"
    private double quantity;    // #shares/coins owned
    
    public UserHolding() {}
    
    public UserHolding(String username, String symbol, String type, double quantity) {
        this.username = username;
        this.symbol = symbol;
        this.type = type;
        this.quantity = quantity;
    }
    
    public Long getId() { 
        return id; }

    public void setId(Long id) { 
        this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
}