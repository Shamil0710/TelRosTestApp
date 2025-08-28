package com.example.demo.utils;

import com.example.demo.utils.annotations.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Служебный класс необходимый для валидации телефонного номера
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Pattern pattern = Pattern.compile("^\\d{8}$");
        Matcher matcher = pattern.matcher(value);

        return matcher.matches();
    }
}
