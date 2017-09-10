package com.kirak.util.exception.model.apt_type;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 10.09.2017.
 */
public class AptTypeIsDemoException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public AptTypeIsDemoException(String msgCode, HttpStatus httpStatus) {
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
