package br.unitins.pibiti.application;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

public class Result {
    
    private String message;
    private Boolean success;

    public Result(String message) {
       
        this.success = true;
        this.message = message;
    }

    public Result(Set<? extends ConstraintViolation<?>> violations) {
       
        this.success = false;
        this.message = violations.stream()
             .map(cv -> cv.getMessage())
             .collect(Collectors.joining(", "));
    }

    public Result(String message, boolean success) {
       
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
