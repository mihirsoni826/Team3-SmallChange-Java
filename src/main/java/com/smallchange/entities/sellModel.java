package com.smallchange.entities;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class sellModel {
    private Security security;

    private int quantity;
    private int accountNumber;
    private String dateOfPurchase;
    private double securityPrice;
    private long timeMilli;
    private Users user;
    public static final String SELL = "Sell";

}

