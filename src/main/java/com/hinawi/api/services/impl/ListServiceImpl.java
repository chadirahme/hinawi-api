package com.hinawi.api.services.impl;

import com.hinawi.api.domains.HRListFields;
import com.hinawi.api.domains.HRListValues;
import com.hinawi.api.domains.MultilingualText;
import com.hinawi.api.repository.HRListFieldsRepository;
import com.hinawi.api.repository.HRListValuesRepository;
import com.hinawi.api.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
@Service
public class ListServiceImpl implements ListService {

    @PersistenceContext
    private EntityManager _entityManager;


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
        return hrListValuesRepository.findByFieldIdOrderByDescription(fieldId);
    }
    @Override
    public List<HRListValues> getHRSubListValues(Long fieldId,Integer subId) {
        return hrListValuesRepository.getSubList(fieldId,subId);
    }

    @Override
    @Transactional
    public HRListValues saveHRListValues(HRListValues hrListValues) {
        //int newID=getMaxID("HRLISTVALUES", "ID");
        if(hrListValues.getId()==0){
            hrListValues.setId(hrListValuesRepository.getMaxId() +1);
        }
        if(hrListValues.get_disclaimer()==null){
            MultilingualText multilingualText=new MultilingualText();
            hrListValues.set_disclaimer(multilingualText);
        }
        hrListValues.get_disclaimer().setEnglish(hrListValues.getDescription());
        hrListValues.get_disclaimer().setFrench(hrListValues.getArDescription());
        //hrListValues.setIsEdit("KP");

//       HRListFields hrListFields= hrListFieldsRepository.findById(hrListValues.getFieldId()).orElse(null);
//        if(hrListFields!=null){
//            hrListFields.setNewFlag("KN");
//            hrListFieldsRepository.save(hrListFields);
//        }

        _entityManager.createQuery(HRListFields.UPDATE_DATE_BY_ID_QUERY)
                .setParameter("fieldId", hrListValues.getFieldId())
                .setParameter("lastModified", new Date())
                .executeUpdate();

//        HRListFields hrListFields= hrListFieldsRepository.findById(hrListValues.getFieldId()).orElse(null);
//        if(hrListFields!=null){
//            Date now = new Date();
//            hrListFields.setLastModified(new java.sql.Date(now.getTime()));
//            hrListFieldsRepository.save(hrListFields);
//        }

        return hrListValuesRepository.save(hrListValues);
        //hrListValuesRepository.save(hrListValues.get_disclaimer());
        //return hrListValues;
    }
}
