package com.kirak.util.exception.model.region;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 12.09.2017.
 */
public class RegionDuplNameException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public RegionDuplNameException(String msgCode, HttpStatus httpStatus) {
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
