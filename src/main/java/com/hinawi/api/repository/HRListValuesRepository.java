package com.hinawi.api.repository;


import com.hinawi.api.domains.HRListValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "hrListValues", path = "hrListValues")
public interface HRListValuesRepository extends JpaRepository<HRListValues,Long> {

    @Query("SELECT hr FROM HRListValues hr where fieldId=:fieldId order by priorityId")
    List<HRListValues> findByFieldIdOrderByDescription(@Param("fieldId") long fieldId);

    @Query("SELECT hr FROM HRListValues hr where fieldId=:fieldId and subId=:subId order by description")
    List<HRListValues> getSubList(@Param("fieldId") long fieldId,@Param("subId") Integer subId);

    @Query("SELECT coalesce(max(ch.id), 0) FROM HRListValues ch")
    Long getMaxId();

}
