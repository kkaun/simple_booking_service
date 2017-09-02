package com.kirak.util.exception.model.hotel;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 02.09.2017.
 */
public class HotelHasBookingsException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public HotelHasBookingsException(String msgCode, HttpStatus httpStatus) {
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
