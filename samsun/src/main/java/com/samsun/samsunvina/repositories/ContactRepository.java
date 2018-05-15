package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Integer>{
}
