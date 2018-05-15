package com.samsun.samsunvina.services;

import com.samsun.samsunvina.entities.Account;
import com.samsun.samsunvina.entities.Rc;
import com.samsun.samsunvina.entities.Township;
import com.samsun.samsunvina.entities.User;
import com.samsun.samsunvina.enumerations.Status;
import com.samsun.samsunvina.models.RegisterModel;
import com.samsun.samsunvina.repositories.AccountRepository;
import com.samsun.samsunvina.repositories.RcRepository;
import com.samsun.samsunvina.repositories.TownshipRepository;
import com.samsun.samsunvina.repositories.UserRepository;
import com.samsun.samsunvina.services.interfaces.TownshipService;
import com.samsun.samsunvina.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TownshipService townshipService;
    private final AccountRepository accountRepository;
    private final RcRepository rcRepository;
    private final TownshipRepository townshipRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, TownshipService townshipService,
                           AccountRepository accountRepository,
                           RcRepository rcRepository
                           ,TownshipRepository townshipRepository) {
        this.userRepository = userRepository;
        this.townshipService = townshipService;
        this.accountRepository = accountRepository;
        this.rcRepository = rcRepository;
        this.townshipRepository = townshipRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<Object[]> getAllPatientFromUsers() {
        return userRepository.findAllPatientFromUsers();
    }

    @Override
    public Optional<User> createNewUserFromRegisterModelAndAccount(RegisterModel registerModel, Account account) {
        Optional<Township> townshipOpt = townshipService.getTownshipByTownshipId(registerModel.getTownshipId());
        Township township = townshipOpt.get();
        User user = new User();
        user.setAccount(account);
        user.setFullName(registerModel.getDentistName().trim());
        user.setClinicName(registerModel.getClinicName().trim());
        user.setDentistName(registerModel.getDentistName().trim());
        user.setAddress(registerModel.getAddress().trim());
        user.setHardPhone(registerModel.getHardPhone().trim());
        user.setPhoneNumber(registerModel.getPhoneNumber().trim());
        user.setEmail(registerModel.getEmail().trim());
        user.setEmployeeQuantity(registerModel.getEmployeeQuantity());
        user.setDentalChairQuantity(registerModel.getDentalChairQuantity());
        user.setSpeciality(registerModel.getSpeciality().trim());
        user.setTownship(township);
        user.setGender(registerModel.getGender());
        Rc rc = rcRepository.findOne(registerModel.getRc_id());
        user.setRc(rc);
//        user.setRc();

        int countuserontownship = userRepository.getcoutclinic_township(township.getAcronym())+1;
        user.setDentistryNo(township.getAcronym() + "_" + String.format("%04d", countuserontownship));
        user.setStatus(Status.ACTIVE);
        return createNewUser(user);
    }

    @Override
    public Optional<User> createNewUser(User user) {
        return Optional.ofNullable(userRepository.save(user));
    }

    @Override
    public User findByUsername(String username) {
        Integer accountId = accountRepository.findIdByUsername(username);
        return userRepository.findByAccountId(accountId);
    }

    @Override
    public User findByID(Integer iduser){
        return userRepository.findOne(iduser);
    }

    @Override
    public int countAllClinicfromRc_id(int rc_id){
        return userRepository.countAllClinicFromRc_id(rc_id);
    }

    @Override
    public List<User> findAllClinicbyRcid(int rc_id){
        return userRepository.findAllUserFormRc_id(rc_id);
    }

    @Override
    public User saveEdit(RegisterModel registerModel, String clinicname){
        User user = userRepository.finduser(clinicname);
        user.setFullName(registerModel.getDentistName().trim());
        user.setGender(registerModel.getGender());
        user.setClinicName(registerModel.getClinicName().trim());
        user.setDentistName(registerModel.getDentistName().trim());
        user.setAddress(registerModel.getAddress().trim());
        user.setHardPhone(registerModel.getHardPhone().trim());
        user.setPhoneNumber(registerModel.getPhoneNumber().trim());
        user.setEmail(registerModel.getEmail().trim());
        user.setDentalChairQuantity(registerModel.getDentalChairQuantity());
        user.setEmployeeQuantity(registerModel.getEmployeeQuantity());
        user.setSpeciality(registerModel.getSpeciality().trim());
        Township township = townshipRepository.findOne(registerModel.getTownshipId());
        user.setTownship(township);
        return userRepository.save(user);
    }
}

