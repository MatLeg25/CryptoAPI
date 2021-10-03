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

    private LinkedHashMap<String, Double> currencyExchange;
    private static double FEE = 0.01;


    public Exchange(CryptocurrencyExchangeRequest request, Double currencyRate) {
        //System.out.println("THREAD= "+ Thread.currentThread().getName());
        System.out.println("START");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        LinkedHashMap<String, Double> rate = new LinkedHashMap<>();
        rate.put("rate", currencyRate);
        rate.put("amount", (double) request.getAmount());
        rate.put("result", (request.getAmount()*(1-FEE)) * currencyRate);
        rate.put("fee", FEE);
        this.currencyExchange = rate;
        System.out.println("KONIEC");
    }
}
