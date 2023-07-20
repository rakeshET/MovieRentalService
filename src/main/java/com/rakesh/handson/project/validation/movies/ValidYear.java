package com.rakesh.handson.project.validation.movies;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearValidator.class)
@Documented
public @interface ValidYear {

    String message() default "Release year should be in the past or present";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}