package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.Image;
import com.samsun.samsunvina.enumerations.Status;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends CrudRepository<Image,Integer>{
    @Query(value = "select * from image where patient_id=?1" , nativeQuery = true)
    List<Image> findOneByPatient(Integer patient_id);


}
