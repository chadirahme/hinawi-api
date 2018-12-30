package com.hinawi.api.repository;


import com.hinawi.api.domains.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentsRepository extends JpaRepository<Students,Long> {
}
