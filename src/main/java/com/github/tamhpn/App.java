package com.github.tamhpn;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.tamhpn.http.Client;
import com.github.tamhpn.http.Server;
import com.github.tamhpn.repository.StockRepository;
import com.github.tamhpn.service.StockService;

import reactor.core.publisher.Flux;
import reactor.netty.http.server.HttpServer;

public class App {
    public static void main(String[] args) {
        CqlSession session = CqlSession.builder().build();

        // temporary database setup until I set up HttpClient and such
        Flux.just("CREATE KEYSPACE IF NOT EXISTS brokerage WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };",
            "CREATE TABLE IF NOT EXISTS brokerage.holdings (symbol text, price double, PRIMARY KEY (symbol, price));",
            "INSERT INTO brokerage.holdings (symbol, price) VALUES ('AMD', 110.55);",
            "INSERT INTO brokerage.holdings (symbol, price) VALUES ('GME', 162.52);",
            "INSERT INTO brokerage.holdings (symbol, price) VALUES ('GME', 419.99);")
            .flatMap(session::executeReactive)
            .subscribe();

        StockRepository stockRepository = new StockRepository(session);
        StockService stockService = new StockService(stockRepository);

        Client client = new Client();

        HttpServer server = new Server(client, stockService, "localhost", 8080).getServer();
        server.bindNow().onDispose().block();
    }
}
