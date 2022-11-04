package com.smallchange.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(schema = "scott", name = "bank_account_details")
public class BankAccount {
    @Id
    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "balance")
    private double balance;

    @Column(name = "user_email")
    private String email;
}
