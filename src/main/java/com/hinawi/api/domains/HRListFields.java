package com.hinawi.api.domains;

import com.hinawi.api.dto.TruncateAnnotation;
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
//@NamedQueries({
//        @NamedQuery(name=HRListFields.QUERY_UPDATEDATE, query = HRListFields.UPDATE_DATE_BY_ID_QUERY)
//})
public class HRListFields {

    public static final String QUERY_UPDATEDATE = "HRListFields.QUERY_UPDATEDATE";
    public static final String UPDATE_DATE_BY_ID_QUERY = "UPDATE HRListFields SET lastModified=:lastModified where fieldId =:fieldId";

    @Id
    @Column(name="FIELD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fieldId;

    //@Column(name="ID")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Integer id;

    @Column(name="PARENT_LIST")
    private Integer parentListId;

    @Column(name="FIELD_NAME")
    private String fieldName;

    @Column(name="FIELD_DESCRIPTION")
    private String fieldDescription;

    @Column(name="ARABIC")
    private String fieldArDescription;

    @TruncateAnnotation
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
