package com.github.tamhpn.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.truncate.Truncate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tamhpn.domain.Stock;
import com.github.tamhpn.http.StockClient;

import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.*;

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
        Select query = selectFrom("brokerage", "holdings").all();
        return Flux.from(session.executeReactive(query.build()))
            .map(row -> new Stock(row.getString("symbol"),
                row.getString("name"),
                row.getDouble("price"),
                row.getLong("timestamp")));
    }

    public Flux<Stock> get(String symbol) {
        Select query = selectFrom("brokerage", "holdings").all()
            .whereColumn("symbol").isEqualTo(literal(symbol));
        return Flux.from(session.executeReactive(query.build()))
            .map(row -> new Stock(row.getString("symbol"),
                row.getString("name"),
                row.getDouble("price"),
                row.getLong("timestamp")));
    }

    public void buy(String symbol) {
        client.getStock(symbol).subscribe(response -> {
            Stock stock = deserializeStock(response.substring(2, response.length() - 2));
            Insert query = insertInto("brokerage", "holdings")
                .value("symbol", literal(stock.getSymbol()))
                .value("name", literal(stock.getName()))
                .value("price", literal(stock.getPrice()))
                .value("timestamp", literal(stock.getTimestamp()));
            Flux.just(query.build())
                .flatMap(session::executeReactive)
                .subscribe();
        });
    }

    public void sellAll() {
        Truncate query = truncate("brokerage", "holdings");
        Flux.just(query.build())
            .flatMap(session::executeReactive)
            .subscribe();
    }

    public void sell(String symbol) {
        Delete query = deleteFrom("brokerage", "holdings")
            .whereColumn("symbol").isEqualTo(literal(symbol));
        Flux.just(query.build())
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
