package com.hinawi.api.repository;

import com.hinawi.api.domains.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//http://localhost:8091/rest/customers
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomersRepository extends JpaRepository<Customers,Long> {
}
