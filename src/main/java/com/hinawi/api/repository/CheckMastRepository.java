package com.hinawi.api.repository;

import com.hinawi.api.domains.Checkmast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "checkmast", path = "checkmast")
public interface CheckMastRepository extends JpaRepository<Checkmast,Long> {

    @Query(
            value = "SELECT round(sum(amount),2) as total,month(pvDate) as paymonth" +
                    " FROM Checkmast p WHERE year(pvDate) = ?" +
                    " group by year(pvDate),month(pvDate)" +
                    " order by month(pvDate)",
            nativeQuery = true)
    List<PaymentsStatistics> findAllPettyCashByYear(Integer year);
}
