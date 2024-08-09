package com.flutter.alloffootball.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDate> {

    private int maxDays;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        maxDays = constraintAnnotation.maxDays();
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) return true;

        LocalDate now = LocalDate.now();
        LocalDate target = now.plusDays(maxDays);

        return !date.isBefore(target) && !date.isAfter(now);
    }
}
