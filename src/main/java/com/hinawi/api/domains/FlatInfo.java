package com.hinawi.api.domains;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("F")
@Getter
@Setter
@NoArgsConstructor
public class FlatInfo extends ClassMaster{


    @Column(name="Parent")
    private String parent;

}


//    CREATE TABLE [dbo].[Class](
//        [Class_Key] [int] NULL,
//        [ListID] [nvarchar](50) NULL,
//        [TimeCreated] [nvarchar](50) NULL,
//        [TimeModified] [nvarchar](50) NULL,
//        [EditSequence] [int] NULL,
//        [Name] [nvarchar](255) NULL,
//        [FullName] [nvarchar](255) NULL,
//        [Parent] [nvarchar](255) NULL,
//        [IsActive] [nvarchar](1) NULL,
//        [Sublevel] [int] NULL,
//        [Class_Type] [nvarchar](50) NULL,
//        [Status] [nvarchar](1) NULL,
//        [Sub_Of_Parent] [int] NULL,
//        [EditedFromOnline] [nvarchar](1) NULL,
//        [NameArabic] [nvarchar](255) NULL,
//        [ArFullName] [nvarchar](255) NULL
//        ) ON [PRIMARY]