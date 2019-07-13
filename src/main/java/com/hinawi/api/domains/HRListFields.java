package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "HRLISTFIELDS")
@Getter
@Setter
@NoArgsConstructor
public class HRListFields {

    @Id
    @Column(name="FIELD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    @Column(name="ID")
    private Integer id;

    @Column(name="PARENT_LIST")
    private Integer parentListId;

    @Column(name="FIELD_NAME")
    private String fieldName;

    @Column(name="FIELD_DESCRIPTION")
    private String fieldDescription;

    @Column(name="ARABIC")
    private String fieldArDescription;

    @Column(name="NEW_FLAG")
    private String newFlag;
    @Column(name="EDIT_FLAG")
    private String editFlag;
    @Column(name="DELETE_FLAG")
    private String deleteFlag;

    @Column(name="REQUIRED")
    private String required;

    @Column(name="LastModified")
    private Date lastModified;

}
