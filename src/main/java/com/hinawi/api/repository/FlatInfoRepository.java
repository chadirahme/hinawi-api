package com.hinawi.api.repository;

import com.hinawi.api.domains.FlatInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "flatInfo", path = "flatInfo")
public interface FlatInfoRepository  extends JpaRepository<FlatInfo,Long> {

//    select count(*) , FlatList.Status from Flat
//    Right JOIN Class As FlatList ON Flat.RecNo     = FlatList.Class_Key
//    WHERE Class_Type='F'
//    GROUP BY FlatList.Status

    @Query(
            value = "SELECT count(*)  as paymonth,FlatList.Status as status" +
                    " FROM Flat Right JOIN Class As FlatList ON Flat.RecNo = FlatList.Class_Key " +
                    " WHERE Class_Type='F' " +
                    " group by FlatList.Status" +
                    " ",
            nativeQuery = true)
    List<PaymentsStatistics> findAllFlats();
}
