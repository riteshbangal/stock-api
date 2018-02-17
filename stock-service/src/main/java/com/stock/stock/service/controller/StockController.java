package com.stock.stock.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class StockController {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackWelcome")
    @GetMapping("/welcome/{username}")
    public String welcome(@PathVariable("username") final String userName) {
        String url = "http://db-service/data/user/" + userName;
        return restTemplate.getForObject(url, String.class);
    }

    public String fallbackWelcome(final String userName, Throwable hystrixCommand) {
        return "Fall Back. Unable to fetch user name: "  + userName;
    }

    @HystrixCommand(fallbackMethod = "fallbackGetStock")
    @GetMapping("/{username}")
    public List<String> getStock(@PathVariable("username") final String userName) {

        String dbHostUrl = "http://db-service/data/" + userName;
        //String dbHostUrl = "http://localhost:7002/api/db-service/data/" + userName;

        ResponseEntity<List<String>> quoteResponse = restTemplate
                .exchange(dbHostUrl, HttpMethod.GET,null, new ParameterizedTypeReference<List<String>>() { });

        List<String> quotes = quoteResponse.getBody();
        return quotes
                .stream()
                .map(quote -> {
                    Stock stock = getStockPrice(quote);
                    if (null == stock) {
                        return "0.00 $";
                    }
                    return stock.getCurrency();
                }).collect(Collectors.toList());
    }

    public List<String> fallbackGetStock(final String userName, Throwable hystrixCommand) {
        return Arrays.asList("Fall Back. Unable to fetch user name: "  + userName);
    }

    private Stock getStockPrice(String quote) {
        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            //e.printStackTrace();
            return null;
        }
    }
}