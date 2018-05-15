//package com.samsun.samsunvina.services;
//
//import com.samsun.samsunvina.entities.User;
//import com.samsun.samsunvina.repositories.UserRepository;
//import com.samsun.samsunvina.services.interfaces.UserServices;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServicesImpl implements UserServices{
//    @Autowired
//    UserRepository userRepository;
//
//    User user ;
//
//
//    @Override
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public List<Object[]> findAllPatientFromUsers(){
//        return userRepository.findAllPatientFromUsers();
//    }
//
//    @Override
//    public List<User> findAllClinicbyRcid(int rc_id) {
//        return userRepository.findAllUserFormRc_id(rc_id);
//    }
//}
