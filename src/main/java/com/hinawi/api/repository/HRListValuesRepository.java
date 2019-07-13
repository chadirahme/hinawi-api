package com.hinawi.api.repository;


import com.hinawi.api.domains.HRListValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "hrListValues", path = "hrListValues")
public interface HRListValuesRepository extends JpaRepository<HRListValues,Long> {

    List<HRListValues> findByFieldIdOrderByFieldName(Long fieldId);

    @Query("SELECT coalesce(max(ch.id), 0) FROM HRListValues ch")
    Long getMaxId();

}
