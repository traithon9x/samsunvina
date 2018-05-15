package com.samsun.samsunvina.apis;

import com.samsun.samsunvina.models.ReplyModel;
import com.samsun.samsunvina.services.interfaces.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@CrossOrigin
@RequestMapping(path = "api/patients")
public class StaffPatientApi {

    @Autowired
    PatientServices patientServices;

    @PostMapping(path = "/save-reply")
    @ResponseBody
    public ReplyModel saveReply(@RequestParam int id, @RequestBody ReplyModel replyModel) {
        patientServices.Savereply(replyModel, id);
        return replyModel;
    }


}
