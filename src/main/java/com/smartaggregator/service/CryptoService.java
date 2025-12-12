package com.smartaggregator.service;

import com.smartaggregator.dto.CryptoPriceDTO;
import com.smartaggregator.entity.Crypto;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class CryptoService {

    private final RestTemplate restTemplate = new RestTemplate();

    public CryptoPriceDTO getCryptoPrice(String coinId) {
        String url = UriComponentsBuilder
            .fromHttpUrl("https://api.coingecko.com/api/v3/simple/price")
            .queryParam("ids", coinId)
            .queryParam("vs_currencies", "usd")
            .toUriString();

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0"); // Prevent 403 error

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
            );

            String jsonString = response.getBody();
            JSONObject json = new JSONObject(jsonString);

            if (!json.has(coinId)) {
                throw new RuntimeException("Coin not found: " + coinId);
            }

            double price = json.getJSONObject(coinId).getDouble("usd");
            return new CryptoPriceDTO(coinId, price);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch crypto price: " + e.getMessage());
        }
    }

    public List<Crypto> getCryptoData(List<String> symbols) {
        List<Crypto> cryptoList = new ArrayList<>();

        for (String symbol : symbols) {
            try {
                CryptoPriceDTO dto = getCryptoPrice(symbol);
                double price = dto.getPrice();
                double change24h = 2.5; // placeholder
                String trend = change24h >= 0 ? "up" : "down";
                String name = "Crypto " + symbol.toUpperCase();

                Crypto crypto = new Crypto(symbol.toUpperCase(), name, price, change24h, trend);
                cryptoList.add(crypto);
            } catch (Exception e) {
                System.out.println("Failed to fetch crypto: " + symbol + " - " + e.getMessage());
            }
        }

        return cryptoList;
    }
}
