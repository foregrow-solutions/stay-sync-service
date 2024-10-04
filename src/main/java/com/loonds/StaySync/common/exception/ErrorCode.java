package com.loonds.StaySync.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    BAD_REQUEST(4000, HttpStatus.BAD_REQUEST, "Bad request"),
    UNAUTHORIZED(4001, HttpStatus.UNAUTHORIZED, "Unauthorized"),
    INVALID_CREDENTIALS(4002, HttpStatus.BAD_REQUEST, "Invalid credentials"),

    NOT_FOUND(4004, HttpStatus.NOT_FOUND, "Not found given record"),
    JWT_TOKEN_EXPIRED(4003, HttpStatus.UNAUTHORIZED, "JWT token expired");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(final Integer code, HttpStatus httpStatus, final String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
