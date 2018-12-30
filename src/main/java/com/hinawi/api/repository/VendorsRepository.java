package com.hinawi.api.repository;

import com.hinawi.api.domains.Vendors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by chadirahme on 12/22/18.
 */
@RepositoryRestResource(collectionResourceRel = "vendors", path = "vendors")
public interface VendorsRepository extends JpaRepository<Vendors,Long> {

    @Query("SELECT p FROM Vendors p WHERE balance is not null and balance<>0")
    public List<Vendors> findBalance();
}


