package com.smartaggregator.dto;

public class StockPriceDTO {
    private String symbol;
    private double price;

    public StockPriceDTO(){

    }

    public StockPriceDTO(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
}
