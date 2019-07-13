package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HRLISTVALUES")
@Getter
@Setter
@NoArgsConstructor
public class HRListValues {

    @Id
    @Column(name="ID")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="FIELD_ID")
    private Long fieldId;

    @Column(name="FIELD_NAME")
    private String fieldName;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="ARABIC")
    private String arDescription;

    @Column(name="SUB_ID")
    private Integer subId;

    @Column(name="DEF_VALUE")
    private String defaultValue;

    @Column(name="REQUIRED")
    private String required;

    @Column(name="PRIORITY_ID")
    private Integer priorityId;

    @Column(name="IsEdit")
    private String isEdit;

    @Column(name="Notes")
    private String notes;
}
