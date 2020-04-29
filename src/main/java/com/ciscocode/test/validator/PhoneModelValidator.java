package com.ciscocode.test.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PhoneModelValidator implements ConstraintValidator<PhoneModel, String> {

    List<String> models = Arrays.asList("IPHONE", "ANDROID", "DESK_PHONE", "SOFT_PHONE");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return models.contains(value);
    }
}