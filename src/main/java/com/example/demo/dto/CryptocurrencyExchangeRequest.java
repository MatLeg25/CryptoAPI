package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CryptocurrencyExchangeRequest {

    private String from;
    private List<String> to;
    private int amount;

}
