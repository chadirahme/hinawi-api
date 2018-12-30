package com.hinawi.api.repository;

import com.hinawi.api.domains.Prospective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "prospective", path = "prospective")
public interface ProspectiveRepository extends JpaRepository<Prospective,Long> {

}
