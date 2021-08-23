package com.github.tamhpn.controller;

import com.github.tamhpn.domain.Stock;
import com.github.tamhpn.service.StockService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/holdings")
public class StockController {
    private static Logger logger = LoggerFactory.getLogger(StockController.class);
    
    private StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("")
    public Flux<Stock> getAll() {
        logger.info("Client made a GET request to /holdings");
        return stockService.getAll();
    }

    @GetMapping("/{symbol}")
    public Flux<Stock> get(@PathVariable String symbol) {
        logger.info("Client made a GET request to /holdings/" + symbol);
        return stockService.get(symbol);
    }

    @PostMapping("/{symbol}")
    public void buy(@PathVariable String symbol) {
        logger.info("Client made a POST request to /holdings/" + symbol);
        stockService.buy(symbol);
    }

    @DeleteMapping("")
    public void sellAll() {
        logger.info("Client made a DELETE request to /holdings");
        stockService.sellAll();
    }

    @DeleteMapping("/{symbol}")
    public void sell(@PathVariable String symbol) {
        logger.info("Client made a DELETE request to /holdings/" + symbol);
        stockService.sell(symbol);
    }
}
