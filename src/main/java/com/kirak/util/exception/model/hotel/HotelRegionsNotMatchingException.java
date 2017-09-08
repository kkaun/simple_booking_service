package com.kirak.util.exception.model.hotel;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 08.09.2017.
 */
public class HotelRegionsNotMatchingException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public HotelRegionsNotMatchingException(String msgCode, HttpStatus httpStatus) {
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