package com.example.demo.model;

import com.example.demo.dto.CryptocurrencyExchangeRequest;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Data
public class Exchange {


    //TODO in JSON map currencyExchange as currency name
    @JsonProperty("currency")
    private String currencyName;
    @JsonProperty("details")
    private LinkedHashMap<String, Double> currencyExchange;
    private static double FEE = 0.01;


    //TODO get proper currency Rate from client API
    public Exchange(CryptocurrencyExchangeRequest request, Double currencyRate, String currencyName) {
        Double currencyRateEx = (1/currencyRate);
        LinkedHashMap<String, Double> rate = new LinkedHashMap<>();
        rate.put("rate", currencyRateEx);
        rate.put("amount", (double) request.getAmount());
        rate.put("result", ((request.getAmount() * (1-FEE)) * currencyRateEx));
        rate.put("fee", (request.getAmount() * FEE * currencyRateEx));
        this.currencyExchange = rate;
        this.currencyName = currencyName;
    }

}
