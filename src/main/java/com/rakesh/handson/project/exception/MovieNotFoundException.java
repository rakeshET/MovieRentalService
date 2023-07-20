package com.rakesh.handson.project.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(int id) {
        super("Could not find movie with id: " + id);
    }
}
