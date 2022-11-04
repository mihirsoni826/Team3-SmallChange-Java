package com.smallchange.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "scott", name = "securities")
public class Security {

    @Id
    @Column(name = "TICKER")
    private String ticker;

    @Column(name = "SECURITY_NAME")
    private String securityName;

    @Column(name = "ASSET_CLASS")
    private String assetClass;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "SUB_ACCOUNT_TYPE")
    private String subAccountType;

    @Column(name = "CURRENT_MARKET_PRICE")
    private double marketPrice;

}