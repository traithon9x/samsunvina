package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.Township;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownshipRepository extends CrudRepository<Township, Integer> {

    @Query(value = "select t.acronym from township t inner join user u on t.id = u.township_id where u.id=?1",nativeQuery = true)
    String getacronym_clinic(int id_user);

    List<Township> findAllByCityId(Integer cityId);

    Optional<Township> findById(Integer townshipId);

}
