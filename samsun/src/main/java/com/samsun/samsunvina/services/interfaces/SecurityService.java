package com.samsun.samsunvina.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityService {

    public String findLoggedInUserName();

    public void autoLogin(String username, String password);
}
