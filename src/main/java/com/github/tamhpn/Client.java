package com.github.tamhpn;

import reactor.netty.http.client.HttpClient;

public class Client {
    private HttpClient client;
    String baseUrl = "";

    public Client() {

    }

    public String getRequest(String route) {
        return route;
    }
}
