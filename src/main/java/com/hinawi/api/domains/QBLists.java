package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QBLists")
@Getter
@Setter
@NoArgsConstructor
public class QBLists {

    @Id
    @Column(name="RecNo")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

    @Column(name="FullName")
    private String fullName;

    @Column(name="ListType")
    private String listType;

}
