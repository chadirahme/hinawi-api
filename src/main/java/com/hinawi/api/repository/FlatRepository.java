package com.hinawi.api.repository;


import com.hinawi.api.domains.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "flat", path = "flat")
public interface FlatRepository extends JpaRepository<Flat,Long> {
}
