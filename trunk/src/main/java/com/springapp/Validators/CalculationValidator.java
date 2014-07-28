package com.springapp.Validators;

import com.springapp.Calculation.DataInputForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("calculationValidator")
public class CalculationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DataInputForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "precision", "precision.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberOfThreads", "numberOfThreads.required");
//        ValidationUtils.
        DataInputForm dataInputForm = (DataInputForm) target;

        if ((!dataInputForm.getPrecision().isEmpty()
                && !dataInputForm.getPrecision().matches("^([0-9])+$"))) {
            errors.rejectValue("precision", "precision.match");
        }
        if ((!dataInputForm.getPrecision().isEmpty()
                && Integer.valueOf(dataInputForm.getPrecision()) <= 0)) {
            errors.rejectValue("precision", "precision.match");
        }

        if ((!dataInputForm.getNumberOfThreads().isEmpty()
                && !dataInputForm.getNumberOfThreads().matches("^([0-9])+$"))) {
            errors.rejectValue("numberOfThreads", "numberOfThreads.match");
        }
        if ((!dataInputForm.getNumberOfThreads().isEmpty()
                && Integer.valueOf(dataInputForm.getNumberOfThreads()) <= 0)) {
            errors.rejectValue("numberOfThreads", "numberOfThreads.match");
        }

    }
}

