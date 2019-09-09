package com.hinawi.api.services.impl;

import com.hinawi.api.dao.ChequeDAO;
import com.hinawi.api.domains.Checkmast;
import com.hinawi.api.dto.ChequeModel;
import com.hinawi.api.repository.CheckMastRepository;
import com.hinawi.api.repository.PaymentsStatistics;
import com.hinawi.api.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    ChequeDAO chequeDAO;

    @Autowired
    CheckMastRepository checkMastRepository;

    @PersistenceContext
    private EntityManager _entityManager;


    @Override
    public List<ChequeModel> getChequesUnderCollectionList() {
        List<ChequeModel> lst= chequeDAO.getChequesUnderCollectionList();
        return lst;
    }

    @Override
    public List<ChequeModel> getPurchaseOrderList() {
        List<ChequeModel> lst= chequeDAO.getPurchaseOrderList();
        return lst;
    }

    @Override
    public int approvePurchaseOrder(int recNo,String status) {
        return chequeDAO.approvePurchaseOrder(recNo,status);
    }

    @Override
    public List<Checkmast> getPettyCashList() {
        //return checkMastRepository .findAll(new Sort(Sort.Direction.DESC, "pvDate"));
        return _entityManager.createNamedQuery(Checkmast.QUERY_GET_ALL).getResultList();//. .findAll(new Sort(Sort.Direction.DESC, "pvDate"));
    }

    @Override
    public List<PaymentsStatistics> findAllPettyCashByYear(Integer year) {
        //int month=1;
        List<PaymentsStatistics> result=new ArrayList<>();
        List<PaymentsStatistics> lst=checkMastRepository.findAllPettyCashByYear(year);
        for(int month=1;month<13;month++) {
            PaymentsStatistics item= findPettyCashByMonth(month,lst);
           result.add(item);
        }
        return result;//checkMastRepository.findAllPettyCashByYear(year);
    }

    private PaymentsStatistics findPettyCashByMonth(int month,List<PaymentsStatistics> lst)
    {
        PaymentsStatistics matchingObject = lst.stream().
                filter(p -> p.getPaymonth()==month).
                findAny().orElse(new PaymentsStatistics() {
            @Override
            public Double getTotal() {
                return new Double(0);
            }
            @Override
            public int getPaymonth() {
                return month;
            }
            @Override
            public String getStatus() {
                return "";
            }
        });
        return matchingObject;
    }

//    public int savePettyCash(CheckModel item)
//    {
//        int result = 0;
//        try
//        {
//            string sql = "";
//            SqlConnection conn;
//            SqlCommand cmd;
//            int RecNo = getMaxID("Checkmast", "RecNo");
//            //int PVNo = getMaxID("Checkmast", "PVNo");
//
//            item.Memo = item.Memo.Replace("'", "''");
//
//            sql = String.Format(@"Insert into Checkmast (RecNo,PVNo,PVDate,PayeeType,Amount,PrintName,Memo,UserID,CheckNo,CheckDate,swiftCode,Cheque)
//            values ('{0}','{1}','{2}','{3}','{4}' ,'{5}','{6}',1,'{7}','{8}','{9}','{10}')",
//            RecNo, RecNo, item.PVDate, item.AccountType, item.Amount, item.AccountName, item.Memo,item.CheckNo,item.CheckDate,item.BankName,item.currency);
//
//            conn = new SqlConnection(ConnectionString);
//            cmd = conn.CreateCommand();
//            cmd.CommandText = sql;
//            conn.Open();
//            result = cmd.ExecuteNonQuery();
//            conn.Close();
//            result = RecNo;
//            return result;
//        }
//        catch (Exception ex)
//        {
//            return -1;
//        }
//    }
}
