package com.kirak.util.exception.model.user;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 08.09.2017.
 */
public class UserIsAnonymousException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public UserIsAnonymousException(String msgCode, HttpStatus httpStatus) {
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
