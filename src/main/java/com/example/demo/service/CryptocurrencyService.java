package com.example.demo.service;

import com.example.demo.dto.CryptocurrencyExchangeResponse;
import com.example.demo.dto.CryptocurrencyRates;
import com.example.demo.client.CryptocurrencyClient;
import com.example.demo.dto.CryptocurrencyExchangeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CryptocurrencyService {

    private final CryptocurrencyClient cryptocurrencyClient;

    @Autowired
    public CryptocurrencyService(CryptocurrencyClient cryptocurrencyClient) {
        this.cryptocurrencyClient = cryptocurrencyClient;
    }


    public CryptocurrencyRates getRatesByCurrencies(String currency, List<String> filter) {
        return cryptocurrencyClient.getRatesByCurrencies(currency, filter);
    }


    public CryptocurrencyExchangeResponse getExchanges(CryptocurrencyExchangeRequest request) {
        log.info(request.toString());
        String source = request.getFrom();
        List<String> currencies = request.getTo();
        CryptocurrencyRates requestedRates = cryptocurrencyClient.getRatesByCurrencies(source, currencies);
        CryptocurrencyExchangeResponse response = new CryptocurrencyExchangeResponse(request,requestedRates);
        return new CryptocurrencyExchangeResponse(request,requestedRates);
    }
}
