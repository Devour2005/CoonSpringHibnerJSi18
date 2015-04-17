package com.springapp.validators;


import com.springapp.forms.UserForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("updateValidator")
public class UpdateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        UserForm userRegisterUpdt = (UserForm) target;

        //Update Validation
        if ((!userRegisterUpdt.getLogin().isEmpty()) &&
                (!userRegisterUpdt.getLogin().matches("^([a-zA-Z0-9_-])+$"))) {
            errors.rejectValue("login", "login.match");
        }

        if ((!userRegisterUpdt.getName().isEmpty()) &&
                (!userRegisterUpdt.getName().matches("^([a-zA-Z])+$"))) {
            errors.rejectValue("name", "name.match");
        }
        if ((!userRegisterUpdt.getEmail().isEmpty()) &&
                (!userRegisterUpdt.getEmail().matches("^([a-zA-Z0-9_\\.\\-+])+" +
                        "@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$"))) {
            errors.rejectValue("email", "email.match");
        }
    }
}
