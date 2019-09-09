package com.hinawi.api.domains;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Class")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Class_Type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
public class ClassMaster {

    @Id
    @Column(name="Class_Key")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classKey;


    @Column(name="Class_Type",insertable = false, updatable = false)
    private String classType;


    @Column(name="Name")
    private String name;

    @Column(name="FullName")
    private String fullName;

    @Column(name="Status")
    private String status;
}
