package com.github.tamhpn.http;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

public class StockClient {
    private HttpClient client;
    private String baseUrl = "https://financialmodelingprep.com/api/v3/quote/"; // {symbol}?apikey={YOUR_API_KEY}
    private final String API_KEY;

    public StockClient(String API_KEY) {
        this.API_KEY = API_KEY;
        this.client = HttpClient.create();
    }

    public Mono<String> getStock(String symbol) {
        return client.get().uri(baseUrl + symbol + "?apikey=" + API_KEY).responseContent().aggregate().asString();
    }
}
