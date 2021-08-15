package com.github.tamhpn.service;

import com.github.tamhpn.domain.Stock;
import com.github.tamhpn.repository.StockRepository;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Flux<Stock> getAll() {
        return stockRepository.getAll();
    }

    public Flux<Stock> get(String symbol) {
        return stockRepository.get(symbol);
    }

    public void buy(String symbol) {
        stockRepository.buy(symbol);
    }

    public void sellAll() {
        stockRepository.sellAll();
    }

    public void sell(String symbol) {
        stockRepository.sell(symbol);
    }
}
