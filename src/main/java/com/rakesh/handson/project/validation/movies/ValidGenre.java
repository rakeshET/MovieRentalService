package com.rakesh.handson.project.validation.movies;

import com.rakesh.handson.project.validation.movies.GenreValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenreValidator.class)
@Documented
public @interface ValidGenre {

    String message() default "Genre provided is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}