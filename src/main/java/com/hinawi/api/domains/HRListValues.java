package com.hinawi.api.domains;

import com.hinawi.api.dto.TruncateAnnotation;
import com.hinawi.api.dto.TruncatedStringConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

import static com.hinawi.api.domains.MultilingualText.FIELD_ENG;
import static com.hinawi.api.domains.MultilingualText.FIELD_FRE;

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

    @Column(name="DESCRIPTION",insertable = false, updatable = false)
    private String description;

    @Column(name="ARABIC",insertable = false, updatable = false)
    private String arDescription;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = FIELD_ENG, column = @Column(name = "DESCRIPTION", nullable = true)),
            @AttributeOverride(name = FIELD_FRE, column = @Column(name = "ARABIC", nullable = true))
    })
    private MultilingualText _disclaimer;

    @Column(name="SUB_ID")
    private Integer subId;

    @Column(name="DEF_VALUE")
    private String defaultValue;

    @Column(name="REQUIRED")
    private String required;

    @Column(name="PRIORITY_ID")
    private Integer priorityId;

    @Convert(converter = TruncatedStringConverter.class)
    @Column(name="IsEdit")
    private String isEdit;

    @Column(name="Notes")
    private String notes;

    @Column(name="QBListID")
    private String qbListID;
}
