package com.hinawi.api.domains;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @Column(name="UserId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="emp_key")
    private String empKey;

    @Column(name="UserName")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="userPassword")
    private String password;

    public Users(String userName , String password) {
        this.userName = userName;
        this.password = password;
    }
}
