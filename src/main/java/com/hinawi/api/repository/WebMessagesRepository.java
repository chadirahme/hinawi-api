package com.hinawi.api.repository;

import com.hinawi.api.domains.WebMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "messages", path = "messages")
public interface WebMessagesRepository extends JpaRepository<WebMessages,Long> {

}
