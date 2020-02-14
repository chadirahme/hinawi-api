package com.hinawi.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Quotation")
@Getter
@Setter
@NoArgsConstructor
public class Quotation {

    @Id
    @Column(name="RecNo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="TxnDate")
    private Date txnDate;

    @Column(name="ClientType")
    private String clientType;

    @Column(name="CustomerRefKey")
    private int customerRefKey;

    @Column(name="Status")
    private String status;

}
