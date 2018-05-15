package com.samsun.samsunvina.controllers;

import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.models.AddPatientModel;
import com.samsun.samsunvina.models.CommentModel;
import com.samsun.samsunvina.models.EmailModel;
import com.samsun.samsunvina.repositories.PatientRepository;
import com.samsun.samsunvina.services.interfaces.AccountService;
import com.samsun.samsunvina.services.interfaces.ImagesServices;
import com.samsun.samsunvina.services.interfaces.PatientServices;
import com.samsun.samsunvina.services.interfaces.XrayServices;
import com.samsun.samsunvina.validators.AddPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ClinicPatientController {

    @Autowired
    PatientServices patientServices;

    @Autowired
    ImagesServices imagesServices;

    @Autowired
    XrayServices xrayServices;

    @Autowired
    AccountService accountService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    AddPatientValidator addPatientValidator;

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @GetMapping("/admin/clinic/index")
    public String ClinicIndex(Model model,Authentication authentication){
        model.addAttribute("listpatient",patientServices.findpatientofclinic(authentication.getName()));
        model.addAttribute("countmonth",patientRepository.countpatientbymonth(authentication.getName()));
        model.addAttribute("count3month",patientRepository.countpatientbyclinic90day(authentication.getName()));
        return "admin/clinic_index";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @GetMapping("/admin/clinic/list")
    public String getPatientList(Model model,Authentication authentication) {
        model.addAttribute("listpatient",patientServices.findpatientofclinic(authentication.getName()));
        return "admin/clinic_list";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @GetMapping("/admin/patient/{id}/images")
    public String getimagesfrompatient(@PathVariable("id") Integer id, Model model){
        model.addAttribute("image",imagesServices.findOneByPatient(id));
        model.addAttribute("patient",patientServices.findOne(id));
        model.addAttribute("comment", new CommentModel());
        model.addAttribute("getmail",new EmailModel());
        return "admin/patient_images";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @PostMapping("/admin/patient/{id}/savecomment")
    public  String Savecomment(@ModelAttribute CommentModel commentModel,
                               @PathVariable("id") Integer id,
                               RedirectAttributes redirect){
        patientServices.Savecomment(commentModel,id);
        redirect.addFlashAttribute("success", "Gửi ý kiến thành công!");

        return "redirect:/admin/patient/{id}/images";
    }
    //form them benh nhan
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @GetMapping("/admin/clinic/form")
    public String getCreatePatientForm(Model model) {
        model.addAttribute("addPatientModel", new AddPatientModel());
        model.addAttribute("xraylist",xrayServices.findAll());
        return "admin/patient_form";
    }
    //them benh nhan thanh cong
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @PostMapping("/admin/clinic/save")
    public String save(@ModelAttribute AddPatientModel addPatientModel,
                       Model model,
                       BindingResult bindingResult,
                       Authentication authentication,
                       RedirectAttributes redirect){
        addPatientValidator.ValidateAddPatient(addPatientModel, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("xraylist", xrayServices.findAll());
            return "admin/patient_form";
        }

        patientServices.save(addPatientModel, authentication.getName());
        redirect.addFlashAttribute("success", "Thêm bệnh nhân thành công!");
        return "redirect:/admin/clinic/list";
    }
    //sua benh nhan
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @GetMapping("/admin/patient/{id}/edit")
    public String edit(@PathVariable("id") Integer id,Model model){
        model.addAttribute("xraylist",xrayServices.findAll());
        model.addAttribute("addpatientmodel", patientServices.findById(id));
        model.addAttribute("idpatient",id);
        return "admin/patient_form_edit";
    }

    //sua thanh cong
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @PostMapping("/admin/patient/{id}/editpatient")
    public String edit(@Valid AddPatientModel editpatient,@PathVariable("id") Integer id,
                       RedirectAttributes redirect,Authentication authentication){
        patientServices.Editpatient(editpatient,id);
        redirect.addFlashAttribute("updatesuccess", "Sửa bệnh nhân thành công!");
        Account account = accountService.findByUsername(authentication.getName()).get();

        if(account.getRole().getName().toString()=="ROLE_STAFF"){
            return "redirect:/admin/staff/list";
        }else {
            return "redirect:/admin/clinic/list";
        }
    }

}
