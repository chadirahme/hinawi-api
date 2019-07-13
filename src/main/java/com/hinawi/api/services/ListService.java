package com.hinawi.api.services;

import com.hinawi.api.domains.HRListFields;
import com.hinawi.api.domains.HRListValues;

import java.util.List;

public interface ListService {

    List<HRListFields> getHRListFields();
    List<HRListValues> getHRListValues(Long fieldId);
    HRListValues saveHRListValues(HRListValues hrListValues);
}
