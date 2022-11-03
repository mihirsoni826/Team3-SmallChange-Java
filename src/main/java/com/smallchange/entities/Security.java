package com.smallchange.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "scott", name = "SECURITY")
public class Security {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;

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
