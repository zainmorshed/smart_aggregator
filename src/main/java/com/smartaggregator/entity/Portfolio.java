package com.smartaggregator.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userId;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockHolding> stockHoldings = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CryptoHolding> cryptoHoldings = new ArrayList<>();

    public Portfolio() {
    }

    public Portfolio(Long id, String userId, 
        List<StockHolding> stockHoldings, List<CryptoHolding> cryptoHoldings){
        this.id = id;
        this.userId = userId;
        this.stockHoldings = stockHoldings;
        this.cryptoHoldings = cryptoHoldings;
    }



    public void addStock(StockHolding stock) {
        stockHoldings.add(stock);
        stock.setPortfolio(this);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<StockHolding> getStockHoldings() {
        return stockHoldings;
    }

    public void setStockHoldings(List<StockHolding> stockHoldings) {
        this.stockHoldings = stockHoldings;
    }

    public List<CryptoHolding> getCryptoHoldings() {
        return cryptoHoldings;
    }

    public void setCryptoHoldings(List<CryptoHolding> cryptoHoldings) {
        this.cryptoHoldings = cryptoHoldings;
    }

    
}
