package com.hinawi.api.repository;

import com.hinawi.api.domains.MobileAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//http://localhost:8091/rest/mobileAttendance
@RepositoryRestResource(collectionResourceRel = "mobileAttendance", path = "mobileAttendance")
public interface MobileAttendanceRepository extends JpaRepository<MobileAttendance,Long> {

    @Query("SELECT p FROM MobileAttendance p WHERE userId =?1 and checkoutTime is null order by attendId desc")
    public List<MobileAttendance> findLastVisit(int userId);

    @Query("SELECT p FROM MobileAttendance p WHERE userId =?1 order by attendId desc")
    List<MobileAttendance> findLastUserVisit(int userId);
}
