package com.smallchange.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "scott", name = "USER_DETAILS")
public class Users {
    @Id
    @Column(name = "EMAIL", nullable = false, unique = true)
    public String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "DATE_OF_BIRTH")
    private Date dob;

    @Column(name = "PHONE_NUMBER")
    private Long phone;

    @Column(name = "RISK_APPETITE")
    private int riskAppetite;
}
