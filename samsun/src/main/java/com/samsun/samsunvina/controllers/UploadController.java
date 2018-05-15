package com.samsun.samsunvina.controllers;

import com.samsun.samsunvina.entities.Image;
import com.samsun.samsunvina.services.interfaces.ImagesServices;
import com.samsun.samsunvina.services.interfaces.PatientServices;
import com.samsun.samsunvina.validators.UploadFileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.samsun.samsunvina.validators.UploadFileValidator.regexTypeImages;

@Controller
public class UploadController {
    @Autowired
    PatientServices patientServices;
    @Autowired
    ImagesServices imagesServices;

    @Autowired
    UploadFileValidator uploadFileValidator;

    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/{id}/upload")
    public String getformupload(@PathVariable("id") Integer id, Model model){
        model.addAttribute("patient",patientServices.findOne(id));
        File file = new File(uploadingdir);
        model.addAttribute("files",file.listFiles());
        return "/admin/upload";

    }



    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @PostMapping("/admin/{id}/upload")
    public String uploadingPost(@PathVariable("id") Integer id, @RequestParam("ct") List<MultipartFile> ct,
                                @RequestParam("cel") MultipartFile cel,
                                @RequestParam("pano") MultipartFile pano,
                                RedirectAttributes redirectAttributes) {

        boolean isCtValid = !ct.stream().anyMatch(item ->
                !item.getOriginalFilename().matches(regexTypeImages));
        boolean isCelValid = !cel.getOriginalFilename().isEmpty() ;
        boolean isPanoValid = !pano.getOriginalFilename().isEmpty() ;

        if(!isCtValid && !isCelValid && !isPanoValid){
            redirectAttributes.addFlashAttribute("fileimpty","Vui lòng chọn một ảnh !");
            return "redirect:/admin/"+id+"/upload";

        }

        if (isCtValid) {
            for (MultipartFile multipartFile:ct
                 ) {
                    if(!multipartFile.getOriginalFilename().matches(regexTypeImages)){
                        redirectAttributes.addFlashAttribute("filetypenotsp","Định dạng file không hợp lệ !");
                        return "redirect:/admin/"+id+"/upload";
                    }
            }

        }
        if(isCelValid){
            if(!cel.getOriginalFilename().matches(regexTypeImages)){
                redirectAttributes.addFlashAttribute("filetypenotsp","Định dạng file không hợp lệ !");
                return "redirect:/admin/"+id+"/upload";
            }
        }
        if(isPanoValid){
            if(!pano.getOriginalFilename().matches(regexTypeImages)){
                redirectAttributes.addFlashAttribute("filetypenotsp","Định dạng file không hợp lệ !");
                return "redirect:/admin/"+id+"/upload";
            }
        }




        imagesServices.save(id,ct,cel,pano);
        redirectAttributes.addFlashAttribute("uploadsuccess","Upload ảnh thành công!");
        return "redirect:/admin/staff/list";
    }



    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/patient/image/delete/{id_pa}/{id}")
    public String deleteImage(@PathVariable("id_pa") Integer id_pa,@PathVariable("id") Integer id) {
        imagesServices.deleteById(id);
        return "redirect:/admin/patient/" + id_pa+ "/images2";
    }



    @PreAuthorize("hasRole('ROLE_STAFF') || hasRole('ROLE_ADMIN')")
    @RequestMapping("/images/edit")
    @ResponseBody
    public String editImages(@RequestParam("id") int id, @RequestParam("name") String name) throws Exception {
        Image img = imagesServices.changeImageName(id, name);
        if (img != null) {
            return "{\"status\":\"ok\",\"newName\":\"" + img.getNameWithoutExtension() + "\"}";
        }
        return "{\"status\":\"false\"}";
    }
}



