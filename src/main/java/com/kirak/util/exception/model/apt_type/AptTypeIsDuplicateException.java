package com.kirak.util.exception.model.apt_type;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 18.09.2017.
 */
public class AptTypeIsDuplicateException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public AptTypeIsDuplicateException(String msgCode, HttpStatus httpStatus) {
        this.msgCode = msgCode;
        this.httpStatus = httpStatus;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
