package com.smartaggregator.service;

import com.smartaggregator.dto.CryptoPriceDTO;
import com.smartaggregator.entity.Crypto;

import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    @Value("${coinmarketcap.api.key}")
    private String apiKey;

    public CryptoPriceDTO getCryptoPrice(String symbol) {
        // CoinMarketCap uses uppercase symbols (BTC, ETH, not bitcoin, ethereum)
        String upperSymbol = symbol.toUpperCase();
        
        String url = UriComponentsBuilder
            .fromHttpUrl("https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest")
            .queryParam("symbol", upperSymbol)
            .queryParam("convert", "USD")
            .toUriString();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-CMC_PRO_API_KEY", apiKey); // CoinMarketCap requires this header
            headers.set("Accept", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
            );

            String jsonString = response.getBody();
            JSONObject json = new JSONObject(jsonString);
            
            // CoinMarketCap response structure:
            // {"data": {"BTC": {"quote": {"USD": {"price": 95000.0}}}}}
            JSONObject data = json.getJSONObject("data");
            JSONObject coinData = data.getJSONObject(upperSymbol);
            JSONObject quote = coinData.getJSONObject("quote");
            JSONObject usd = quote.getJSONObject("USD");
            double price = usd.getDouble("price");
            
            return new CryptoPriceDTO(upperSymbol, price);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch crypto price for " + symbol + ": " + e.getMessage());
        }
    }

    public List<Crypto> getCryptoData(List<String> symbols) {
        List<Crypto> cryptoList = new ArrayList<>();

        for (String symbol : symbols) {
            try {
                CryptoPriceDTO dto = getCryptoPrice(symbol);
                double price = dto.getPrice();
                double change24h = 0.0; // You can get this from CoinMarketCap response too
                String trend = "neutral";
                String name = "Crypto " + symbol.toUpperCase();

                Crypto crypto = new Crypto();
                crypto.setSymbol(symbol.toUpperCase());
                crypto.setName(name);
                crypto.setPrice(price);
                crypto.setChange24h(change24h);
                crypto.setTrend(trend);
                cryptoList.add(crypto);
            } catch (Exception e) {
                System.out.println("Failed to fetch crypto: " + symbol + " - " + e.getMessage());
            }
        }

        return cryptoList;
    }
}