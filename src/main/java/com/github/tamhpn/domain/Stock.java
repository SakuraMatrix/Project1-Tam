package com.github.tamhpn.domain;

public class Stock {
    private String symbol;
    private String name;
    private double price;
    private long timestamp; // in seconds (epoch)

    public Stock() {
        
    }

    public Stock(String symbol, String name, double price, long timestamp) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append("\"symbol\" : \"" + symbol + "\", ");
        sb.append("\"name\" : \"" + name + "\", ");
        sb.append("\"price\" : " + price + ", ");
        sb.append("\"timestamp\" : " + timestamp);
        sb.append(" }");
        return sb.toString();
    }
}
