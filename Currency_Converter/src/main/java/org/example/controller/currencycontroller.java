package org.example.controller;
import org.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api")

public class currencycontroller {

        @Autowired
        private CurrencyService currencyService;

        @GetMapping("/rates")
        public Map<String, Object> getRates(@RequestParam(defaultValue = "USD") String base) {
            return currencyService.getExchangeRates(base);
        }

        @PostMapping("/convert")
        public Map<String, Object> convertCurrency(@RequestBody Map<String, Object> request) {
            return currencyService.convertCurrency(request);
        }
    }


