package com.samsun.samsunvina.validators;

import com.samsun.samsunvina.constants.ErrorMessageConstants;
import com.samsun.samsunvina.models.AddPatientModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class AddPatientValidator {

    public static final String wordOrNumberRegex = "[A-Za-z0-9]*";
    public static final String phoneNumberRegex = "[0-9+]*";
    public static final String emailRegex = "^[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@([A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)+|\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])$";


    public void ValidateAddPatient(AddPatientModel addPatientModel, Errors errors) {
        validateName(addPatientModel.getName(), errors);
    }

    private void validateName(String name, Errors errors) {
        boolean isNameError = false;

        if(name == null) {
            errors.rejectValue("name","null.AddPatientModel.name", ErrorMessageConstants.NAME_PATIEMT_NULL);
            isNameError = true;
        }

        if (!isNameError && name.trim().length() < 5) {
            errors.rejectValue("name", "min.AddPatientModel.name", ErrorMessageConstants.NAME_PATIEMT_SHORT);
            isNameError = true;
        }

        if (!isNameError && name.trim().length() > 255) {
            errors.rejectValue("name", "max.AddPatientModel.name", ErrorMessageConstants.NAME_PATIEMT_LONG);
        }
    }
}
