package com.github.tamhpn.domain;

public class Stock {
    private String symbol;
    // private String name;
    private double price;
    // private LocalDateTime timestamp;

    public Stock() {
        
    }

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public double getPrice() {
        return this.price;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
