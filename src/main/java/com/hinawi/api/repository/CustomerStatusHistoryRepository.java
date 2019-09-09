package com.hinawi.api.repository;


import com.hinawi.api.domains.CustomerStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "customerStatusHistory", path = "customerStatusHistory")
public interface CustomerStatusHistoryRepository extends JpaRepository<CustomerStatusHistory,Long> {

    @Query("SELECT p FROM CustomerStatusHistory p WHERE custKey =?1 and type='P' and statusDescription<>'' order by actionDate desc")
    List<CustomerStatusHistory> findProspectiveStatusHistory(int custKey);
}






