package com.hinawi.api.services.impl;

import com.hinawi.api.domains.HRListFields;
import com.hinawi.api.domains.HRListValues;
import com.hinawi.api.repository.HRListFieldsRepository;
import com.hinawi.api.repository.HRListValuesRepository;
import com.hinawi.api.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListServiceImpl implements ListService {

    @Autowired
    HRListFieldsRepository hrListFieldsRepository;

    @Autowired
    HRListValuesRepository hrListValuesRepository;

    @Override
    public List<HRListFields> getHRListFields() {
        return hrListFieldsRepository.findAll(new Sort(Sort.Direction.ASC, "fieldDescription"));
    }

    @Override
    public List<HRListValues> getHRListValues(Long fieldId) {
        return hrListValuesRepository.findByFieldIdOrderByFieldName(fieldId);
    }

    @Override
    public HRListValues saveHRListValues(HRListValues hrListValues) {
        //int newID=getMaxID("HRLISTVALUES", "ID");
        if(hrListValues.getId()==0){
            hrListValues.setId(hrListValuesRepository.getMaxId() +1);
        }
        return hrListValuesRepository.save(hrListValues);
        //return hrListValues;
    }
}
