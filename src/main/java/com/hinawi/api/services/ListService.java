package com.hinawi.api.services;

import com.hinawi.api.domains.HRListFields;
import com.hinawi.api.domains.HRListValues;
import com.hinawi.api.domains.SalesRep;

import java.util.List;

public interface ListService {

    List<HRListFields> getHRListFields();
    List<HRListValues> getHRListValues(Long fieldId);
    List<HRListValues> getHRSubListValues(Long fieldId,Integer subId);
    HRListValues saveHRListValues(HRListValues hrListValues);

    List<SalesRep> getSalesRepList();

}
