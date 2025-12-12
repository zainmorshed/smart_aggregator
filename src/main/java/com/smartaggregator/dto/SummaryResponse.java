package com.smartaggregator.dto;

import java.util.List;

import com.smartaggregator.entity.Crypto;
import com.smartaggregator.entity.NewsArticle;
import com.smartaggregator.entity.Stock;

public class SummaryResponse {
    private String username;
    private String timestamp;
    private List<Stock> stocks;
    private List<Crypto> cryptos;
    private List<NewsArticle> news;

    /*
     * dto is basically a container that holds the information you want to send back to the client
     * when someone hits the endpoint GET /summary?user=zain the backend will:
Gathers all the data (stocks, crypto, news)

Packs it into a SummaryResponse object

Sends that object as a JSON response
     */

    public SummaryResponse(String username, String timestamp, List<Stock> stocks, List<Crypto> cryptos, List<NewsArticle> news) {
        this.username = username;
        this.timestamp = timestamp;
        this.stocks = stocks;
        this.cryptos = cryptos;
        this.news = news;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Crypto> getCryptos() {
        return cryptos;
    }

    public void setCryptos(List<Crypto> cryptos) {
        this.cryptos = cryptos;
    }

    public List<NewsArticle> getNews() {
        return news;
    }

    public void setNews(List<NewsArticle> news) {
        this.news = news;
    }

}
