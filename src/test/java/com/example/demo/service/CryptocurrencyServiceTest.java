package com.example.demo.service;

import com.example.demo.client.CryptocurrencyClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.verify;

class CryptocurrencyServiceTest {

    @Mock
    private CryptocurrencyClient cryptocurrencyClient;
    private AutoCloseable autoCloseable;
    private CryptocurrencyService testService;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        testService = new CryptocurrencyService(cryptocurrencyClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllRates() {
        testService.getRatesByCurrencies("USD", new ArrayList<>());
        verify(cryptocurrencyClient).getRatesByCurrencies("USD",new ArrayList<>());
    }

    @Test
    void canGetFilteredRates() {
        testService.getRatesByCurrencies("USD", new ArrayList<>(Arrays.asList("ETH", "BTC")));
        verify(cryptocurrencyClient).getRatesByCurrencies("USD", new ArrayList<>(Arrays.asList("ETH", "BTC")));
    }

}