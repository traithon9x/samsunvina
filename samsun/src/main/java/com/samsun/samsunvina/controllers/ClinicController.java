package com.samsun.samsunvina.controllers;

import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ClinicController {

    private final UserService userService;

    @Autowired
    public ClinicController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_CLINIC')")
    @GetMapping(path = "/profile")
    public String getProfile(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "clinic/profile";
    }

}
