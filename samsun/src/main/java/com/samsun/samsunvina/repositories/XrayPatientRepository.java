package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.XrayPatient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface XrayPatientRepository extends CrudRepository<XrayPatient,Integer>{
    @Query(value = "SELECT xp.xray_id FROM patient p inner join xray_patient xp on xp.patient_id=p.id where p.id=?1",nativeQuery = true)
    List<Integer> getXrayOnPatient(int id);
}
