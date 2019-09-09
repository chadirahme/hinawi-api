package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "Flat")
@Getter
@Setter
@NoArgsConstructor
public class Flat {

    @Id
    @Column(name="RecNo")
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

    @Column(name="FlatNo")
    private String flatNo;

    @Column(name="Floor")
    private Integer floor;

    @Column(name="Bldg_Key")
    private Integer bldgKey;

    @Column(name="Size")
    private Float size;

//    @Column(name="SizeType")
//    private Integer sizeType;

    @Column(name="NoofRooms")
    private Integer noOfRooms;

    @Column(name="NoofBalcony")
    private Integer noOfBalcony;

    @Column(name="NoofBathRooms")
    private Integer noOfBathRooms;

    @Column(name="NoofHousemaidRooms")
    private Integer noOfHousemaidRooms;

    @Column(name="Description")
    private String description;

    @Column(name="Status")
    private String status;

//    @Column(name="FlatType")
//    private Integer flatType;

    @Column(name="Purpose")
    private Integer purpose;

    @Column(name="NoPersonsAllowed")
    private Integer noPersonsAllowed;

    @Column(name="RentMode")
    private String rentMode;

    @Column(name="RentPer")
    private String rentPer;

    @Column(name="Rent")
    private Float rent;

    @Column(name="AnnualRent")
    private Float annualRent;

    @Column(name="Deposit")
    private Float deposit;

    @Column(name="Notes")
    private String notes;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FlatType", referencedColumnName = "ID")
    private HRListValues flatTypeDesc;

    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne//(cascade=CascadeType.MERGE)//(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "SizeType", referencedColumnName = "ID")
    private HRListValues sizeTypeDesc;
}

//
//
//        CREATE TABLE [dbo].[Flat](
//        [RecNo] [int] NULL,
//        [ListID] [nvarchar](25) NULL,
//        [EditSequence] [int] NULL,
//        [FlatNo] [nvarchar](50) NULL,
//        [Floor] [int] NULL,
//        [Bldg_Key] [int] NULL,
//        [Size] [float] NULL,
//        [SizeType] [int] NULL,
//        [NoofRooms] [int] NULL,
//        [NoofBalcony] [int] NULL,
//        [NoofBathRooms] [int] NULL,
//        [NoofHousemaidRooms] [int] NULL,
//        [Description] [nvarchar](255) NULL,
//        [Status] [nvarchar](1) NULL,
//        [FlatType] [int] NULL,
//        [Purpose] [int] NULL,
//        [NoPersonsAllowed] [int] NULL,
//        [RentMode] [nvarchar](1) NULL,
//        [RentPer] [nvarchar](1) NULL,
//        [Rent] [float] NULL,
//        [AnnualRent] [float] NULL,
//        [Deposit] [float] NULL,
//        [Notes] [nvarchar](255) NULL,
//        [Photo1] [nvarchar](max) NULL,
//        [Photo2] [nvarchar](max) NULL,
//        [Photo3] [nvarchar](max) NULL,
//        [Change_Price] [nvarchar](1) NULL,
//        [TermOfPayment] [nvarchar](1) NULL,
//        [ContractRecNo] [int] NULL,
//        [Parking] [int] NULL,
//        [DepositPaid] [float] NULL,
//        [TPayChange] [nvarchar](1) NULL,
//        [Change_Deposit] [nvarchar](1) NULL,
//        [Min_Rent] [float] NULL,
//        [Max_Rent] [float] NULL,
//        [Min_Deposit] [float] NULL,
//        [Max_Deposit] [float] NULL,
//        [Allow_ExceedMaxRent] [nvarchar](1) NULL,
//        [Allow_ExceedMaxDeposit] [nvarchar](1) NULL,
//        [Allow_BelowMinRent] [nvarchar](1) NULL,
//        [Allow_BelowMinDeposit] [nvarchar](1) NULL,
//        [FlatNoAR] [nvarchar](50) NULL,
//        [Default_ARAccount] [int] NULL,
//        [Default_IncomeAct] [int] NULL,
//        [Default_DiffreredAct] [int] NULL,
//        [Default_SecurityAct] [int] NULL,
//        [Default_RetentionAct] [int] NULL,
//        [AllowToChangeAct] [nvarchar](1) NULL,
//        [AllowToChangeARAccount] [nvarchar](1) NULL,
//        [AllowToChangeIncomeAct] [nvarchar](1) NULL,
//        [AllowToChangeDiffreredAct] [nvarchar](1) NULL,
//        [AllowToChangeSecurityAct] [nvarchar](1) NULL,
//        [AllowToChangeRetentionAct] [nvarchar](1) NULL,
//        [Default_CashAct] [int] NULL,
//        [Default_ChequeAct] [int] NULL,
//        [Default_CUCAct] [int] NULL,
//        [Default_CUCBankAct] [int] NULL,
//        [Deferred_CurrentMonth] [nvarchar](1) NULL,
//        [Notes_Status] [nvarchar](255) NULL,
//        [ParkingID] [int] NULL
//        ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

