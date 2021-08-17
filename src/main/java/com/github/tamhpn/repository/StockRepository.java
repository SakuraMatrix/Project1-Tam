package com.github.tamhpn.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tamhpn.domain.Stock;
import com.github.tamhpn.http.StockClient;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public class StockRepository {
    private CqlSession session;
    private ObjectMapper objectMapper;
    private StockClient client;

    public StockRepository(CqlSession session, StockClient client) {
        this.session = session;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.client = client;
    }

    public Flux<Stock> getAll() {
        return Flux.from(session.executeReactive("SELECT * FROM brokerage.holdings;"))
            .map(row -> new Stock(row.getString("symbol"), row.getString("name"), row.getDouble("price"), row.getLong("timestamp")));
    }

    public Flux<Stock> get(String symbol) {
        return Flux.from(session.executeReactive("SELECT * FROM brokerage.holdings WHERE symbol = '" + symbol + "';"))
            .map(row -> new Stock(row.getString("symbol"), row.getString("name"), row.getDouble("price"), row.getLong("timestamp")));
    }

    public void buy(String symbol) {
        client.getStock(symbol).subscribe(response -> {
            Stock stock = deserializeStock(response.substring(2, response.length() - 2));
            Flux.just("INSERT INTO brokerage.holdings (symbol, name, price, timestamp) VALUES ('" + stock.getSymbol() + "', '" + stock.getName() + "', " + stock.getPrice() + ", " + stock.getTimestamp() + ");")
                .flatMap(session::executeReactive)
                .subscribe();
        });
    }

    public void sellAll() {
        Flux.just("TRUNCATE brokerage.holdings;")
            .flatMap(session::executeReactive)
            .subscribe();
    }

    public void sell(String symbol) {
        Flux.just("DELETE FROM brokerage.holdings WHERE symbol = '" + symbol + "';")
            .flatMap(session::executeReactive)
            .subscribe();
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
