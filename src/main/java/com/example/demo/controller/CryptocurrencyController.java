package com.example.demo.controller;

import com.example.demo.dto.CryptocurrencyExchangeResponse;
import com.example.demo.dto.CryptocurrencyRates;
import com.example.demo.dto.CryptocurrencyExchangeRequest;
import com.example.demo.service.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;

    @Autowired
    public CryptocurrencyController(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }


    @GetMapping("/currencies/{currency}")
    public CryptocurrencyRates getRatesByCurrencies(
            @PathVariable String currency,
            @RequestParam(value = "filter", required = false, defaultValue = "") List<String> filter) {
        return cryptocurrencyService.getRatesByCurrencies(currency, filter);
    }


    @PostMapping("/currencies/exchange")
    public CryptocurrencyExchangeResponse getRatesByCurrencies(
            @RequestBody CryptocurrencyExchangeRequest cryptocurrencyExchangeRequest) {
        return cryptocurrencyService.getExchanges(cryptocurrencyExchangeRequest);
    }

}
