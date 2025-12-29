package com.smartaggregator.dto;

public class AddStockRequest {
    public String ticker;
    public double quantity;


    public AddStockRequest(){}
    
    public AddStockRequest(String ticker, double quantity) {
        this.ticker = ticker;
        this.quantity = quantity;
    }
    public String getTicker() {
        return ticker;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
