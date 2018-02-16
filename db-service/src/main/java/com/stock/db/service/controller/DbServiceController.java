package com.stock.db.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/db")
public class DbServiceController {

    @GetMapping("/{username}")
    public String welcome(@PathVariable("username") final String username) {
        return "Hi! " + username + ". Welcome to Stock Portal.";
    }
}