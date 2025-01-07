package org.example.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class ExchangeRateResponse {

    private String base;

    @JsonProperty("rates")
    private Map<String, Double> rates;

    // Getters and Setters
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
