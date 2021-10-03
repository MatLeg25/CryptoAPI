package com.example.demo.dto;

import com.example.demo.model.Exchange;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Data
@Slf4j
public class CryptocurrencyExchangeResponse {

    private String from;

    @JsonProperty("to")
    private final List<Exchange> exchanges = new ArrayList<>();

    @JsonIgnore
    private ExecutorService executor = Executors.newFixedThreadPool(10);


    public CryptocurrencyExchangeResponse (CryptocurrencyExchangeRequest request, CryptocurrencyRates requestedRates) {
        this.setFrom(request.getFrom());
        this.addRatesMT(request, requestedRates);
        log.info(this.toString());
    }


    //BASIC SOLUTION
    public void addRatesB(CryptocurrencyExchangeRequest request, CryptocurrencyRates requestedRates) {
        List<String> currencies = new ArrayList<>(requestedRates.getRates().keySet());

        currencies.forEach(currency -> {
            this.exchanges.add(
                    new Exchange(request, requestedRates.getRates().get(currency), currency));
        });
    }


    //MULTI-THREAD SOLUTION
    public void addRatesMT(CryptocurrencyExchangeRequest request, CryptocurrencyRates requestedRates) {
        List<String> currencies = new ArrayList<>(requestedRates.getRates().keySet());

        List<Future<Exchange>> futures = new LinkedList<>();

        currencies.forEach(currency ->{
            futures.add(executor.submit(() ->
                    new Exchange(request, requestedRates.getRates().get(currency), currency)));
        });


        try {
            for(int i=0;i<futures.size();i++) {
                Exchange exchange = futures.get(i).get();
                this.exchanges.add(exchange);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
