package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(Enum name);

}
