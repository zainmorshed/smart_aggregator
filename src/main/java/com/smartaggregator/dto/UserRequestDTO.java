package com.smartaggregator.dto;
import java.util.List;

public class UserRequestDTO {
    private String username;
    private List<String> stocks;
    private List<String> cryptos;

    public UserRequestDTO(){}

    public UserRequestDTO(String username, List<String> stocks, List<String> cryptos) {
        this.username = username;
        this.stocks = stocks;
        this.cryptos = cryptos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getStocks() {
        return stocks;
    }

    public void setStocks(List<String> stocks) {
        this.stocks = stocks;
    }

    public List<String> getCryptos() {
        return cryptos;
    }

    public void setCryptos(List<String> cryptos) {
        this.cryptos = cryptos;
    }

    
}
