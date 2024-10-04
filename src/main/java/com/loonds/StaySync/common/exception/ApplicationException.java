package com.loonds.StaySync.common.exception;

public class ApplicationException extends RuntimeException {
    private final ErrorCode errorCode;
    private final boolean loggable;

    public ApplicationException(ErrorCode errorCode, String message) {
        this(errorCode, message, false);
    }

    public ApplicationException(ErrorCode errorCode, String message, Throwable cause) {
        this(errorCode, message, cause, false);
    }

    public ApplicationException(ErrorCode errorCode, String message, boolean loggable) {
        super(message);
        this.errorCode = errorCode;
        this.loggable = loggable;
    }

    public ApplicationException(ErrorCode errorCode, String message, Throwable cause, boolean loggable) {
        super(message, cause);
        this.errorCode = errorCode;
        this.loggable = loggable;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public boolean isLoggable() {
        return loggable;
    }
}
