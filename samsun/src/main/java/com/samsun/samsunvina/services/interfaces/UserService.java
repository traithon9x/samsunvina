package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.models.RegisterModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    List<Object[]> getAllPatientFromUsers();

    Optional<User> createNewUserFromRegisterModelAndAccount(RegisterModel registerModel, Account account);

    Optional<User> createNewUser(User user);

    User findByUsername(String name);

    int countAllClinicfromRc_id(int rc_id);

    List<User> findAllClinicbyRcid(int rc_id);

    User saveEdit(RegisterModel registerModel, String clinicname);

    User findByID(Integer iduser);
}
