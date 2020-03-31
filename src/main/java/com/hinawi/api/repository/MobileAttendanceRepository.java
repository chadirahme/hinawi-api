package com.hinawi.api.repository;

import com.hinawi.api.domains.MobileAttendance;
import com.hinawi.api.dto.ReportsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

//http://localhost:8091/rest/mobileAttendance
@RepositoryRestResource(collectionResourceRel = "mobileAttendance", path = "mobileAttendance")
public interface MobileAttendanceRepository extends JpaRepository<MobileAttendance,Long> {

    @Query("SELECT p FROM MobileAttendance p WHERE userId =?1 and checkoutTime is null order by attendId desc")
    List<MobileAttendance> findLastVisit(int userId);

    @Query("SELECT p FROM MobileAttendance p WHERE userId =?1 order by attendId desc")
    List<MobileAttendance> findLastUserVisit(int userId);

    @Query("SELECT p FROM MobileAttendance p WHERE month(checkintime) = ?1 order by attendId desc")
    List<MobileAttendance> findDailyVisit(int month);

    @Query("SELECT p FROM MobileAttendance p WHERE checkintime between :start and :end")
    List<MobileAttendance> findAbsenceReport(@Param("start") String start,@Param("end") String end);

    //Min(checkinTime) as 'checkinTime', Max(checkoutTime) as 'checkoutTime'

    @Query(
            value = "select userId,userName,CONVERT(nvarchar,Min(checkinTime), 120) as checkinTime," +
                    " CONVERT(nvarchar,Max(checkoutTime), 120) as checkoutTime" +
                    " FROM MobileAttendance p WHERE month(checkintime) = :month and month(checkouttime)= :month " +
                    " group by userid,username,DATEADD(dd, DATEDIFF(dd, 0, checkinTime), 0)" +
                    " ",
            nativeQuery = true)
    List<ReportsModel>  getAttendaceReport1(@Param("month") Integer month);

    //@Query(name="MobileAttendance.carkey",nativeQuery = true)
    @Query(nativeQuery=true)
    List<ReportsModel> getAttendanceReport(@Param("month") Integer month);

    @Query(nativeQuery=true)
    List<ReportsModel> getMonthlyAttendanceReport(@Param("month") Integer month);

    //@Query(nativeQuery=true)
    //List<ReportsModel> getAttendanceReportByReason1(@Param("month") Integer month , @Param("userId") Integer userId);

    @Query(nativeQuery=true)
    List<ReportsModel> getAttendanceReportByReason(@Param("userId") Integer userId,@Param("start") String start);

    @Query(nativeQuery=true)
    List<ReportsModel> getAbsenceReport(@Param("start") String start,@Param("end") String end);

    @Query(nativeQuery=true)
    List<ReportsModel> getAttendanceReportByMovement(@Param("userId") Integer userId, @Param("start") String start);

//    select userid,username,Min(checkinTime) as 'checkin',Max(checkoutTime)as 'checkout'  ,
//    DATEADD(dd, DATEDIFF(dd, 0, checkinTime), 0) as 'attDate'
//    from MobileAttendance
//    where month(checkintime)=2 and month(checkouttime)=2
//    group by userid,username,DATEADD(dd, DATEDIFF(dd, 0, checkinTime), 0)
}
