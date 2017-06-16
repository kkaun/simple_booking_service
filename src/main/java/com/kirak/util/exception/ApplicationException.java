package com.kirak.util.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 16.06.2017.
 */
public class ApplicationException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public ApplicationException(String msgCode, HttpStatus httpStatus) {
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
