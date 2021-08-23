package com.github.tamhpn;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.tamhpn.http.StockClient;
import com.github.tamhpn.service.StockService;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import reactor.netty.http.server.HttpServer;

@Configuration
@ComponentScan
public class AppConfig {
    Dotenv dotenv = Dotenv.load();
    @Autowired
    StockService stockService;

    @Bean
    public CqlSession cqlSession() {
        return CqlSession.builder().build();
    }

    @Bean
    public StockClient stockClient() {
        return new StockClient(dotenv.get("API_KEY"));
    }

    @Bean
    public HttpServer server(ApplicationContext context) {
        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        return HttpServer.create().port(8080).handle(adapter);
    }
}
