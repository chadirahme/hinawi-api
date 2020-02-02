package com.hinawi.api.repository;

import com.hinawi.api.domains.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "quotation", path = "quotation")
public interface QuotationRepository extends JpaRepository<Quotation,Long> {

    @Query(
            value = "SELECT count(*) as total, month(TxnDate) as paymonth" +
                    " FROM Quotation p WHERE year(TxnDate) = ?" +
                    " group by year(TxnDate),month(TxnDate)" +
                    " order by month(TxnDate)",
            nativeQuery = true)
    List<PaymentsStatistics> findAllQuotationByYear(Integer year);
}