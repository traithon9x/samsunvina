package com.samsun.samsunvina.services;


import com.samsun.samsunvina.entities.Xray;
import com.samsun.samsunvina.repositories.XrayRepository;
import com.samsun.samsunvina.services.interfaces.XrayServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XrayServicesImpl implements XrayServices{
    @Autowired
    XrayRepository xrayRepository;

    public Iterable<Xray> findAll(){
        return xrayRepository.findAll();
    }

}
