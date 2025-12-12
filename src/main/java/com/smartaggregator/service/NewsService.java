package com.smartaggregator.service;
import com.smartaggregator.entity.NewsArticle;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    @Value("${gnews.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<NewsArticle> getTopFinancialNews() {
        String url = "https://gnews.io/api/v4/top-headlines?category=business&lang=en&token=" + apiKey;

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            JSONObject json = new JSONObject(response.getBody());
            JSONArray articles = json.getJSONArray("articles");

            List<NewsArticle> result = new ArrayList<>();

            for (int i = 0; i < Math.min(5, articles.length()); i++) {
                JSONObject item = articles.getJSONObject(i);
                String title = item.optString("title", "No title");
                String source = item.getJSONObject("source").optString("name", "Unknown");

                // Dummy sentiment analysis (placeholder)
                String sentiment = title.toLowerCase().contains("down") ? "negative" : "positive";

                result.add(new NewsArticle(title, sentiment, source));
            }

            return result;

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch news: " + e.getMessage());
        }
    }
}
