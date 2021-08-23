package com.github.tamhpn.domain;

public class StockInfo {
    private String symbol;
    private String name;
    private double price;
    private double changesPercentage;
    private double change;
    private double dayLow;
    private double dayHigh;
    private double yearHigh;
    private double yearLow;
    private double marketCap;
    private double priceAvg50;
    private double priceAvg200;
    private long volume;
    private long avgVolume;
    private String exchange;
    private double open;
    private double previousClose;
    private double eps;
    private double pe;
    private String earningsAnnouncement;
    private long sharesOutstanding;
    private long timestamp;

    public StockInfo() {

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

    public double getChangesPercentage() {
        return changesPercentage;
    }

    public double getChange() {
        return change;
    }

    public double getDayLow() {
        return dayLow;
    }

    public double getDayHigh() {
        return dayHigh;
    }

    public double getYearHigh() {
        return yearHigh;
    }

    public double getYearLow() {
        return yearLow;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public double getPriceAvg50() {
        return priceAvg50;
    }

    public double getPriceAvg200() {
        return priceAvg200;
    }

    public long getVolume() {
        return volume;
    }

    public long getAvgVolume() {
        return avgVolume;
    }

    public String getExchange() {
        return exchange;
    }

    public double getOpen() {
        return open;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public double getEps() {
        return eps;
    }

    public double getPe() {
        return pe;
    }

    public String getEarningsAnnouncement() {
        return earningsAnnouncement;
    }

    public long getSharesOutstanding() {
        return sharesOutstanding;
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

    public void setChangesPercentage(double changesPercentage) {
        this.changesPercentage = changesPercentage;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setDayLow(double dayLow) {
        this.dayLow = dayLow;
    }

    public void setDayHigh(double dayHigh) {
        this.dayHigh = dayHigh;
    }

    public void setYearHigh(double yearHigh) {
        this.yearHigh = yearHigh;
    }

    public void setYearLow(double yearLow) {
        this.yearLow = yearLow;
    }

    public void setMarketCap(double marketCap) {
        this.marketCap = marketCap;
    }

    public void setPriceAvg50(double priceAvg50) {
        this.priceAvg50 = priceAvg50;
    }

    public void setPriceAvg200(double priceAvg200) {
        this.priceAvg200 = priceAvg200;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public void setAvgVolume(long avgVolume) {
        this.avgVolume = avgVolume;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public void setPe(double pe) {
        this.pe = pe;
    }

    public void setEarningsAnnouncement(String earningsAnnouncement) {
        this.earningsAnnouncement = earningsAnnouncement;
    }

    public void setSharesOutstanding(long sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
