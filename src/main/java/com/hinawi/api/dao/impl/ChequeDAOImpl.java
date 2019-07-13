package com.hinawi.api.dao.impl;

import com.hinawi.api.dao.ChequeDAO;
import com.hinawi.api.dto.ChequeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ChequeDAOImpl implements ChequeDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ChequeModel> getChequesUnderCollectionList() {


       String sql = " SELECT * from customer";
       sql="SELECT RVMast.Rec_No,RVMAST.Status,RVMast.Mode, RVMast.TimeCreated , RVMast.RefNumber AS RvNo, " +
               "RvMast.Printed4Approval,RVMast.TxnDate AS RvDate                   , RVCustomer.Name AS CustomerName   ,RVCustomer.ArName as ArName, " +
               "RVArAccount.AccountNumber AS ArAccountNo,RVArAccount.Name AS ArAccountName    ,Banks.Name AS BankName  ," +
               "RVDetails.ChequeNo,RVDetails.ChequeDate , RvDepositToAccount.AccountNumber AS DepositToAccountNo, RvDepositToAccount.Name AS DepositToAccountName " +
               ", RVDetails.Amount  , RvCUCAccount.Name AS CUCAccountName        , RvCUCAccount.AccountNumber AS CUCAccountNo, RVDetails.paymentmethod" +
               " , RVDetails.Memo AS RvMemo,RVdetails.QBstatus,RVDetails.TxnID, RVDetails.Status AS RvStatus               , RVMast.RvOrJv AS " +
               "PostToQBBy,RVDetails.RemoveFromCUC,  Class4BF.FullName     ,Class4BF.ArFullName, Class4BF.Name as ClassName    ,Class4BF.NameArabic as ClassNameAr,Class4BF.Class_Type    ,Class4BF.SubLevel " +
               ",RVDetails.Bank_Key , RVMast.TotalAmount" +
               " FROM ((((((RVMast INNER   JOIN RVDetails                     ON RVMast.Rec_No                    = RVDetails.Rec_No)            LEFT    JOIN QBLists AS " +
               " RVCustomer          ON RVMast.CustomerRef_key            = RVCustomer.RecNo)            LEFT    JOIN Accounts AS RVArAccount        ON " +
               " RVMast.ArAccountRef_Key           = RVArAccount.REC_NO)            LEFT    JOIN Banks                          ON RVDetails.Bank_Key  = " +
               " Banks.RecNo)            LEFT    JOIN Class As Class4BF              ON RVDetails.ClassRef_Key            = Class4BF.Class_Key)" +
               " LEFT    JOIN Accounts AS RvDepositToAccount ON RVDetails.DepositToAccountRef_Key = RvDepositToAccount.REC_NO)            LEFT    JOIN Accounts " +
               " AS RvCUCAccount       ON RVDetails.CUCAcctKey              = RvCUCAccount.REC_NO Where RVMast.mode " +
               " in ('Cheque','Cheque-Contract','Cheque-Opening','Both','Both-Contract') " +
               " and (RvDepositToAccount.AccountType ='Cheque Under Collection' Or RvCUCAccount.AccountType ='Cheque Under Collection')" +
               " order by TxnDate,RVDetails.amount ";



        List<ChequeModel> lst = this.jdbcTemplate.query(
                sql,
                new RowMapper<ChequeModel>() {
                    public ChequeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String status="" , PostToQBBy="";
                        ChequeModel chequeModel = new ChequeModel();
                        chequeModel.setId(rs.getInt("Rec_No"));
                        chequeModel.setRVNo(rs.getString("RvNo"));
                        chequeModel.setRVDate(rs.getDate("RvDate"));
                        chequeModel.setCustomerName(rs.getString("CustomerName"));
                        chequeModel.setAccountNumber(rs.getString("ArAccountNo"));
                        chequeModel.setAccountName(rs.getString("ArAccountName"));
                        if (!IsNullOrEmpty(chequeModel.getAccountNumber()))
                        {
                            chequeModel.setAccountName(chequeModel.getAccountNumber() + " . " + chequeModel.getAccountName());
                        }

                        chequeModel.setMode(rs.getString("Mode"));
                        chequeModel.setStatus(rs.getString("Status"));
                        if (!IsNullOrEmpty(chequeModel.getStatus()))
                        {
                            switch (chequeModel.getStatus())
                            {
                                case "C":
                                    status = "Created (Printed Temporary RV)";
                                    break;
                                case "A":
                                    status = "Approved (Printed Temporary RV)";
                                    break;
                                case "P":
                                    status = "RV Printed";
                                    break;
                                default:
                                    break;
                            }
                        }


                        chequeModel.setPrinted4Approval(rs.getString("Printed4Approval"));
                        chequeModel.setBankName(rs.getString("BankName"));
                        chequeModel.setChequeNo(rs.getString("ChequeNo"));
                        chequeModel.setChequeDate(rs.getDate("ChequeDate"));
                        chequeModel.setDepositToAccountName(rs.getString("DepositToAccountName"));
                        chequeModel.setDepositToAccountNo(rs.getString("DepositToAccountNo"));

                        if (!IsNullOrEmpty(chequeModel.getDepositToAccountNo()))
                        {
                            chequeModel.setDepositToAccountName ( chequeModel.getDepositToAccountNo() + " . " + chequeModel.getDepositToAccountName());
                        }

                        chequeModel.setAmount(rs.getFloat("Amount"));
                        chequeModel.setMemo(rs.getString("RvMemo"));
                        chequeModel.setPostToQBBy(rs.getString("PostToQBBy"));
                        if (!IsNullOrEmpty(chequeModel.getPostToQBBy()))
                        {
                            switch (chequeModel.getPostToQBBy())
                            {
                                case "J":
                                    PostToQBBy = "Journal Voucher";
                                    break;
                                case "R":
                                    PostToQBBy = "Receipt Voucher";
                                    break;
                                case "NP":
                                    PostToQBBy = "CUC Opening - Journal Voucher";
                                    break;

                                default:
                                    break;
                            }
                        }
                        chequeModel.setPostToQBBy( PostToQBBy);

                        chequeModel.setPaymentmethod(rs.getString("paymentmethod"));
                        if (chequeModel.getPaymentmethod().equals("Both"))
                            chequeModel.setPaymentmethod( "Cash & Cheque");
                        else
                            chequeModel.setPaymentmethod( "Cheque");
                        chequeModel.setRVstatus(rs.getString("RVstatus")) ;

                        if (!IsNullOrEmpty(chequeModel.getRVstatus()))
                        {
                            switch (chequeModel.getRVstatus())
                            {
                                case "C":
                                    status = "Created";
                                    break;
                                case "A":
                                    status = "Approved";
                                    break;
                                case "P":
                                    status = "RV Printed";
                                    break;
                                case "H":
                                    status = "Hold";
                                    break;
                                case "R":
                                    status = "Returned";
                                    break;
                                case "N":
                                    status = "Cancelled";
                                    break;
                                case "F":
                                    status = "Passed/Cleared";
                                    break;
                                case "O":
                                    status = "Posted";
                                    break;
                                case "L":
                                    status = "Lost";
                                    break;
                                case "V":
                                    status = "Void";
                                    break;

                                default:
                                    break;
                            }
                        }

                        chequeModel.setRemoveFromCUC(rs.getString("RemoveFromCUC"));
                        if (!IsNullOrEmpty(chequeModel.getRemoveFromCUC()))
                        {
                            if (chequeModel.getRemoveFromCUC().equals("Y"))
                            {
                                status = "Cleared without Posting";
                            }
                        }

                        chequeModel.setQBstatus(rs.getString("QBstatus")) ;
                        chequeModel.setTxnID(rs.getString("txnID"));
                        if (!IsNullOrEmpty(chequeModel.getQBstatus()))
                        {
                            if (chequeModel.getQBstatus().equals("Y") && IsNullOrEmpty(chequeModel.getTxnID()))
                            {
                                status = "Cleared without Posting";
                            }
                        }
                        chequeModel.setStatus(status);

                        return chequeModel;
                    }
                });

        return lst;
    }

    private boolean IsNullOrEmpty(String val){
        if(val==null || val.isEmpty()) return true;
        else return false;
    }

    public List<ChequeModel> getPurchaseOrderList()
    {
        String sql = "SELECT distinct PurchaseOrder.rec_no, PurchaseOrder.txnDate , VENDOR.FULLNAME as VendorName,QBLists.FullName As DropShipto,Class.Name As ClassName,\n" +
                "   QBitems.Name as ItemName,Customerlists.FullName As CustomerName, PurchaseOrderLine.Amount,PurchaseOrderLine.rate,\n" +
                "  PurchaseOrderLine.Quantity,PurchaseOrderLine.RcvdQuantity,PurchaseOrderLine.Description AS itemDesc\n" +
                "  FROM PurchaseOrder  INNER JOIN PurchaseOrderLine ON PurchaseOrder.Rec_No  = PurchaseOrderLine.Rec_No\n" +
                "  LEFT  JOIN Vendor ON PurchaseOrder.VendorRefKey         = Vendor.Vend_Key\n" +
                "  LEFT  JOIN QBLists ON PurchaseOrder.EntityRefKey           = QBLists.RecNo\n" +
                "  LEFT  JOIN Class ON PurchaseOrder.ClassRefKey             = Class.Class_Key\n" +
                "  LEFT  JOIN QBLists As Customerlists   ON PurchaseOrderLine.EntityRefKey    = Customerlists.RecNo\n" +
                "  LEFT  JOIN QBItems ON PurchaseOrderLine.ItemRefKey      = QBItems.Item_Key\n" +
                "where PurchaseOrder.status = 'C'\n" +
                "order by PurchaseOrder.Rec_No";

        List<ChequeModel> lst = this.jdbcTemplate.query(
                sql,
                new RowMapper<ChequeModel>() {
                    public ChequeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String status="" , PostToQBBy="";
                        ChequeModel chequeModel = new ChequeModel();
                        chequeModel.setId(rs.getInt("Rec_No"));
                        chequeModel.setRVDate(rs.getDate("txnDate"));
                        chequeModel.setCustomerName(rs.getString("CustomerName"));
                        chequeModel.setItemName(rs.getString("ItemName"));
                        chequeModel.setVendorName(rs.getString("VendorName"));
                        chequeModel.setAmount(rs.getFloat("Amount"));
                        chequeModel.setRate(rs.getFloat("rate"));
                        chequeModel.setQuantity(rs.getFloat("Quantity"));
                        return chequeModel;
                    }
                });

        return lst;

    }

    //https://examples.javacodegeeks.com/enterprise-java/spring/jdbc/spring-jdbctemplate-example/
    public int approvePurchaseOrder(int recNo,String status)
    {
        String sql = "Update PurchaseOrder set Status=? where rec_no=?";
       return this.jdbcTemplate.update(sql, new Object[] {status, recNo});
       //sql = "Update PurchaseOrder set Status='A' where rec_no=" + recNo;
       // return 1;
    }

}
