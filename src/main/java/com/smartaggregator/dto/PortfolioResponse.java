package com.smartaggregator.dto;

import java.util.List;
import java.math.BigDecimal;


public class PortfolioResponse {
    public String userId;
    public List<StockHoldingResponse> stocks;
    public List<CryptoHoldingResponse> crypto;
    public BigDecimal totalNetWorth;


    public PortfolioResponse(){}
    
    public PortfolioResponse(String userId, List<StockHoldingResponse> stocks, List<CryptoHoldingResponse> crypto,
            BigDecimal totalNetWorth) {
        this.userId = userId;
        this.stocks = stocks;
        this.crypto = crypto;
        this.totalNetWorth = totalNetWorth;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<StockHoldingResponse> getStocks() {
        return stocks;
    }
    public void setStocks(List<StockHoldingResponse> stocks) {
        this.stocks = stocks;
    }
    public List<CryptoHoldingResponse> getCrypto() {
        return crypto;
    }
    public void setCrypto(List<CryptoHoldingResponse> crypto) {
        this.crypto = crypto;
    }
    public BigDecimal getTotalNetWorth() {
        return totalNetWorth;
    }
    public void setTotalNetWorth(BigDecimal totalNetWorth) {
        this.totalNetWorth = totalNetWorth;
    }
}
