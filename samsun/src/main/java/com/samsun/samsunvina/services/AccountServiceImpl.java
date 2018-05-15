package com.samsun.samsunvina.services;


import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.entities.Role;
import com.samsun.samsunvina.entities.Township;
import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.enumerations.RoleName;
import com.samsun.samsunvina.enumerations.Status;
import com.samsun.samsunvina.models.PasswordModel;
import com.samsun.samsunvina.models.RegisterModel;
import com.samsun.samsunvina.repositories.AccountRepository;
import com.samsun.samsunvina.repositories.RoleRepository;
import com.samsun.samsunvina.services.interfaces.AccountService;
import com.samsun.samsunvina.services.interfaces.TownshipService;
import com.samsun.samsunvina.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TownshipService townshipService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailServiceImpl emailService;

    HttpServletRequest request;


    @Override
    public Optional<Account> findByUsername(String username) {
        return Optional.ofNullable(accountRepository.findByUsername(username));
    }



    @Override
    public Optional<Account> registerAccount(RegisterModel registerModel) {
        Optional<Account> accountOpt = createNewAccountFormRegisterModel(registerModel);
        userService.createNewUserFromRegisterModelAndAccount(registerModel, accountOpt.get());
        return accountOpt;
    }

    @Override
    public Optional<Account> createNewAccountFormRegisterModel(RegisterModel registerModel) {

        Account account = new Account();
        account.setUsername(registerModel.getUsername().trim());
        account.setPassword(bCryptPasswordEncoder.encode(registerModel.getPassword().trim()));
        account.setRole(roleRepository.findByName(RoleName.ROLE_CLINIC));
        account.setStatus(Status.NOTACTIVE);

        String token = UUID.randomUUID().toString();
        //verify mail
        account.setToken(token);
        String subject ="SamSun RC account activation";
        String text="Hi "+registerModel.getUsername().trim()+"," +
                "\n your username: "+registerModel.getUsername()+
                "\n your Password : " +registerModel.getPassword()+
                "\nTo confirm your e-mail address, please click the link below:\nhttp://localhost:8080/authorize?token="+token+
                "Please change your Password after login ."+
                "\n Thank you!\n";
        Runnable task = () -> emailService.sendSimpleMessage(registerModel.getEmail(),subject,text);
        new Thread(task).start();



        return createNewAccount(account);
    }


    @Override
    public Optional<Account> createNewAccount(Account account) {
        return Optional.ofNullable(accountRepository.save(account));
    }


//    @Override
//    public void save(Account account) {
//        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
////        account.setRole(roleRepository.findByRoleName(RoleName.ROLE_ADMIN.toString()));
//        accountRepository.save(account);
//    }



    @Override
    public String getRoleByAuthentication(Authentication authentication) {
        String role = new String();
        for (GrantedAuthority authority: authentication.getAuthorities()) {
            role = authority.getAuthority();
        }
        return role;
    }

    @Override
    public Account findBytoken(String token){

        return accountRepository.findByToken(token);
    }

    @Override
    public String EditPasswordSave(PasswordModel passwordModel, String name){
        String status="";
        Account account = accountRepository.findByUsername(name);
        if(bCryptPasswordEncoder.matches(passwordModel.getPassword().trim(), account.getPassword())){
            if(passwordModel.getNewpassword().trim().equals(passwordModel.getConfimnewPassword().trim())){
                account.setPassword(bCryptPasswordEncoder.encode(passwordModel.getNewpassword().trim()));
                accountRepository.save(account);
                status = "thay đổi mật khẩu thành công !";
            }else{
                status= "Mật khẩu mới không khớp !";
            }
        }else {
            status = "Mật khẩu hiện tại không đúng !";
        }
        return status;
    }
}
