package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Prospective")
@Getter
@Setter
@NoArgsConstructor
public class Prospective {

    @Id
    @Column(name="RecNo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

    @Column(name="name")
    private String name;

    @Column(name="arabic")
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

    @Column(name="telephone1")
    private String telephone1;

    @Column(name="telephone2")
    private String telephone2;

}
