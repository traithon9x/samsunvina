package com.samsun.samsunvina.services;

import com.samsun.samsunvina.entities.Patient;
import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.entities.Xray;
import com.samsun.samsunvina.entities.XrayPatient;
import com.samsun.samsunvina.enumerations.Status;
import com.samsun.samsunvina.models.AddPatientModel;
import com.samsun.samsunvina.models.CommentModel;
import com.samsun.samsunvina.models.ReplyModel;
import com.samsun.samsunvina.repositories.*;
import com.samsun.samsunvina.services.interfaces.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.DateUtils;

import java.net.Authenticator;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PatientServicesImpl implements PatientServices{
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    TownshipRepository townShipRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    XrayPatientRepository xrayPatientRepository;

    @Autowired
    XrayRepository xrayRepository;



    @Override
    public List<Patient> findpatientofclinic(String username){
        return patientRepository.findPatientOfClinic(username);
    }

    @Override
    public List<Patient> findAllByRcid(int rc_id){

        return patientRepository.findAllPatientByStaffOfRc_id(rc_id);
    }

    @Override
    public String save(AddPatientModel addPatientModel,String username){
        Patient patient = new Patient();
        patient.setName(addPatientModel.getName());
        patient.setBirthday(addPatientModel.getBirthday());
        patient.setGender(addPatientModel.getGender());
        patient.setPhone(addPatientModel.getPhone());
        patient.setEmail(addPatientModel.getEmail());
        patient.setAddress(addPatientModel.getAddress());
        patient.setTeethPosition(addPatientModel.getTeethposition());
        patient.setImplant(addPatientModel.getImplant());
        patient.setOtherRequired(addPatientModel.getOtherrequired());
        patient.setStatus(Status.WAITING);
        String dentistry_no = patientRepository.getDentistry_No(username);
        int patient_no=patientRepository.getcoutpatientinclinic(username)+1;
        String chartno = dentistry_no+"_"+String.format("%04d",patient_no);
        patient.setChartNo(chartno);
        patient.setUser(userRepository.finduser(username));
        Patient patientnew= patientRepository.save(patient);
        if(patientnew==null){
            return "FAILED";
        }

        List<XrayPatient> xrayPatients ;

        Xray xray= new Xray();
        List<Integer> xrayid = addPatientModel.getXrayid();
        for(int i=0;i<xrayid.size();i++){
            XrayPatient xrayPatient = new XrayPatient();
            xray.setId(xrayid.get(i));
            xrayPatient.setXray(xray);
            xrayPatient.setPatient(patientnew);
            xrayPatient.setStatus(Status.ACTIVE);
            xrayPatientRepository.save(xrayPatient);
        }
        return "SUCCESS";
    }

    public Patient findOne(int id){
        return patientRepository.findOne(id);
    }

    @Override
    public AddPatientModel findById(Integer id) {
        Patient patient = patientRepository.findOne(id);

        List<Integer> xrayid = new ArrayList<>();
        AddPatientModel addPatientModel = new AddPatientModel();
        addPatientModel.setName(patient.getName());
        addPatientModel.setBirthday(patient.getBirthday());
        addPatientModel.setGender(patient.getGender());
        addPatientModel.setPhone(patient.getPhone());

        addPatientModel.setEmail(patient.getEmail());
        addPatientModel.setAddress(patient.getAddress());
        addPatientModel.setImplant(patient.getImplant());
        addPatientModel.setTeethposition(patient.getTeethPosition());
        addPatientModel.setOtherrequired(patient.getOtherRequired());


        for (XrayPatient xrayPatient: patient.getXrayPatients()) {
            xrayid.add(xrayPatient.getXray().getId());
        }

        addPatientModel.setXrayid(xrayid);
        return addPatientModel;


    }
    @Override
    public String Savecomment(CommentModel commentModel,int id){
        Patient patient = patientRepository.findOne(id);
        patient.setComment(commentModel.getComment());
        patientRepository.save(patient);
        return "SUCCESS";
    }

    @Override
    public String Savereply(ReplyModel replyModel, Integer id){
        Patient patient = patientRepository.findOne(id);
        patient.setReply(replyModel.getReply());
        patientRepository.save(patient);
        return "SUCCESS";
    }



    @Override
    public String Editpatient(AddPatientModel addPatientModel,Integer id){
        Patient patient = patientRepository.findOne(id);
        patient.setName(addPatientModel.getName());
        patient.setBirthday(addPatientModel.getBirthday());
        patient.setGender(addPatientModel.getGender());
        patient.setPhone(addPatientModel.getPhone());
        patient.setEmail(addPatientModel.getEmail());
        patient.setAddress(addPatientModel.getAddress());
        patient.setTeethPosition(addPatientModel.getTeethposition());
        patient.setImplant(addPatientModel.getImplant());
        patient.setOtherRequired(addPatientModel.getOtherrequired());

        Patient patientnew= patientRepository.save(patient);
        if(patientnew==null){
            return "FAILED";
        }
        List<XrayPatient> xrayPatients =patient.getXrayPatients();
        xrayPatientRepository.delete(xrayPatients);
        Xray xray= new Xray();
        List<Integer> xrayid = addPatientModel.getXrayid();
        for(int i=0;i<xrayid.size();i++){
            XrayPatient xrayPatient = new XrayPatient();
            xray.setId(xrayid.get(i));
            xrayPatient.setXray(xray);
            xrayPatient.setPatient(patientnew);
            xrayPatient.setStatus(Status.ACTIVE);
            xrayPatientRepository.save(xrayPatient);
        }
        return null;
    }

    @Override
    public String savePatientByStaff(AddPatientModel addPatientModel){
        Patient patient = new Patient();
        patient.setName(addPatientModel.getName());
        patient.setBirthday(addPatientModel.getBirthday());
        patient.setGender(addPatientModel.getGender());
        patient.setPhone(addPatientModel.getPhone());
        patient.setEmail(addPatientModel.getEmail());
        patient.setAddress(addPatientModel.getAddress());
        patient.setTeethPosition(addPatientModel.getTeethposition());
        patient.setImplant(addPatientModel.getImplant());
        patient.setOtherRequired(addPatientModel.getOtherrequired());
        patient.setStatus(Status.WAITING);

        patient.setChartNo(addPatientModel.getChartno().trim());
        patient.setUser(userRepository.findUserByDentistryNo(addPatientModel.getChartno().trim().substring(0,7)));
        Patient patientnew= patientRepository.save(patient);
        if(patientnew==null){
            return "FAILED";
        }

        List<XrayPatient> xrayPatients ;

        Xray xray= new Xray();
        List<Integer> xrayid = addPatientModel.getXrayid();
        for(int i=0;i<xrayid.size();i++){
            XrayPatient xrayPatient = new XrayPatient();
            xray.setId(xrayid.get(i));
            xrayPatient.setXray(xray);
            xrayPatient.setPatient(patientnew);
            xrayPatient.setStatus(Status.ACTIVE);
            xrayPatientRepository.save(xrayPatient);
        }
        return "SUCCESS";
    }

}
