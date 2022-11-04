package com.smallchange.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BuyRequest {
    private Security security;
    private int quantity;
    private String dateOfPurchase;
    private long timeInMilliseconds;
    private String accountNumber;
    private Users user;
    public static final String BUY = "Buy";
}
