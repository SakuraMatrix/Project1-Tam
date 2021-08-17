package com.github.tamhpn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import reactor.netty.DisposableServer;

public class App {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class)) {
            appContext.getBean(DisposableServer.class)
                .onDispose()
                .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
