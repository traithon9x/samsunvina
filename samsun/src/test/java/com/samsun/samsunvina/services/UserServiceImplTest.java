package com.samsun.samsunvina.services;

import com.samsun.samsunvina.SamsunvinaApplication;
import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.models.ListPatientModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SamsunvinaApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void getAllUser() {
        List<User> users = userService.findAll();
        System.out.println("Long");
    }

    @Test
    public void getAllPatientFromUsers() {
        List<Object[]> objects = userService.getAllPatientFromUsers();
        List<ListPatientModel> patientModels = new ArrayList<>();
        for (Object[] object: objects) {
            ListPatientModel patientModel = new ListPatientModel();
            patientModel.setEmployeeName(object[0].toString());
            patientModel.setPatientName(object[1].toString());
            patientModel.setPatientChart(object[2].toString());
            patientModels.add(patientModel);
        }
        System.out.println("Long");
    }

}