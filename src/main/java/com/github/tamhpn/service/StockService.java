package com.github.tamhpn.service;

import com.github.tamhpn.repository.StockRepository;

public class StockService {
    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAll() {
        return stockRepository.getAll();
    }

    public Stock get(String symbol) {
        return stockRepository.get(symbol);
    }
}
