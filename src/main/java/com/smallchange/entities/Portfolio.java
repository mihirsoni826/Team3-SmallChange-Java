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
@Table(schema = "scott", name = "PORTFOLIO")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "AVG_BUY_PRICE")
    private double avg_buy_price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMAIL", nullable = false)
    private Users user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "TICKER")
    private Security security;

}

