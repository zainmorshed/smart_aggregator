package com.smartaggregator.dto;

public class CryptoPriceDTO {
    

    private String coinId;
    private double price;

    public CryptoPriceDTO() {

    }
    public CryptoPriceDTO(String coinId, double price) {
        this.coinId = coinId;
        this.price = price;
    }
    public String getCoinId() {
        return coinId;
    }
    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    
}
