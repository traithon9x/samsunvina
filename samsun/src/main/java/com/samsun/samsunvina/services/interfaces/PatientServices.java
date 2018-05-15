package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.entities.Patient;
import com.samsun.samsunvina.models.AddPatientModel;
import com.samsun.samsunvina.models.CommentModel;
import com.samsun.samsunvina.models.ReplyModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;

import java.net.Authenticator;
import java.util.Date;
import java.util.List;

public interface PatientServices {
    List<Patient> findpatientofclinic(String username);

    String save(AddPatientModel addPatientModel,String username);

    Patient findOne(int id);

    AddPatientModel findById(Integer id);

    List<Patient> findAllByRcid(int rc_id);

    String Savecomment(CommentModel commentModel,int id);

    String Editpatient(AddPatientModel addPatientModel,Integer id);


    String Savereply(ReplyModel replyModel, Integer id);

    String savePatientByStaff(AddPatientModel addPatientModel);
}
