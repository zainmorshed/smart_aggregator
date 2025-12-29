package com.smartaggregator.dto;

import java.math.BigDecimal;

public class AddCryptoRequest {
    public String symbol;
    public BigDecimal amount;

    public AddCryptoRequest(){}

    public AddCryptoRequest(String symbol, BigDecimal amount) {
        this.symbol = symbol;
        this.amount = amount;
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
}
