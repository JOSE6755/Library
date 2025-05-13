package com.library.library.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Response<T> {

    private String message;
    private int code;
    private boolean success;
    private T data;
    private List<FieldError> fieldErrors;
    private PageMetaInfo pageMetaInfo;


    public Response(String message, int code, boolean success, List<FieldError> errors) {
        this.message = message;
        this.code = code;
        this.success = success;
        this.data = null;
        this.fieldErrors = errors;
        this.pageMetaInfo = null;
    }

    public Response(String message, int code, boolean success, T data, PageMetaInfo pageMetaInfo) {
        this.message = message;
        this.code = code;
        this.success = success;
        this.data = data;
        this.fieldErrors = null;
        this.pageMetaInfo = pageMetaInfo;
    }
}
