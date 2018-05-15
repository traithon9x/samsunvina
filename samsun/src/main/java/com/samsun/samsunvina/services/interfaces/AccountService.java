package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.models.PasswordModel;
import com.samsun.samsunvina.models.RegisterModel;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AccountService {

//    public void save(Account account);

    Optional<Account> findByUsername(String username);

    Optional<Account> createNewAccount(Account account);

    Optional<Account> registerAccount(RegisterModel registerModel);

    Optional<Account> createNewAccountFormRegisterModel(RegisterModel registerModel);

    String getRoleByAuthentication(Authentication authentication);

    Account findBytoken(String token);

    String EditPasswordSave(PasswordModel passwordModel, String name);
}
