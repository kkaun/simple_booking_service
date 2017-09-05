package com.kirak.util.exception.model.booking;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 05.09.2017.
 */
public class BookingDeactivatedException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public BookingDeactivatedException(String msgCode, HttpStatus httpStatus) {
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
