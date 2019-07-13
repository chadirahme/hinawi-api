package com.hinawi.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChequeModel {
    int id;
    String chequeNo;
    Date chequeDate;

    String cheque;

    int PayeeKey;

    String CustomerName;

    float Amount;

    String PVNo;

    Date PVDate;

    String AccountNumber;

    String AccountName;

    String BankName;

    String Memo;

    String company;

    String currency; //Cheque nvarchar(20)
    String swiftCode; //BankName nvarchar(50)


    //collection

     String RVNo ;
     Date RVDate ;

     String Mode ;
     String Status ;

     String Printed4Approval ;

     String DepositToAccountNo ;
     String DepositToAccountName ;

     String PostToQBBy ;

     String paymentmethod ;

     String RVstatus ;
     String RemoveFromCUC ;

     String QBstatus ;
     String txnID ;

     String AccountType ;

     int WebUserID ;
     String WebUserName ;

     //PurchaseOrderModel
    String VendorName;
    String ClassName;
    String ItemName;
    float rate;
    float Quantity;

}
