package com.smallchange.entities;

import java.util.Objects;

public class PortfolioEntity {
    private String ticker;
    private int quantity;
    private double avg_buy_price;

    public double getCurrent_market_price() {
        return current_market_price;
    }

    public void setCurrent_market_price(double current_market_price) {
        this.current_market_price = current_market_price;
    }

    private double current_market_price;

    public PortfolioEntity(String ticker, int quantity,
                           double avg_buy_price,double current_market_price) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.avg_buy_price = avg_buy_price;
        this.current_market_price = current_market_price;
    }
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAvg_buy_price() {
        return avg_buy_price;
    }

    public void setAvg_buy_price(double avg_buy_price) {
        this.avg_buy_price = avg_buy_price;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", avg_buy_price=" + avg_buy_price +
                '}';
    }
}

