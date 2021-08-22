package com.github.tamhpn.controller;

import com.github.tamhpn.service.StockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/search")
public class StockLookupController {
    private static Logger logger = LoggerFactory.getLogger(StockLookupController.class);

    private StockService stockService;

    public StockLookupController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{symbol}")
    public Mono<String> searchStock(@PathVariable String symbol) {
        logger.info("Client made a GET request to /search/" + symbol);
        return stockService.searchStock(symbol);
    }
}
