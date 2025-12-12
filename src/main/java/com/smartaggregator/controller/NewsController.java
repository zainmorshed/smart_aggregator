package com.smartaggregator.controller;

import com.smartaggregator.dto.NewsArticleDTO;
import com.smartaggregator.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    
@GetMapping
public List<NewsArticleDTO> getNews() {
    return newsService.getTopFinancialNews()
        .stream()
        .map(article -> new NewsArticleDTO(
            article.getHeadline(),
            article.getSentiment(),
            article.getSource()
        ))
        .toList();
}

}
