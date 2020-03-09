package com.hinawi.api.repository;

import com.hinawi.api.domains.EmployeeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeesRepository extends JpaRepository<EmployeeMaster,Long> {

    @Query("SELECT p FROM EmployeeMaster p WHERE englishFullName is not null and active=?1")
    List<EmployeeMaster> findByActive(String active);
}
