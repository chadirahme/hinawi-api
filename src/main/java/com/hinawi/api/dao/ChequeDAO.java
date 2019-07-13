package com.hinawi.api.dao;

import com.hinawi.api.dto.ChequeModel;

import java.util.List;

public interface ChequeDAO {

    public List<ChequeModel> getChequesUnderCollectionList();
    public List<ChequeModel> getPurchaseOrderList();
    public int approvePurchaseOrder(int recNo,String status);
}
