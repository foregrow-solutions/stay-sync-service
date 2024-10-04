package com.loonds.StaySync.common.exception;

public class CustomExceptions {

    public static class ResourceNotFoundException extends ApplicationException {
        public ResourceNotFoundException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public ResourceNotFoundException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }

    public static class DuplicateResourceException extends ApplicationException {
        public DuplicateResourceException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public DuplicateResourceException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }

    public static class InvalidCredentialsException extends ApplicationException {
        public InvalidCredentialsException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public InvalidCredentialsException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }

    public static class UnauthorizedException extends ApplicationException {
        public UnauthorizedException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public UnauthorizedException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }

    public static class BadRequestException extends ApplicationException {
        public BadRequestException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public BadRequestException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }

    public static class JobApplicationException extends ApplicationException {
        public JobApplicationException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public JobApplicationException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }

    public static class JwtTokenExpiredException extends ApplicationException {
        public JwtTokenExpiredException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public JwtTokenExpiredException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }
}