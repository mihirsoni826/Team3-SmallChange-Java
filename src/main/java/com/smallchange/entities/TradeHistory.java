package com.smallchange.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;



@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "scott", name = "TRADE_HISTORY")
public class TradeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "SECURITY_NAME")
    private String securityName;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "TRANSACTION_DATE")
    private Timestamp transactionDate;

    @Column(name = "TRADE_TYPE")
    private String tradeType;

    @Column(name = "ASSET_CLASS")
    private String assetClass;

    @Column(name = "TRADE_PRICE")
    private double tradePrice;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "EMAIL")
    private String email;
}

