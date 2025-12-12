package com.smartaggregator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NewsArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // primary key

    private String headline;
    private String sentiment;
    private String source;

    public NewsArticle() {
    }

    public NewsArticle(String headline, String sentiment, String source) {
        this.headline = headline;
        this.sentiment = sentiment;
        this.source = source;
  
    }

    public Long getId() { return id; }

    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    public String getSentiment() {
        return sentiment;
    }
    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
}
