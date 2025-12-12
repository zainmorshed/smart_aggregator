package com.smartaggregator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartaggregator.dto.SummaryResponse;
import com.smartaggregator.entity.Stock;
import com.smartaggregator.service.StockService;
import com.smartaggregator.service.CryptoService;
import com.smartaggregator.service.NewsService;

import com.smartaggregator.entity.Crypto;
import com.smartaggregator.entity.NewsArticle;
import java.time.Instant;

@Service
public class AggregatorService {
    @Autowired
    private UserService userService;
    @Autowired
    private StockService stockService;
    @Autowired
    private CryptoService cryptoService;
    @Autowired
    private NewsService newsService;

    public SummaryResponse getSummaryForUser(String username){
        List<String> stocks = userService.getTrackedStocks(username);
        List<String> cryptos = userService.getTrackedCryptos(username);

        List<Stock> stockData = stockService.getStockData(stocks);
        List<Crypto> cryptoData = cryptoService.getCryptoData(cryptos);
        List<NewsArticle> news =  newsService.getTopFinancialNews();

        return new SummaryResponse(username, Instant.now().toString(), stockData, cryptoData, news);
    }

    
}

