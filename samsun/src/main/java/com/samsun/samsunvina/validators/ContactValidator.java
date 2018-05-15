package com.samsun.samsunvina.validators;

import com.samsun.samsunvina.constants.ErrorMessageConstants;
import com.samsun.samsunvina.models.ContactModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ContactValidator {
    public static final String wordOrNumberRegex = "[A-Za-z0-9]*";
    public static final String phoneNumberRegex = "[0-9+]*";
    public static final String emailRegex = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@([A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)+|\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])$";
    public static final String sqlinjection = "/\\w*((\\%27)|(\\'))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix";


    public void validatecontact(ContactModel contactModel,Errors errors){
        validateName(contactModel.getName(),errors);
        validateSubject(contactModel.getSubject(),errors);
        validateComment(contactModel.getWrite_comment(),errors);
        validateEmail(contactModel.getMail(),errors);
        validatePhoneNumber(contactModel.getPhone(),errors);
    }

    private void validateName(String name, Errors errors) {
        boolean isDentistNameError = false;
        if (name == null) {
            errors.rejectValue("name", "null.contacModel.dentistName", ErrorMessageConstants.DENTIST_NAME_NULL);
            isDentistNameError = true;
        }
        if (!isDentistNameError && name.trim().length() < 5) {
            errors.rejectValue("name", "min.contacModel.dentistName", ErrorMessageConstants.DENTIST_NAME_TOO_SHORT);
            isDentistNameError = true;
        }
        if (!isDentistNameError && name.trim().length() > 100) {
            errors.rejectValue("Name", "max.contacModel.dentistName", ErrorMessageConstants.DENTIST_NAME_TOO_LONG);
        }
        if (!isDentistNameError && name.trim().matches(sqlinjection)) {
            errors.rejectValue("Name", "max.contacModel.dentistName", ErrorMessageConstants.NAME_INVALID);
        }
    }

    private void validateSubject(String subject, Errors errors) {
        boolean isDentistNameError = false;
        if (subject == null) {
            errors.rejectValue("subject", "null.contacModel.dentistName", ErrorMessageConstants.SUBJECT_NULL);
            isDentistNameError = true;
        }
        if (!isDentistNameError && subject.trim().length() < 3) {
            errors.rejectValue("subject", "min.contacModel.dentistName", ErrorMessageConstants.SUBJECT_TOO_SHORT);
            isDentistNameError = true;
        }
        if (!isDentistNameError && subject.trim().length() > 255) {
            errors.rejectValue("subject", "max.contacModel.dentistName", ErrorMessageConstants.SUBJECT_TOO_LONG);
        }
        if (!isDentistNameError && subject.trim().matches(sqlinjection)) {
            errors.rejectValue("subject", "max.contacModel.dentistName", ErrorMessageConstants.SUBJECT_INVALID);
        }
    }


    private void validateEmail(String mail, Errors errors) {
        boolean isEmailError = false;
        if (mail == null) {
            errors.rejectValue("mail", "null.contacModel.email", ErrorMessageConstants.EMAIL_NULL);
            isEmailError = true;
        }
        if (!isEmailError && mail.trim().length() < 9) {
            errors.rejectValue("mail", "min.contacModel.email", ErrorMessageConstants.EMAIL_TOO_SHORT);
            isEmailError = true;
        }
        if (!isEmailError && mail.trim().length() > 255) {
            errors.rejectValue("mail", "max.contacModel.email", ErrorMessageConstants.EMAIL_TOO_LONG);
            isEmailError = true;
        }
        if (!isEmailError && !mail.trim().matches(emailRegex)) {
            errors.rejectValue("mail", "invalid.contacModel.email", ErrorMessageConstants.EMAIL_INVALID);
        }
    }

    private void validatePhoneNumber(String phone, Errors errors) {
        boolean isPhoneNumberError = false;
        if (!isPhoneNumberError && phone.trim().length() < 0 || phone.trim().length() > 18 || !phone.trim().matches(phoneNumberRegex)) {
            errors.rejectValue("phone", "invalid.contacModel.phoneNumber", ErrorMessageConstants.PHONE_NUMBER_INVALID);
        }
    }

    private void validateComment(String write_comment, Errors errors) {
        boolean isDentistNameError = false;

        if (!isDentistNameError && write_comment.trim().matches(sqlinjection)) {
            errors.rejectValue("write_comment", "max.contacModel.dentistName", ErrorMessageConstants.COMMENT_INVALID);
        }
    }

}
