package com.samsun.samsunvina.controllers;

import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.enumerations.Status;
import com.samsun.samsunvina.models.LoginModel;
import com.samsun.samsunvina.models.RegisterModel;
import com.samsun.samsunvina.repositories.AccountRepository;
import com.samsun.samsunvina.repositories.RcRepository;
import com.samsun.samsunvina.services.interfaces.*;
import com.samsun.samsunvina.validators.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Controller
public class AccountController {

    @Autowired
    private AccountValidator accountValidator;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CityService cityService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RcRepository rcRepository;

    @Autowired
    UserService userService;

    @GetMapping(path = "admin/staff/register")
    public String registration(RegisterModel registerModel, Model model) {
        model.addAttribute("registerModel", registerModel);
        model.addAttribute("cities", cityService.getAllCityOrderByName());
        model.addAttribute("rc", rcRepository.findAll());

        return "/admin/register";
    }

    @PostMapping(path = "/admin/staff/register/success")
    public String registration(@ModelAttribute RegisterModel registerModel, BindingResult bindingResult, Model model,RedirectAttributes redirect) {

        accountValidator.validateRegister(registerModel, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", cityService.getAllCityOrderByName());
            model.addAttribute("rc", rcRepository.findAll());
            return "/admin/register";
        }
        Optional<Account> accountOpt = accountService.registerAccount(registerModel);

        //securityService.autoLogin(accountOpt.get().getUsername(), registerModel.getPasswordConfirm());
        redirect.addFlashAttribute("registersucces", "Đăng ký thành công !");
        return "redirect:/admin/staff/list_clinic";
    }

    @GetMapping(path = "/login")
    public String login(LoginModel loginModel, Model model) {
        model.addAttribute("loginModel", loginModel);

        return "login";
    }

    @GetMapping(path = "/login-process")
    public String loginProcess(Authentication authentication, RedirectAttributes redirectAttributes,Model model) {
        Account account = accountService.findByUsername(authentication.getName()).get();
        account.getUser().getTownship().getCity().getName();
        if(account.getStatus() == Status.NOTACTIVE){
            redirectAttributes.addFlashAttribute("notactive","Đăng nhập thất bại ! bạn chưa kích hoạt Email .");
            return "redirect:/login";
        }

        String role = accountService.getRoleByAuthentication(authentication);
        if ("ROLE_ADMIN".equals(role)) {
            return "redirect:/admin";
        }
        if ("ROLE_STAFF".equals(role)) {
            return "redirect:/admin/staff/index";

        }
        if ("ROLE_CLINIC".equals(role)) {
            return "redirect:/admin/clinic/index";
        }
        return "redirect:/info";
    }

    @GetMapping(path = "/info")
    public @ResponseBody String userInfo(Authentication authentication) {
        String msg = "";
        for (GrantedAuthority authority: authentication.getAuthorities()) {
            String role = authority.getAuthority();
            msg += "Hello " + authentication.getName() + ", You have " + role;
        }
        return msg;
    }


    @GetMapping(path = "/authorize")
            public String AuthorizeVetify(@RequestParam("token") String token,ModelAndView modelAndView){
                Account account = accountService.findBytoken(token);
                if(account ==null){

                }else {
                    account.setStatus(Status.ACTIVE);
                    modelAndView.addObject("success", "Bạn đã xác thực Email thành công !");
                    accountRepository.save(account);

                }
             return "authorize";
            }

}
