package com.stock.db.service.controller;

import com.stock.db.service.dto.QuoteDTO;
import com.stock.db.service.repository.QuoteRepository;
import com.stock.db.service.repository.entities.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service/db")
public class DbServiceController {

    private QuoteRepository quoteRepository;

    @Autowired
    public DbServiceController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping
    public String welcome() {
        return "Hi! Welcome to Stock Portal.";
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username) {
        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final QuoteDTO quoteDTO) {
        quoteDTO.getQuotes()
                .stream()
                .map(quote -> new Quote(quoteDTO.getUserName(), quote))
                .forEach(quote -> quoteRepository.save(quote));
        return getQuotesByUserName(quoteDTO.getUserName());
    }

    @PostMapping("/delete/{username}")
    public List<String> delete(@PathVariable("username") final String username) {
        List<Quote> quotes = quoteRepository.findByUserName(username);
        quoteRepository.delete(quotes);
        return getQuotesByUserName(username);
    }

    private List<String> getQuotesByUserName(@PathVariable("username") String username) {

        if (StringUtils.isEmpty(username)) {
            return quoteRepository.findAll()
                    .stream()
                    .map(Quote::getQuote)
                    .collect(Collectors.toList());
        }

        return quoteRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }
}