package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
@Getter
@Setter
@NoArgsConstructor
public class Customers {

    @Id
    @Column(name="cust_Key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custKey;

    @Column(name="name")
    private String name;

    @Column(name="arname")
    private String arName;

    @Column(name="fullname")
    private String fullName;

    @Column(name="isActive")
    private String active;

    @Column(name="companyName")
    private String companyName;

    @Column(name="email")
    private String email;

}
