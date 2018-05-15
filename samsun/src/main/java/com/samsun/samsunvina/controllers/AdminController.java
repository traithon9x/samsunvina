package com.samsun.samsunvina.controllers;

import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.models.PasswordModel;
import com.samsun.samsunvina.models.RegisterModel;
import com.samsun.samsunvina.repositories.RcRepository;
import com.samsun.samsunvina.repositories.TownshipRepository;
import com.samsun.samsunvina.services.interfaces.AccountService;
import com.samsun.samsunvina.services.interfaces.CityService;
import com.samsun.samsunvina.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminController {


    @Autowired
    CityService cityService;

    @Autowired
    RcRepository rcRepository;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    TownshipRepository townshipRepository;

    private Authentication authentication;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/admin/profile")
    public String getProfile(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("user", user);
        return "admin/profile";
    }
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @GetMapping(path = "/admin/{clinicname}/profile")
    public String getProfileclinic(@PathVariable("clinicname") String clinicname, Model model) {
        model.addAttribute("cities", cityService.getAllCityOrderByName());
        model.addAttribute("township",townshipRepository.findAll());
        User user = userService.findByUsername(clinicname);
        model.addAttribute("RegisterModel", user);
            model.addAttribute("rc",rcRepository.findAll());
            model.addAttribute("clinicname",clinicname);
        return "clinic/profile";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @PostMapping(path = "/admin/{clinicname}/profile/edit")
    public String Editprofile(@PathVariable("clinicname") String clinicname,
                              @ModelAttribute RegisterModel RegisterModel ,
                              RedirectAttributes redirectAttributes){
        userService.saveEdit(RegisterModel,clinicname);
        redirectAttributes.addFlashAttribute("updatesuccess", "Sửa Thông tin thành công!");
        return "redirect:/admin/"+clinicname+"/profile";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @GetMapping(path = "/admin/{clinicname}/editpassword")
    public String EditPassword(Model model,
                               @PathVariable("clinicname") String clinicname) {
        model.addAttribute("PasswordModel",new PasswordModel());
        model.addAttribute("clinicname",clinicname);
        return "clinic/changepassword";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @PostMapping(path = "/admin/{clinicname}/editpasswordsuccess")
    public String EditPasswordSave(@PathVariable("clinicname") String clinicname,
                                   @ModelAttribute PasswordModel passwordModel,
                                   RedirectAttributes redirectAttributes){
            String status = accountService.EditPasswordSave(passwordModel, clinicname);

            if(status.equals("thay đổi mật khẩu thành công !")){
                redirectAttributes.addFlashAttribute("status", status);
                return "redirect:/admin/"+clinicname+"/profile";
            }else {
                redirectAttributes.addFlashAttribute("status", status);
                return "redirect:/admin/"+clinicname+"/editpassword";
        }
    }

}
