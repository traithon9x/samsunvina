package com.samsun.samsunvina.services;

import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(account.getRole().getName().toString());

        return new User(account.getUsername(), account.getPassword(), Arrays.asList(grantedAuthority));
    }
}
