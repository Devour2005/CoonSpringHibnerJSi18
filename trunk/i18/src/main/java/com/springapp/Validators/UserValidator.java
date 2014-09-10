package com.springapp.Validators;

import com.springapp.mvc.RegisterForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("userValidator")
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "passwordConfirm.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailConfirm", "email.confirm.required");
        RegisterForm userRegisterReg = (RegisterForm) target;


        //Register Validation
        if ((!userRegisterReg.getLogin().isEmpty()) && (!userRegisterReg.getLogin().matches("^([a-zA-Z0-9_-])+$"))) {
            errors.rejectValue("login", "login.match");
        }

        if ((!userRegisterReg.getName().isEmpty()) && (!userRegisterReg.getName().matches("^([a-zA-Z])+$"))) {
            errors.rejectValue("name", "name.match");
        }
        if (!userRegisterReg.getPassword().equalsIgnoreCase(userRegisterReg.getPasswordConfirm())) {
            errors.rejectValue("passwordConfirm", "password.again");
        }
        if ((!userRegisterReg.getEmail().isEmpty()) &&
                (!userRegisterReg.getEmail().matches("^([a-zA-Z0-9_\\.\\-+])+" +
                        "@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$"))) {
            errors.rejectValue("email", "email.match");
        }
        if ((!userRegisterReg.getEmail().equalsIgnoreCase(userRegisterReg.getEmailConfirm())) &&
                (!userRegisterReg.getEmailConfirm().isEmpty())) {
            errors.rejectValue("emailConfirm", "email.again");
        }
    }
}



