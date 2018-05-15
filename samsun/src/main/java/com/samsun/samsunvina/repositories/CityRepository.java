package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    List<City> findAllByOrderByNameAsc();

}
