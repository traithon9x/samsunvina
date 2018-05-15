package com.samsun.samsunvina.validators;

import com.samsun.samsunvina.constants.ErrorMessageConstants;
import com.samsun.samsunvina.entities.Township;
import com.samsun.samsunvina.models.LoginModel;
import com.samsun.samsunvina.models.RegisterModel;
import com.samsun.samsunvina.repositories.TownshipRepository;
import com.samsun.samsunvina.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

@Component
public class AccountValidator {

    public static final String wordOrNumberRegex = "[A-Za-z0-9]*";
    public static final String phoneNumberRegex = "[0-9+]*";
    public static final String emailRegex = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@([A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)+|\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])$";

    @Autowired
    private AccountService accountService;

    @Autowired
    private TownshipRepository townshipRepository;

    public void validateRegister(RegisterModel registerModel, Errors errors) {
        validateUsername(registerModel.getUsername(), errors);
        validatePassword(registerModel.getPassword(), registerModel.getPasswordConfirm(), errors);
        validateClinicName(registerModel.getClinicName(), errors);
        validateDentistName(registerModel.getDentistName(), errors);
        validatePhoneNumber(registerModel.getPhoneNumber(), errors);
        validateHardPhone(registerModel.getHardPhone(), errors);
        validateEmail(registerModel.getEmail(), errors);
        validateSpeciality(registerModel.getSpeciality(), errors);

        validateAddress(registerModel.getAddress(), errors);
        validateTownship(registerModel.getTownshipId(), errors);
    }

    private void validateUsername(String username, Errors errors) {
        boolean isUsernameError = false;
        if (username == null) {
            errors.rejectValue("username", "null.registerModel.username", ErrorMessageConstants.USERNAME_NULL);
            isUsernameError = true;
        }
        if (!isUsernameError && username.trim().length() < 5) {
            errors.rejectValue("username", "min.registerModel.username", ErrorMessageConstants.USERNAME_TOO_SHORT);
            isUsernameError = true;
        }
        if (!isUsernameError && username.trim().length() > 50) {
            errors.rejectValue("username", "max.registerModel.username", ErrorMessageConstants.USERNAME_TOO_LONG);
            isUsernameError = true;
        }
        if (!isUsernameError && !username.trim().matches(wordOrNumberRegex)) {
            errors.rejectValue("username", "invalid.registerModel.username", ErrorMessageConstants.USERNAME_INVALID);
            isUsernameError = true;
        }
        if (!isUsernameError && accountService.findByUsername(username).isPresent()) {
            errors.rejectValue("username", "exist.registerModel.username", ErrorMessageConstants.ACCOUNT_EXISTED);
        }
    }

    private void validatePassword(String password, String passwordConfirm, Errors errors) {
        boolean isPasswordError = false;
        if (password == null) {
            errors.rejectValue("password", "null.registerModel.password", ErrorMessageConstants.PASSWORD_NULL);
            isPasswordError = true;
        }
        if (!isPasswordError && password.trim().length() < 5) {
            errors.rejectValue("password", "min.registerModel.password", ErrorMessageConstants.PASSWORD_TOO_SHORT);
            isPasswordError = true;
        }
        if (!isPasswordError && password.trim().length() > 255) {
            errors.rejectValue("password", "max.registerModel.password", ErrorMessageConstants.PASSWORD_TOO_LONG);
        }
        if (!password.equals(passwordConfirm)) {
            errors.rejectValue("passwordConfirm", "notMatch.registerModel.passwordConfirm", ErrorMessageConstants.PASSWORD_CONFIRM_NOT_MATCH);
        }
    }

    private void validateClinicName(String clinicName, Errors errors) {
        boolean isClinicNameError = false;
        if (clinicName == null) {
            errors.rejectValue("clinicName", "null.registerModel.clinicName", ErrorMessageConstants.CLINIC_NAME_NULL);
            isClinicNameError = true;
        }
        if (!isClinicNameError && clinicName.trim().length() < 1) {
            errors.rejectValue("clinicName", "min.registerModel.clinicName", ErrorMessageConstants.CLINIC_NAME_TOO_SHORT);
            isClinicNameError = true;
        }
        if (!isClinicNameError && clinicName.trim().length() > 255) {
            errors.rejectValue("clinicName", "max.registerModel.clinicName", ErrorMessageConstants.CLINIC_NAME_TOO_LONG);
        }
    }

    private void validateDentistName(String dentistName, Errors errors) {
        boolean isDentistNameError = false;
        if (dentistName == null) {
            errors.rejectValue("dentistName", "null.registerModel.dentistName", ErrorMessageConstants.DENTIST_NAME_NULL);
            isDentistNameError = true;
        }
        if (!isDentistNameError && dentistName.trim().length() < 5) {
            errors.rejectValue("dentistName", "min.registerModel.dentistName", ErrorMessageConstants.DENTIST_NAME_TOO_SHORT);
            isDentistNameError = true;
        }
        if (!isDentistNameError && dentistName.trim().length() > 255) {
            errors.rejectValue("dentistName", "max.registerModel.dentistName", ErrorMessageConstants.DENTIST_NAME_TOO_LONG);
        }
    }

    private void validatePhoneNumber(String phoneNumber, Errors errors) {
        boolean isPhoneNumberError = false;
        if (!isPhoneNumberError && phoneNumber.trim().length() < 0 || phoneNumber.trim().length() > 15 || !phoneNumber.trim().matches(phoneNumberRegex)) {
            errors.rejectValue("phoneNumber", "invalid.registerModel.phoneNumber", ErrorMessageConstants.PHONE_NUMBER_INVALID);
        }
    }

    private void validateHardPhone(String hardPhone, Errors errors) {
        boolean isHardPhoneError = false;

        if (!isHardPhoneError && hardPhone.trim().length() < 0 || hardPhone.trim().length() > 18 || !hardPhone.trim().matches(phoneNumberRegex)) {
            errors.rejectValue("hardPhone", "invalid.registerModel.hardPhone", ErrorMessageConstants.HARD_PHONE_INVALID);
        }
    }

    private void validateEmail(String email, Errors errors) {
        boolean isEmailError = false;
        if (email == null) {
            errors.rejectValue("email", "null.registerModel.email", ErrorMessageConstants.EMAIL_NULL);
            isEmailError = true;
        }
        if (!isEmailError && email.trim().length() < 9) {
            errors.rejectValue("email", "min.registerModel.email", ErrorMessageConstants.EMAIL_TOO_SHORT);
            isEmailError = true;
        }
        if (!isEmailError && email.trim().length() > 255) {
            errors.rejectValue("email", "max.registerModel.email", ErrorMessageConstants.EMAIL_TOO_LONG);
            isEmailError = true;
        }
        if (!isEmailError && !email.trim().matches(emailRegex)) {
            errors.rejectValue("email", "invalid.registerModel.email", ErrorMessageConstants.EMAIL_INVALID);
        }
    }

    private void validateSpeciality(String speciality, Errors errors) {
        boolean isSpecialityError = false;

        if (!isSpecialityError && speciality.trim().length() < 0) {
            errors.rejectValue("speciality", "min.registerModel.speciality", ErrorMessageConstants.SPECIALITY_TOO_SHORT);
            isSpecialityError = true;
        }
        if (!isSpecialityError && speciality.trim().length() > 255) {
            errors.rejectValue("speciality", "max.registerModel.speciality", ErrorMessageConstants.SPECIALITY_TOO_LONG);
        }
    }




    private void validateAddress(String address, Errors errors) {
        boolean isAddressError = false;
        if (address == null) {
            errors.rejectValue("address", "null.registerModel.address", ErrorMessageConstants.ADDRESS_NULL);
            isAddressError = true;
        }
        if (!isAddressError && address.trim().length() < 5) {
            errors.rejectValue("address", "min.registerModel.address", ErrorMessageConstants.ADDRESS_TOO_SHORT);
            isAddressError = true;
        }
        if (!isAddressError && address.trim().length() > 255) {
            errors.rejectValue("address", "max.registerModel.address", ErrorMessageConstants.ADDRESS_TOO_LONG);
        }
    }

    private void validateTownship(Integer townshipId, Errors errors) {
        boolean isTownshipError = false;
        if (townshipId == null) {
            errors.rejectValue("townshipId", "null.registerModel.townshipId", ErrorMessageConstants.TOWNSHIP_NULL);
            isTownshipError = true;
        }
        if (!isTownshipError && !townshipRepository.findById(townshipId).isPresent()) {
            errors.rejectValue("townshipId", "notFound.registerModel.townshipId", ErrorMessageConstants.TOWNSHIP_NULL);
        }

    }

    public void validateLogin(LoginModel loginModel, Errors errors) {
        validateUsernameLogin(loginModel.getUsername(), errors);
        validatePasswordLogin(loginModel.getPassword(), errors);
    }

    private void validateUsernameLogin(String username, Errors errors) {
        boolean isUsernameError = false;
        if (username == null) {
            errors.rejectValue("username", "null.loginModel.username", ErrorMessageConstants.USERNAME_NULL);
            isUsernameError = true;
        }
        if (!isUsernameError && username.trim().length() < 5) {
            errors.rejectValue("username", "min.loginModel.username", ErrorMessageConstants.USERNAME_TOO_SHORT);
            isUsernameError = true;
        }
        if (!isUsernameError && username.trim().length() > 50) {
            errors.rejectValue("username", "max.loginModel.username", ErrorMessageConstants.USERNAME_TOO_LONG);
            isUsernameError = true;
        }
        if (!isUsernameError && !username.trim().matches(wordOrNumberRegex)) {
            errors.rejectValue("username", "invalid.loginModel.username", ErrorMessageConstants.USERNAME_INVALID);
        }
    }

    private void validatePasswordLogin(String password, Errors errors) {
        boolean isPasswordError = false;
        if (password == null) {
            errors.rejectValue("password", "null.loginModel.password", ErrorMessageConstants.PASSWORD_NULL);
            isPasswordError = true;
        }
        if (!isPasswordError && password.trim().length() < 5) {
            errors.rejectValue("password", "min.loginModel.password", ErrorMessageConstants.PASSWORD_TOO_SHORT);
            isPasswordError = true;
        }
        if (!isPasswordError && password.trim().length() > 255) {
            errors.rejectValue("password", "max.loginModel.password", ErrorMessageConstants.PASSWORD_TOO_LONG);
        }
    }
}
