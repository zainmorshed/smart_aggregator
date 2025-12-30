package com.smartaggregator.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class FinanceApiClient {

    private final RestTemplate restTemplate;
    private final String financeApiUrl;

    public FinanceApiClient(RestTemplate restTemplate,
                            @Value("${api.finance.url}") String financeApiUrl) {
        this.restTemplate = restTemplate;
        this.financeApiUrl = financeApiUrl;
    }

    public BigDecimal getStockPrice(String ticker) {
        String url = financeApiUrl + "/stocks/price?ticker=" + ticker;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return new BigDecimal(response.get("price").toString());
    }

    public BigDecimal getCryptoPrice(String symbol) {
        String url = financeApiUrl + "/crypto/price?symbol=" + symbol;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        return new BigDecimal(response.get("price").toString());
    }
}
