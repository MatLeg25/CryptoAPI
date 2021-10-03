package com.example.demo.client;

import com.example.demo.dto.CryptocurrencyRates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@Component
@Slf4j
public class CryptocurrencyClient {

    @Value("${api.basicUrl}")
    private String apiBasicUrl;


    public CryptocurrencyRates getRatesByCurrencies(String currency, List<String> filter) {
        RestTemplate restTemplate = new RestTemplate();
        String apiURL = apiBasicUrl + "&target=" + currency;
        CryptocurrencyRates response = restTemplate.getForObject(apiURL, CryptocurrencyRates.class);
        LinkedHashMap<String, Double> ratesAll = response.getRates();

        response.setSource(currency);

        if (filter.size() > 0 && ratesAll!=null) {
            LinkedHashMap<String, Double> ratesFiltered = new LinkedHashMap<>();

            ratesAll.forEach(
                    (key, value) -> {
                        for (String f : filter) {
                            if (key.equals(f)) {
                                ratesFiltered.put(key, value);
                            }
                        }
                    });
            response.setRates(ratesFiltered);
        }

        return response;
    }

}
