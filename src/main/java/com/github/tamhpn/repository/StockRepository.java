package com.github.tamhpn.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.tamhpn.domain.Stock;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public class StockRepository {
    private CqlSession session;

    public StockRepository(CqlSession session) {
        this.session = session;
    }

    public Flux<Stock> getAll() {
        return Flux.from(session.executeReactive("SELECT * FROM brokerage.holdings;"))
            .map(row -> new Stock(row.getString("symbol"), row.getDouble("price")));
    }

    public Flux<Stock> get(String symbol) {
        return Flux.from(session.executeReactive("SELECT * FROM brokerage.holdings WHERE symbol = '" + symbol + "';"))
            .map(row -> new Stock(row.getString("symbol"), row.getDouble("price")));
    }

    public void buy(String symbol) {
        Flux.just("INSERT INTO brokerage.holdings (symbol, price) VALUES ('" + symbol + "', 123.45);")
            .flatMap(session::executeReactive)
            .subscribe();
    }

    public void sellAll() {
        Flux.just("TRUNCATE brokerage.holdings;").flatMap(session::executeReactive).subscribe(); // deletes all rows
    }

    public void sell(String symbol) {
        Flux.just("DELETE FROM brokerage.holdings WHERE symbol = '" + symbol + "';").flatMap(session::executeReactive).subscribe(); // deletes all rows with matching symbol, add some way to specify quantity
    }
}
