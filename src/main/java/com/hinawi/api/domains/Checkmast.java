package com.hinawi.api.domains;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Checkmast")
@Getter
@Setter
@NoArgsConstructor
public class Checkmast {

    //PrintName,Memo,CheckNo,CheckDate,swiftCode,Cheque from Checkmast order by PVDate desc

    @Id
    @Column(name="RecNo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

    @Column(name="PVNo")
    private String pvNo;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="pvDate")
    private Date pvDate;

    @Column(name="PayeeType")
    private String payeeType;

    @Column(name="Amount")
    private float amount;

    @Column(name="PrintName")
    private String printName;

    @Column(name="Memo")
    private String memo;

    @Column(name="CheckNo")
    private String checkNo;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="CheckDate")
    private Date checkDate;

    @Column(name="swiftCode")
    private String swiftCode;

    @Column(name="Cheque")
    private String Cheque;

}
