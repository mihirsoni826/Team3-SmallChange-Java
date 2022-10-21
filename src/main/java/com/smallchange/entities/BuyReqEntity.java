package com.smallchange.entities;

public class BuyReqEntity {
    private SecurityEntity security;
    private int quantity;
    private String dateOfPurchase;
    private long timeInMilliseconds;
    private String accountNumber;

    public BuyReqEntity(SecurityEntity security, int quantity, String dateOfPurchase, long timeInMilliseconds, String accountNumber) {
        this.security = security;
        this.quantity = quantity;
        this.dateOfPurchase = dateOfPurchase;
        this.timeInMilliseconds = timeInMilliseconds;
        this.accountNumber = accountNumber;
    }

    public SecurityEntity getSecurity() {
        return security;
    }

    public void setSecurity(SecurityEntity security) {
        this.security = security;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "BuyReqEntity{" +
                "security=" + security +
                ", quantity=" + quantity +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                ", timeInMilliseconds=" + timeInMilliseconds +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
