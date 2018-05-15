package com.samsun.samsunvina.repositories;

import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.models.LoginModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findByUsername(String username);

    @Query("SELECT id FROM Account a where a.username = ?1")
    Integer findIdByUsername(String username);

//    @Query(value = "SELECT * FROM Account a where a.token = ?1",nativeQuery = true)
    Account findByToken(String token);
}
