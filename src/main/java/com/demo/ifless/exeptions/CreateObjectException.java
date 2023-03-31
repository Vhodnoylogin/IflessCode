package com.demo.ifless.exeptions;

public class CreateObjectException extends RuntimeException {
    public CreateObjectException() {
    }

    public CreateObjectException(String message) {
        super(message);
    }

    public CreateObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateObjectException(Throwable cause) {
        super(cause);
    }

    public CreateObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
