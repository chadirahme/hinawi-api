package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Vendor")
@Getter
@Setter
@NoArgsConstructor
public class Vendors {

    @Id
    @Column(name="Vend_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendKey;

    @Column(name="name")
    private String name;

    @Column(name="arName")
    private String arName;

    @Column(name="fullname")
    private String fullName;

    @Column(name="isActive")
    private String active;

    @Column(name="companyName")
    private String companyName;

    @Column(name="email")
    private String email;

    @Column(name="note")
    private String note;

    @Column(name="contact")
    private String contact;

    @Column(name="phone")
    private String phone;

    @Column(name="altphone")
    private String altPhone;

    @Column(name="Balance")
    private Double balance;
}
