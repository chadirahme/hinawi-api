package com.hinawi.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CustomerStatusHistory")
@Getter
@Setter
@NoArgsConstructor
public class CustomerStatusHistory {

    @Id
    @Column(name="RecNo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

    @Column(name="CustKey")
    private Integer custKey;

    @Column(name="UserID")
    private Integer userID;

    @Column(name="StatusID")
    private Integer statusID;

  //Left Join HRLISTVALUES As StatusList  ON CustomerStatusHistory.StatusID = StatusList.ID

    @Column(name="Type")
    private String type;

    @Column(name="StatusDescription")
    private String statusDescription;

    @Column(name="Createdfrom")
    private String createdfrom;

    @Column(name="ActionDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date actionDate;


}
