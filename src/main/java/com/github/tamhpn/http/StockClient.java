package com.github.tamhpn.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class StockClient {
    private static Logger logger = LoggerFactory.getLogger(StockClient.class);

    private HttpClient client;
    private String baseUrl = "https://financialmodelingprep.com/api/v3/quote/";
    private final String API_KEY;

    public StockClient(String API_KEY) {
        this.API_KEY = API_KEY;
        this.client = HttpClient.create();
    }

    public Mono<String> getStock(String symbol) {
        logger.info("Requesting stock information for " + symbol);
        return client.get().uri(baseUrl + symbol + "?apikey=" + API_KEY).responseContent().aggregate().asString();
    }
}
