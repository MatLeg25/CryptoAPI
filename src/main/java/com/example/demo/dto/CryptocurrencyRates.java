package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.LinkedHashMap;

@Data
public class CryptocurrencyRates {

    @JsonProperty("source")
    public String source;

    @JsonProperty("rates")
    private LinkedHashMap<String, Double> rates;

}
