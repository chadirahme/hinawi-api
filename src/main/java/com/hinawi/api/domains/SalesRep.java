package com.hinawi.api.domains;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SalesRep")
@Getter
@Setter
@NoArgsConstructor
public class SalesRep {

    @Id
    @Column(name="salesRep_Key")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesRepKey;

    @Column(name="Initial")
    private String initial;

   // @Column(name="qblistkey")
    //private Long qblistkey;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "qblistkey", referencedColumnName = "RecNo")
    private QBLists qbLists;


}
