package com.samsun.samsunvina.controllers;


import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.entities.Patient;
import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.models.AddPatientModel;
import com.samsun.samsunvina.models.CommentModel;
import com.samsun.samsunvina.models.EmailModel;
import com.samsun.samsunvina.models.ReplyModel;
import com.samsun.samsunvina.repositories.ContactRepository;
import com.samsun.samsunvina.repositories.PatientRepository;
import com.samsun.samsunvina.services.interfaces.*;
import com.samsun.samsunvina.validators.AddPatientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StaffPatientController {
    @Autowired
    PatientServices patientServices;

    @Autowired
    ImagesServices imagesServices;

    @Autowired
    AddPatientValidator addPatientValidator;

    @Autowired
    UserService userService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    XrayServices xrayServices;

    @Autowired
    AccountService accountService;

    @Autowired
    PatientRepository patientRepository;

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/staff/index")
    public String dashboard(Model model, Authentication authentication) {
        Account account = accountService.findByUsername(authentication.getName()).get();

        model.addAttribute("countallclinic",userService.countAllClinicfromRc_id(account.getUser().getRc().getId()));
        model.addAttribute("getpatienttoday",patientRepository.findPatientByToday(account.getUser().getTownship().getCity().getId()));
        model.addAttribute("getpatientyesterday",patientRepository.findPatientByYesterday(account.getUser().getTownship().getCity().getId()));
        model.addAttribute("getpatientweed",patientRepository.findPatientByWeed(account.getUser().getTownship().getCity().getName()));
        model.addAttribute("getpatient3month",patientRepository.findPatientBy3month(account.getUser().getTownship().getCity().getId()));
        model.addAttribute("getpatientmonth",patientRepository.findPatientByMonth(account.getUser().getTownship().getCity().getName()));
        model.addAttribute("finallcontact",contactRepository.findAll());
        return "admin/staff-index"; }


    //danh sach benh nhan
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/staff/list")
    public String getListP(Model model,Authentication authentication){
        Account account = accountService.findByUsername(authentication.getName()).get();
        model.addAttribute("patients",patientServices.findAllByRcid(account.getUser().getRc().getId()));

        return "admin/staff_list";
    }

    //danh sach comment tren tung benh nhan cua bac sy
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/staff/comment")
    public String getListComment(Model model,Authentication authentication){
        Account account = accountService.findByUsername(authentication.getName()).get();

        model.addAttribute("comment",patientServices.findAllByRcid(account.getUser().getRc().getId()));
        model.addAttribute("reply",new ReplyModel());
        return "admin/staff_comment";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/staff/list_clinic")
    public String getListClinic(Model model,Authentication authentication){
        Account account = accountService.findByUsername(authentication.getName()).get();
        model.addAttribute("clinic",userService.findAllClinicbyRcid(account.getUser().getRc().getId()));
        return "/admin/staff_list_clinic";
    }


    // danh sach hinh anh cua benh nhan
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/patient/{id}/images2")
    public String getimagesfrompatient2(@PathVariable("id") Integer id, Model model){
        model.addAttribute("image",imagesServices.findOneByPatient(id));
        model.addAttribute("patient",patientServices.findOne(id));
        model.addAttribute("reply", new ReplyModel());
        model.addAttribute("getmail",new EmailModel());
        return "admin/patient_images2";
    }


    //luu tra loi benh nhan
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/patient/{id}/savereply")
    public String UpdateReplyCommentImagesPatient(@ModelAttribute ReplyModel replyModel,
                                                  @PathVariable("id") Integer id,RedirectAttributes redirect){
        patientServices.Savereply(replyModel,id);
        redirect.addFlashAttribute("replysuccess", "Trả lời thành công!");

        return "redirect:/admin/staff/comment";
    }


    //form them benh nhan
    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/clinic/form/staff")
    public String CreatePatientForm(Model model) {
        model.addAttribute("addPatientModel", new AddPatientModel());
        model.addAttribute("xraylist",xrayServices.findAll());
        return "admin/patient_form_staff";
    }

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/clinic/save/staff")
    public String savepatientBystaff(@ModelAttribute AddPatientModel addPatientModel,
                       Model model,
                       BindingResult bindingResult,
                       RedirectAttributes redirect){
        addPatientValidator.ValidateAddPatient(addPatientModel, bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("xraylist", xrayServices.findAll());
            return "admin/patient_form_staff";
        }

        patientServices.savePatientByStaff(addPatientModel);
        redirect.addFlashAttribute("success", "Thêm bệnh nhân thành công!");
        return "redirect:/admin/staff/list";
    }

}
