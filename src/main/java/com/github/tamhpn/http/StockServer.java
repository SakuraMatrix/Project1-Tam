package com.github.tamhpn.http;

import com.github.tamhpn.service.StockService;
import com.github.tamhpn.util.ByteBufs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

public class StockServer {
    private static Logger logger = LoggerFactory.getLogger(StockServer.class);

    private StockService stockService;
    private String host;
    private int port;

    public StockServer(StockService stockService, String host, int port) {
        this.stockService = stockService;
        this.host = host;
        this.port = port;
    }

    public DisposableServer getServer() {
        return HttpServer.create().host(host).port(port)
            .route(r -> r.get("/assets/{param}", (req, res) -> {
                    logger.info("Client made a GET request to /assets/" + req.param("param"));
                    return res.sendString(stockService.searchStock(req.param("param"))
                        .log("http-server"));
                })
                .get("/positions", (req, res) -> {
                    logger.info("Client made a GET request to /positions");
                    return res.send(stockService.getAll()
                        .map(ByteBufs::toByteBuf)
                        .log("http-server"));
                })
                .get("/positions/{param}", (req, res) -> {
                    logger.info("Client made a GET request to /positions/" + req.param("param"));
                    return res.send(stockService.get(req.param("param"))
                        .map(ByteBufs::toByteBuf)
                        .log("http-server"));
                }) 
                .post("/positions/{param}", (req, res) -> {
                    logger.info("Client made a POST request to /positions/" + req.param("param"));
                    stockService.buy(req.param("param"));
                    return res.sendString(Mono.just("Bought " + req.param("param"))
                        .log("http-server"));
                })
                .delete("/positions", (req, res) -> {
                    logger.info("Client made a DELETE request to /positions");
                    stockService.sellAll();
                    return res.sendString(Mono.just("Sold all shares")
                        .log("http-server"));
                })
                .delete("/positions/{param}", (req, res) -> {
                    logger.info("Client made a DELETE request to /positions/" + req.param("param"));
                    stockService.sell(req.param("param"));
                    return res.sendString(Mono.just("Sold " + req.param("param"))
                        .log("http-server"));
                }))
            .bindNow();
    }
}
