package com.zupedu.monica.propostas.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {


    private final HttpStatus httpStatus;
    private final String reason;

    public ApiException(HttpStatus httpStatus, String reason) {
        super(reason);
        this.httpStatus = httpStatus;
        this.reason = reason;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getReason() {
        return reason;
    }

}
