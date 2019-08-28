package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ProspectiveCotact")
@Getter
@Setter
@NoArgsConstructor
public class ProspectiveCotact {

//    @Id
//    @Column(name="RecNo")
//    //@GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long recNo;
//
//    @Id
     // @Column(name="[LineNo]")
//    //@GeneratedValue(strategy = GenerationType.IDENTITY)
     //private Long lineNo;

    @EmbeddedId
    ProspectiveCotactId prospectiveCotactId;

    @Column(name="name")
    private String name;

    @Column(name="telephone1")
    private String telephone1;

    @Column(name="telephone2")
    private String telephone2;

    @Column(name="Fax")
    private String fax;

    @Column(name="Mobile1")
    private String mobile1;


    @Column(name="email")
    private String email;

    @Column(name="defaultCont")
    private String defaultCont;

    @Column(name="Position")
    private String position;
}




