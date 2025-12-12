package com.smartaggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartaggregator.service.CryptoService;

import com.smartaggregator.dto.CryptoPriceDTO;

@RestController
@RequestMapping("/crypto")
public class CryptoController {
    
    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/price")
    public CryptoPriceDTO getPrice(@RequestParam String coin) {
        return cryptoService.getCryptoPrice(coin.toLowerCase());
    }
}
