package com.smartaggregator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // Primary key

    private String symbol;
    private String name;
    private double price;
    private double change24h;
    private String trend;

    public Asset() {}

    public Asset(String symbol, String name, double price, double change24h, String trend) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.change24h = change24h;
        this.trend = trend;
    }

    // String getSymbol();
    // String getName();
    // double getPrice();
    // double getChange24h();
    // String getTrend();


    public Long getId() { return id; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getChange24h() { return change24h; }
    public void setChange24h(double change24h) { this.change24h = change24h; }
    public String getTrend() { return trend; }
    public void setTrend(String trend) { this.trend = trend; }
}
