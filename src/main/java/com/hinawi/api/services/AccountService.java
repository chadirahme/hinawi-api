package com.hinawi.api.services;

import com.hinawi.api.domains.Checkmast;
import com.hinawi.api.dto.ChequeModel;
import com.hinawi.api.repository.PaymentsStatistics;

import java.util.List;

public interface AccountService {
    List<ChequeModel> getChequesUnderCollectionList();

    List<ChequeModel> getPurchaseOrderList();

    int approvePurchaseOrder(int recNo, String status);

    List<Checkmast> getPettyCashList();

    List<PaymentsStatistics> findAllPettyCashByYear(Integer year);
}
