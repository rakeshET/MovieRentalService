package com.rakesh.handson.project.validation.rentals;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class MovieIdValidator implements ConstraintValidator<ValidMovieId, String> {

    @Override
    public boolean isValid(String movieId, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.hasText(movieId) && !movieId.trim().isEmpty() && movieId.length() >= 2;
    }
}
