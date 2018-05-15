package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.Rc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RcRepository extends CrudRepository<Rc,Integer>{
}
