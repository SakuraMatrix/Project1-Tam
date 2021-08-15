package com.github.tamhpn.http;

import reactor.netty.http.client.HttpClient;

public class StockClient {
    private HttpClient client;
    String baseUrl = "";

    public StockClient() {

    }

    public String getRequest(String route) {
        return route;
    }
}
