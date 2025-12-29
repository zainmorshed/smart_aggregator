package com.smartaggregator.dto;

import java.math.BigDecimal;

public class CryptoHoldingResponse {
    public String symbol;
    public BigDecimal amount;
    public BigDecimal marketValue;


    public CryptoHoldingResponse(){}
    
    public CryptoHoldingResponse(String symbol, BigDecimal amount, BigDecimal marketValue) {
        this.symbol = symbol;
        this.amount = amount;
        this.marketValue = marketValue;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getMarketValue() {
        return marketValue;
    }
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }
}
