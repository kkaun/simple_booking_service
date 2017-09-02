package com.kirak.util.exception.model.apt_type;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 02.09.2017.
 */
public class AptTypeHasApartmentsException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public AptTypeHasApartmentsException(String msgCode, HttpStatus httpStatus) {
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
