package com.github.tamhpn.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.Insert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.truncate.Truncate;
import com.github.tamhpn.domain.Stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.*;

@Repository
public class StockRepository {
    private static Logger logger = LoggerFactory.getLogger(StockRepository.class);

    private CqlSession session;

    public StockRepository(CqlSession session) {
        this.session = session;
    }

    public Flux<Stock> getAll() {
        logger.info("Selecting all stock from brokerage.holdings");
        Select query = selectFrom("brokerage", "holdings").all();
        return Flux.from(session.executeReactive(query.build()))
            .map(row -> new Stock(row.getString("symbol"),
                row.getString("name"),
                row.getDouble("price"),
                row.getLong("timestamp")));
    }

    public Flux<Stock> get(String symbol) {
        logger.info("Selecting all " + symbol + " stock from brokerage.holdings");
        Select query = selectFrom("brokerage", "holdings").all()
            .whereColumn("symbol").isEqualTo(literal(symbol));
        return Flux.from(session.executeReactive(query.build()))
            .map(row -> new Stock(row.getString("symbol"),
                row.getString("name"),
                row.getDouble("price"),
                row.getLong("timestamp")));
    }

    public void buy(Stock stock) {
        logger.info("Buying " + stock);
        Insert query = insertInto("brokerage", "holdings")
            .value("symbol", literal(stock.getSymbol()))
            .value("name", literal(stock.getName()))
            .value("price", literal(stock.getPrice()))
            .value("timestamp", literal(stock.getTimestamp()));
        Flux.just(query.build())
            .flatMap(session::executeReactive)
            .subscribe();
    }

    public void sellAll() {
        logger.info("Selling all stock from brokerage.holdings");
        Truncate query = truncate("brokerage", "holdings");
        Flux.just(query.build())
            .flatMap(session::executeReactive)
            .subscribe();
    }

    public void sell(String symbol) {
        logger.info("Selling all " + symbol + " stock from brokerage.holdings");
        Delete query = deleteFrom("brokerage", "holdings")
            .whereColumn("symbol").isEqualTo(literal(symbol));
        Flux.just(query.build())
            .flatMap(session::executeReactive)
            .subscribe();
    }
}
