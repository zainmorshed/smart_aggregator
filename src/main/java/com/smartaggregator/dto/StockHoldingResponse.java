package com.smartaggregator.dto;

import java.math.BigDecimal;

public class StockHoldingResponse {
    public String ticker;
    public int shares;
    public BigDecimal marketValue;

    public StockHoldingResponse(){}
    

    public StockHoldingResponse(String ticker, int shares, BigDecimal marketValue) {
        this.ticker = ticker;
        this.shares = shares;
        this.marketValue = marketValue;
    }
    public String getTicker() {
        return ticker;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    public int getShares() {
        return shares;
    }
    public void setShares(int shares) {
        this.shares = shares;
    }
    public BigDecimal getMarketValue() {
        return marketValue;
    }
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }
}


