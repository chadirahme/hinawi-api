package com.hinawi.api.repository;

import com.hinawi.api.domains.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//http://localhost:8091/rest/customers
@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomersRepository extends JpaRepository<Customers,Long> {

    @Query("SELECT p FROM Customers p WHERE balance is not null and balance<>0")
    public List<Customers> findBalance();

    List<Customers> findByName(String name);
}
