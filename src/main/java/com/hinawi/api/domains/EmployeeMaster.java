package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EmpMast")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeMaster {

    @Id
    @Column(name="Emp_Key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empKey;

    @Column(name="Emp_NO")
    private String employeeNumber;

    @Column(name="English_Full")
    private String englishFullName;

    @Column(name="Active")
    private String active;

    @Column(name="Employeement_date")
    private Date employeementDate;

    @Column(name="RatePerHour")
    private Double ratePerHour;

}
