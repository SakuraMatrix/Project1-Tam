package com.github.tamhpn;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class Server {
    private DisposableServer server;

    public Server(String host, int port) {
        this.server = HttpServer.create().host(host).port(port)
            .route(r -> r.get("/assets/{param}", (req, res) -> res.sendString(Mono.just("placeholder")))
                    .get("/positions", (req, res) -> res.sendString(Mono.just("placeholder")))
                    .post("/positions/{param}", (req, res) -> res.sendString(Mono.just("placeholder")))
                    .delete("/positions", (req, res) -> res.sendString(Mono.just("placeholder")))
                    .delete("/positions/{param}", (req, res) -> res.sendString(Mono.just("placeholder"))))
            .bindNow().onDispose().block();
    }
}
