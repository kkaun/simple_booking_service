package com.kirak.util.exception.model.cross_model;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 11.09.2017.
 */
public class DemoEntityModificationException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public DemoEntityModificationException(String msgCode, HttpStatus httpStatus) {
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
