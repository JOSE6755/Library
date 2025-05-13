package com.library.library.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseGenerator {

    public static <T> ResponseEntity<Response<T>> generateResponseEntity(
            String message, HttpStatus code, boolean success, T data, List<FieldError> fieldErrors, PageMetaInfo pageMetaInfo) {
        Response<T> response = new Response<>(message, code.value(), success, data, fieldErrors, pageMetaInfo);
        return new ResponseEntity<>(response, code);
    }

}
