package com.samsun.samsunvina.controllers;

import com.samsun.samsunvina.models.ContactModel;
import com.samsun.samsunvina.services.interfaces.ContactService;
import com.samsun.samsunvina.services.interfaces.PatientServices;
import com.samsun.samsunvina.services.interfaces.UserService;
import com.samsun.samsunvina.validators.ContactValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomePageController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    PatientServices patientServices;

    @Autowired
    ContactService contactService;

    @Autowired
    ContactValidator contactValidator;

    @GetMapping("")
    public String homePage(Model model) {
        model.addAttribute("contactModel",new ContactModel());
        return "index";
    }

    @PostMapping("/savecontact")
    public String SaveContact(@ModelAttribute ContactModel contactModel,Model model,BindingResult bindingResult,RedirectAttributes redirect){
        contactValidator.validatecontact(contactModel,bindingResult);


        if (bindingResult.hasErrors()) {
            return "index";
        }
        contactService.SaveContact(contactModel);
        redirect.addFlashAttribute("sendcontactsuccess","Ý kiến góp ý của bạn đã được gửi thành công");
        return "redirect:/";
    }




}
