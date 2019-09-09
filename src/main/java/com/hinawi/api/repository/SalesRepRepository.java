package com.hinawi.api.repository;


import com.hinawi.api.domains.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "salesRep", path = "salesRep")
public interface SalesRepRepository extends JpaRepository<SalesRep,Long> {
}
