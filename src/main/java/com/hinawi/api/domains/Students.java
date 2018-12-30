package com.hinawi.api.domains;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbm_Registration")
@Getter
@Setter
@NoArgsConstructor
public class Students {

    //select t.RegistrationId,t.EnrollNo,t.SchoolID,t.AcademicYear,t.RegistrationDate,
    //t.ProgramID,t.GradeID,t.SectionID,t.GenderID,,t.EnFirstName,t.EnMiddleName,t.EnLastName
   // from tbm_Registration t

    @Id
    @Column(name="RegistrationId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @Column(name="AcademicYear")
    private String academicYear;

    @Column(name="EnrollNo")
    private Integer enrollNo;

    @Column(name="SchoolID")
    private Integer schoolID;

    @Column(name="ProgramID")
    private Integer programID;
    @Column(name="GradeID")
    private Integer gradeID;
    @Column(name="SectionID")
    private Integer sectionID;
    @Column(name="GenderID")
    private Integer genderID;

    @Column(name="EnFirstName")
    private String enFirstName;
    @Column(name="EnMiddleName")
    private String enMiddleName;
    @Column(name="EnLastName")
    private String enLastName;

}
