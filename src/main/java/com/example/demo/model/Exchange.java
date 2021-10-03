package com.example.demo.model;

import com.example.demo.dto.CryptocurrencyExchangeRequest;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Data
//@JsonRootName(value = "OO")
public class Exchange {

    //TODO in JSON map as currency name
    private LinkedHashMap<String, Double> currencyExchange;
    private static double FEE = 0.01;


    public Exchange(CryptocurrencyExchangeRequest request, Double currencyRate) {
        LinkedHashMap<String, Double> rate = new LinkedHashMap<>();
        rate.put("rate", currencyRate);
        rate.put("amount", (double) request.getAmount());
        rate.put("result", (request.getAmount()*(1-FEE)) * currencyRate);
        rate.put("fee", FEE);
        this.currencyExchange = rate;
    }
}
