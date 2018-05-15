package com.samsun.samsunvina.controllers;

import java.util.List;

import com.samsun.samsunvina.entities.Patient;
import com.samsun.samsunvina.models.EmailModel;
import com.samsun.samsunvina.services.interfaces.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.samsun.samsunvina.services.interfaces.EmailService;
import com.samsun.samsunvina.services.interfaces.ImagesServices;
import com.samsun.samsunvina.entities.Image;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    ImagesServices imageService;

    @Autowired
    PatientServices patientServices;

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN') || hasRole('ROLE_CLINIC')")
    @PostMapping("/admin/sendEmail/{id_pa}")
    public String sendEmail(@ModelAttribute EmailModel emailModel, @PathVariable("id_pa") Integer id_pa) {
        Patient patient = patientServices.findOne(id_pa);
        String to = emailModel.getEmail();
        String subject = "Bệnh nhân "+patient.getName();
        String content = "Dear bác sĩ !!!\n" +
                         "Dưới đây là kết quả chụp X-quang của bệnh nhân "+patient.getName()+"\nXin chân thành cảm ơn!";
        List<Image> images = imageService.findOneByPatient(id_pa);

       Runnable task = () -> emailService.sendMessageWithAttachmentImages(to, subject, content, images);
        new Thread(task).start();

        //emailService.sendMessageWithAttachmentImages(to, subject, content, images);
        return "redirect:/admin/patient/" + id_pa + "/images2";

    }
}
