package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.entities.City;

import java.util.List;

public interface CityService {
    List<City> getAllCityOrderByName();
}
