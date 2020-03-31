package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Prospective")
@Getter
@Setter
@NoArgsConstructor
public class Prospective {

    @Id
    @Column(name="RecNo")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

    @Column(name="name")
    private String name;

    @Column(name="arabic")
    private String arName;

    @Column(name="fullname")
    private String fullName;

    @Column(name="isActive")
    private String active;

    @Column(name="companyName")
    private String companyName;

    @Column(name="email")
    private String email;

    @Column(name="CC")
    private String cc;


    @Column(name="note")
    private String note;

    @Column(name="contact")
    private String contact;

    @Column(name="altContact")
    private String altContact;

    @Column(name="telephone1")
    private String telephone1;

    @Column(name="telephone2")
    private String telephone2;

    @Column(name="WebSite")
    private String webSite;

    @Column(name="PrintChequeAs")
    private String printChequeAs;

    @Column(name="skypeId")
    private String skypeId;

    @Column(name="TimeCreated")
    private Date timeCreated;

    @Column(name="Sublevel")
    private Integer sublevel;

    @Column(name="ParentRefKey")
    private Integer parentRefKey;


    @Column(name="PriorityID")
    private Integer priorityID;

    @Column(name="CompanyTypeRefKey")
    private Integer companyTypeRefKey;

    @Column(name="CompanySizeRefKey")
    private Integer companySizeRefKey;

    @Column(name="CountryRefKey")
    private Integer countryRefKey;

    @Column(name="CityRefKey")
    private Integer cityRefKey;

    @Column(name="StreeRefKey")
    private Integer streeRefKey;

    @Column(name="HowKnowRefKey")
    private Integer howKnowRefKey;

    @Column(name="fax")
    private String fax;

    @Column(name="shipto")
    private String shipto;

    @Column(name="altphone")
    private String altphone;

    @Transient
    private List<ProspectiveCotact> lstProspectiveCotact;


    @Column(name="SoftwareRefKey")
    private Integer softwareRefKey;

    @Column(name="UserNos")
    private Integer userNos;

    @Column(name="EmpNos")
    private Integer empNos;

    @Column(name="Ownername")
    private String ownername;

    @Column(name="ManagerName")
    private String managerName;

    @Column(name="AuditorName")
    private String auditorName;

    @Column(name="AccountantName")
    private String accountantName;

    @Column(name="LastTrialBalance")
    private Date lastTrialBalance;

    @Column(name="WorkingHrs")
    private Integer workingHrs;

    @Column(name="SalesRepKey")
    private Integer salesRepKey;

    @Column(name="InterestLevel")
    private String interestLevel;


    @Column(name="Address1")
    private String poBox;

    @Column(name="Address2")
    private String zipCode;

    @Column(name="WebUserName")
    private String webUserName;

    //used when send email to know which file we used
    @Transient
    private String webCompanyName;

//    @Column(name="altcontact")
//    private String altcontact;

    @Transient
    private String hasQuotation;
    public String getHasQuotation(){
        if(hasQuotation==null)
            hasQuotation= "No";

        return hasQuotation;
    }
}
