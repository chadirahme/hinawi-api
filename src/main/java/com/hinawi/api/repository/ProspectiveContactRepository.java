package com.hinawi.api.repository;

import com.hinawi.api.domains.ProspectiveCotact;
import com.hinawi.api.domains.ProspectiveCotactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "prospectivecontact", path = "prospectivecontact")
public interface ProspectiveContactRepository  extends JpaRepository<ProspectiveCotact,ProspectiveCotactId> {

    @Query("SELECT pc FROM ProspectiveCotact pc where recNo=:recNo order by defaultCont desc")
    List<ProspectiveCotact> findByMeProspectiveCotactIdRecNo(@Param("recNo") long recNo); //AndProspectiveCotactIdLineNo

    //@Query("SELECT pc FROM ProspectiveCotact pc where recNo=:recNo")
    //List<ProspectiveCotact> findAllContacts(Example<ProspectiveCotact> song);

}
