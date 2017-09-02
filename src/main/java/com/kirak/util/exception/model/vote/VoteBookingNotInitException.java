package com.kirak.util.exception.model.vote;

import org.springframework.http.HttpStatus;

/**
 * Created by Kir on 02.09.2017.
 */
public class VoteBookingNotInitException extends RuntimeException {

    private String msgCode;
    private HttpStatus httpStatus;

    public VoteBookingNotInitException(String msgCode, HttpStatus httpStatus) {
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
