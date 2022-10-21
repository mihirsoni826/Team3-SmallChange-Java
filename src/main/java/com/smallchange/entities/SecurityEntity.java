package com.smallchange.entities;

public class SecurityEntity {
    private String ticker;
    private String securityName;
    private String assetClass;
    private String accountType;
    private String subAccountType;
    private double marketPrice;

    public SecurityEntity(String ticker, String securityName, String assetClass, String accountType, String subAccountType, double marketPrice) {
        this.ticker = ticker;
        this.securityName = securityName;
        this.assetClass = assetClass;
        this.accountType = accountType;
        this.subAccountType = subAccountType;
        this.marketPrice = marketPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSubAccountType() {
        return subAccountType;
    }

    public void setSubAccountType(String subAccountType) {
        this.subAccountType = subAccountType;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    @Override
    public String toString() {
        return "SecurityEntity{" +
                "ticker='" + ticker + '\'' +
                ", securityName='" + securityName + '\'' +
                ", assetClass='" + assetClass + '\'' +
                ", accountType='" + accountType + '\'' +
                ", subAccountType='" + subAccountType + '\'' +
                ", marketPrice=" + marketPrice +
                '}';
    }
}
