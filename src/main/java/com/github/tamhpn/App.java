package com.github.tamhpn;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import reactor.core.publisher.Flux;
import reactor.netty.DisposableServer;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
            CqlSession session = appContext.getBean(CqlSession.class);

            // temporary database setup until I set up HttpClient and such
            Flux.just("CREATE KEYSPACE IF NOT EXISTS brokerage WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };",
                "CREATE TABLE IF NOT EXISTS brokerage.holdings (symbol text, price double, PRIMARY KEY (symbol, price));",
                "INSERT INTO brokerage.holdings (symbol, price) VALUES ('AMD', 110.55);",
                "INSERT INTO brokerage.holdings (symbol, price) VALUES ('GME', 162.52);",
                "INSERT INTO brokerage.holdings (symbol, price) VALUES ('GME', 419.99);")
                .flatMap(session::executeReactive)
                .subscribe();

            appContext.getBean(DisposableServer.class)
                .onDispose()
                .block();
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
