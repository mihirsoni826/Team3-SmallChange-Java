package com.smallchange.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "scott", name = "PORTFOLIO")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "AVG_BUY_PRICE")
    private double avg_buy_price;

    @Column(name="USER_EMAIL")
    private String user_email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TICKER")
    private Security security;

}

