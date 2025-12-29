package com.smartaggregator.service;

import org.springframework.stereotype.Service;
import com.smartaggregator.repository.PortfolioRepository;

import jakarta.transaction.Transactional;

import com.smartaggregator.client.FinanceApiClient;
import com.smartaggregator.entity.Portfolio;
import com.smartaggregator.entity.StockHolding;
import com.smartaggregator.entity.CryptoHolding;
import com.smartaggregator.dto.AddStockRequest;
import java.math.BigDecimal;



@Service 
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final FinanceApiClient financeApiClient;

    public PortfolioService(
        PortfolioRepository portfolioRepository,
        FinanceApiClient financeApiClient
    ) {
        this.portfolioRepository = portfolioRepository;
        this.financeApiClient = financeApiClient;
    }

    public Portfolio getOrCreatePortfolio(String userId) {
        return portfolioRepository
                .findByUserId(userId)
                .orElseGet(() -> {
                    Portfolio portfolio = new Portfolio();
                    portfolio.setUserId(userId);
                    return portfolioRepository.save(portfolio);
                });
    }

    @Transactional
    public void addStock(String userId, AddStockRequest request) {
        Portfolio portfolio = getOrCreatePortfolio(userId);

        StockHolding holding = new StockHolding();
        holding.setTicker(request.getTicker());
        holding.setQuantity(request.getQuantity());
        holding.setPortfolio(portfolio);

        portfolio.getStockHoldings().add(holding);
        portfolioRepository.save(portfolio);
    }

    public BigDecimal calculateNetWorth(String userId) {
        Portfolio portfolio = getOrCreatePortfolio(userId);

        BigDecimal total = BigDecimal.ZERO;

        for (StockHolding stock : portfolio.getStockHoldings()) {
            BigDecimal price = financeApiClient.getStockPrice(stock.getTicker());
            total = total.add(price.multiply(BigDecimal.valueOf(stock.getQuantity())));
        }

        for (CryptoHolding crypto : portfolio.getCryptoHoldings()) {
            BigDecimal price = financeApiClient.getCryptoPrice(crypto.getSymbol());
            total = total.add(price.multiply(crypto.getAmount()));
        }

        return total;
    }
}
