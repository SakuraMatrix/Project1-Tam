package com.github.tamhpn;

import com.github.tamhpn.repository.StockRepository;
import com.github.tamhpn.service.StockService;

public class App {
    public static void main(String[] args) {
        Server server = new Server("localhost", 8080);

        StockRepository stockRepository = new StockRepository();
        StockService stockService = new StockService(stockRepository);
    }
}
