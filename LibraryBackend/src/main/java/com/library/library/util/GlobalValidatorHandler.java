package com.library.library.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class GlobalValidatorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public Response<HashMap<String, String>> requestParamValidator(ConstraintViolationException ex) {
        List<FieldError> errors = new ArrayList<>();

        ex.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(new FieldError(constraintViolation.getPropertyPath().toString().split("\\.")[1], constraintViolation.getMessage()));
        });
        return new Response<>("Error in either pathparams or request params", HttpStatus.BAD_REQUEST.value(), false, errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<HashMap<String, String>> bodyValidator(MethodArgumentNotValidException ex) {
        List<FieldError> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new FieldError(error.getField(), error.getDefaultMessage()));
        });
        return new Response<>("Error in either pathparams or request params", HttpStatus.BAD_REQUEST.value(), false, errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Response<HashMap<String, String>> validateParamsType(MethodArgumentTypeMismatchException ex) {
        String fieldName = ex.getName();
        String fieldType = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "expected value";
        List<FieldError> errors = new ArrayList<>();
        errors.add(new FieldError(fieldName, String.format("%s has to be %s", fieldName, fieldType)));
        return new Response<>("Invalid parameter type", HttpStatus.BAD_REQUEST.value(), false, errors);


    }
}
