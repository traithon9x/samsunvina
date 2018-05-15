package com.samsun.samsunvina.services;

import com.samsun.samsunvina.SamsunvinaApplication;
import com.samsun.samsunvina.entities.City;
import com.samsun.samsunvina.services.interfaces.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SamsunvinaApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class CityServiceImplTest {

    @Autowired
    private CityService cityService;

    @Test
    public void getAllCity() throws Exception {
        List<City> cities = cityService.getAllCityOrderByName();
        System.out.println("LONG");
    }

}