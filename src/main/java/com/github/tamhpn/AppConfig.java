package com.github.tamhpn;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.tamhpn.http.StockClient;
import com.github.tamhpn.http.StockServer;
import com.github.tamhpn.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import reactor.netty.DisposableServer;

@Configuration
@ComponentScan
public class AppConfig {
    @Autowired
    StockService stockService;

    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder().build();
    }

    // @Bean
    // public StockRepository repository() {
    //     return new StockRepository(session());
    // }

    // @Bean
    // public StockService service() {
    //     return new StockService(repository());
    // }

    @Bean
    public DisposableServer disposableServer() {
        return new StockServer(stockClient(), stockService, "localhost", 8080).getServer();
    }

    @Bean
    public StockClient stockClient() {
        return new StockClient();
    }
}
