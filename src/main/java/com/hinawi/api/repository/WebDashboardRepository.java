package com.hinawi.api.repository;


import com.hinawi.api.domains.WebDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "webDashboard", path = "webDashboard")
public interface WebDashboardRepository extends JpaRepository<WebDashboard,Long> {

     List<WebDashboard> findByUserIdOrderByDashOrder(Integer userId);
     List<WebDashboard> findByUserIdAndDashName(Integer userId,String dashName);
     WebDashboard findByDashName(String dashName);

}
