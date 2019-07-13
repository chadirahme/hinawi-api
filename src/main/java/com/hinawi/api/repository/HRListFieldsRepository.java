package com.hinawi.api.repository;

import com.hinawi.api.domains.HRListFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "hrListFields", path = "hrListFields")
public interface HRListFieldsRepository extends JpaRepository<HRListFields,Long> {
}
