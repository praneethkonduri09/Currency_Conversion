package org.example.service;

import org.example.entity.entity;
import org.example.repo.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CurrencyService {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    @Autowired
    private repository exchangeRateRepository;

    private final RestTemplate restTemplate = new RestTemplate();

//    public Map<String, Object> getExchangeRates(String base) {
//        try {
//            // Check database for cached rates
//            Optional<entity> cachedRate = exchangeRateRepository.findByBaseCurrency(base);
//            if (cachedRate.isPresent()) {
//                return Map.of("base", base, "rates", cachedRate.get().getRates());
//            }
//
//            // Fetch from external API if not cached
//            String url = API_URL + base;
//            ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);
//
//            if (response != null && response.getRates() != null) {
//                // Save to database if rates are retrieved
//                entity exchangeRate = new entity(base, response.getRates());
//                exchangeRateRepository.save(exchangeRate);
//                return Map.of("base", base, "rates", response.getRates());
//            }
//        } catch (RestClientException e) {
//            // Handle API-related exceptions
//            System.err.println("Error fetching rates from API: " + e.getMessage());
//        } catch (Exception e) {
//            // Handle unexpected exceptions
//            System.err.println("Unexpected error in getExchangeRates: " + e.getMessage());
//        }
//
//        // Fallback for API or database failure
//        return Map.of(
//                "error", "Unable to fetch exchange rates at this time.",
//                "base", base,
//                "rates", new HashMap<>()
//        );
//    }
public Map<String, Object> getExchangeRates(String base) {
    try {
        // Check database for cached rates
        Optional<entity> cachedRate = exchangeRateRepository.findByBaseCurrency(base);
        if (cachedRate.isPresent()) {
            return Map.of("base", base, "rates", cachedRate.get().getRates());
        }

        // Fetch from external API if not cached
        String url = API_URL + base;
        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        if (response != null && response.getRates() != null) {
            // Save to database if rates are retrieved
            entity exchangeRate = new entity(base, response.getRates());
            exchangeRateRepository.save(exchangeRate); // Ensure this is working properly
            return Map.of("base", base, "rates", response.getRates());
        }
    } catch (RestClientException e) {
        System.err.println("Error fetching rates from API: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Unexpected error in getExchangeRates: " + e.getMessage());
    }

    // Fallback if external API or database operations fail
    return Map.of(
            "error", "Unable to fetch exchange rates at this time.",
            "base", base,
            "rates", new HashMap<>()
    );
}


    public Map<String, Object> convertCurrency(Map<String, Object> request) {
        String from = (String) request.get("from");
        String to = (String) request.get("to");
        Double amount;

        // Validate input
        try {
            amount = Double.valueOf(request.get("amount").toString());
        } catch (NumberFormatException e) {
            return Map.of(
                    "error", "Invalid amount. Please provide a valid numeric value.",
                    "from", from,
                    "to", to
            );
        }

        // Query the database for the base currency
        Optional<entity> fromRateOptional = exchangeRateRepository.findByBaseCurrency(from);

        if (fromRateOptional.isPresent()) {
            entity fromRate = fromRateOptional.get();

            // Check if the target currency exists in the rates
            if (fromRate.getRates().containsKey(to)) {
                double rate = fromRate.getRates().get(to);
                double convertedAmount = amount * rate;

                return Map.of(
                        "from", from,
                        "to", to,
                        "amount", amount,
                        "convertedAmount", convertedAmount
                );
            } else {
                return Map.of(
                        "error", "Conversion rate from " + from + " to " + to + " not available.",
                        "from", from,
                        "to", to,
                        "amount", amount
                );
            }
        }

        // Fallback for missing data
        return Map.of(
                "error", "Base currency " + from + " not found in the database.",
                "from", from,
                "to", to,
                "amount", amount,
                "convertedAmount", 0.0
        );
    }
}
