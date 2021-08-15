package com.github.tamhpn.http;

import com.github.tamhpn.service.StockService;
import com.github.tamhpn.util.ByteBufs;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

public class Server {
    private Client client;
    private HttpServer server;
    private StockService stockService;

    public Server(Client client, StockService stockService, String host, int port) {
        this.client = client;
        this.stockService = stockService;
        this.server = HttpServer.create().host(host).port(port)
            .route(r -> r.get("/assets/{param}", (req, res) -> res.sendString(Mono.just("placeholder").log("http-server")))
                .get("/positions", (req, res) -> 
                    res.send(stockService.getAll().map(ByteBufs::toByteBuf).log("http-server")))
                .get("/positions/{param}", (req, res) ->
                    res.send(stockService.get(req.param("param")).map(ByteBufs::toByteBuf).log("http-server"))) 
                .post("/positions/{param}", (req, res) -> {
                    stockService.buy(req.param("param"));
                    return res.sendString(Mono.just("bought " + req.param("param")).log("http-server"));
                })
                .delete("/positions", (req, res) -> {
                    stockService.sellAll();
                    return res.sendString(Mono.just("sold all").log("http-server"));
                })
                .delete("/positions/{param}", (req, res) -> {
                    stockService.sell(req.param("param"));
                    return res.sendString(Mono.just("sold " + req.param("param")).log("http-server"));
                }));
    }

    public HttpServer getServer() {
        return this.server;
    }
}
