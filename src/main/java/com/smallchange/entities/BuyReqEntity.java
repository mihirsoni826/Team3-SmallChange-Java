package com.smallchange.entities;

public class BuyReqEntity {
    private String ticker;
    private String assetClass;
    private String accountType;
    private int quantity;
    private int accountNumber;
    private String dateOfPurchase;
    private double securityPrice;
    private long timeMilli;
    private String buyOrSell;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(String buyOrSell) {
        this.buyOrSell = buyOrSell;
    }

    public long getTimeMilli() {
        return timeMilli;
    }

    public void setTimeMilli(long timeMilli) {
        this.timeMilli = timeMilli;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getSecurityPrice() {
        return securityPrice;
    }

    public void setSecurityPrice(double securityPrice) {
        this.securityPrice = securityPrice;
    }

    @Override
    public String toString() {
        return "BuyReqModal {" +
                "security='" + ticker + '\'' +
                ", assetClass='" + assetClass + '\'' +
                ", quantity=" + quantity +
                ", accountNumber=" + accountNumber +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                ", securityPrice=" + securityPrice +
                '}';
    }
}
