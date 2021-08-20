package com.github.tamhpn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import reactor.netty.DisposableServer;

public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Starting application");
        try (AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
            logger.info("Starting server");
            appContext.getBean(DisposableServer.class)
                .onDispose()
                .block();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
