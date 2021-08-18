package com.github.tamhpn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tamhpn.domain.Stock;
import com.github.tamhpn.http.StockClient;
import com.github.tamhpn.repository.StockRepository;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StockService {
    private ObjectMapper objectMapper;
    private StockClient stockClient;
    private StockRepository stockRepository;

    public StockService(StockClient stockClient, StockRepository stockRepository) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.stockClient = stockClient;
        this.stockRepository = stockRepository;
    }

    public Mono<String> searchStock(String symbol) {
        return stockClient.getStock(symbol);
    }

    public Flux<Stock> getAll() {
        return stockRepository.getAll();
    }

    public Flux<Stock> get(String symbol) {
        return stockRepository.get(symbol);
    }

    public void buy(String symbol) {
        stockClient.getStock(symbol).subscribe(response -> {
            Stock stock = deserializeStock(response.substring(2, response.length() - 2));
            stockRepository.buy(stock);
        });
    }

    public void sellAll() {
        stockRepository.sellAll();
    }

    public void sell(String symbol) {
        stockRepository.sell(symbol);
    }

    private Stock deserializeStock(String stockJsonString) {
        Stock stock;
        try {
            stock = objectMapper.readValue(stockJsonString, Stock.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            stock = null;
        }
        return stock;
    }
}
