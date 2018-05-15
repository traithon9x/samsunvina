package com.samsun.samsunvina.services;

import com.samsun.samsunvina.entities.City;
import com.samsun.samsunvina.repositories.CityRepository;
import com.samsun.samsunvina.services.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCityOrderByName() {
        return cityRepository.findAllByOrderByNameAsc();
    }

}
