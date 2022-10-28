package com.smallchange.entities;

import java.sql.Timestamp;

public class TradeHistoryEntity {
    private String transaction_id;
    private String ticker;
    private String security_name;
    private String account_type;
    private Timestamp transaction_date;
    private String trad_type;
    private String asset_class;
    private double trade_price;
    private int quantity;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getSecurity_name() {
        return security_name;
    }

    public void setSecurity_name(String security_name) {
        this.security_name = security_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Timestamp getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Timestamp transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTrad_type() {
        return trad_type;
    }

    public void setTrad_type(String trad_type) {
        this.trad_type = trad_type;
    }

    public String getAsset_class() {
        return asset_class;
    }

    public void setAsset_class(String asset_class) {
        this.asset_class = asset_class;
    }

    public double getTrade_price() {
        return trade_price;
    }

    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public TradeHistoryEntity(String transaction_id, String ticker, String security_name, String account_type, Timestamp transaction_date, String trad_type, String asset_class, double trade_price, int quantity) {
        this.transaction_id = transaction_id;
        this.ticker = ticker;
        this.security_name = security_name;
        this.account_type = account_type;
        this.transaction_date = transaction_date;
        this.trad_type = trad_type;
        this.asset_class = asset_class;
        this.trade_price = trade_price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TradeHistoryEntity{" +
                "transaction_id=" + transaction_id +
                ", ticker='" + ticker + '\'' +
                ", security_name='" + security_name + '\'' +
                ", account_type='" + account_type + '\'' +
                ", transaction_date=" + transaction_date +
                ", trad_type='" + trad_type + '\'' +
                ", asset_class='" + asset_class + '\'' +
                ", trade_price=" + trade_price +
                ", quantity=" + quantity +
                '}';
    }
}
