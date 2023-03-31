package com.demo.ifless.exeptions;

public class NoDefaultObjectException extends RuntimeException {
    public NoDefaultObjectException() {
    }

    public NoDefaultObjectException(String message) {
        super(message);
    }

    public NoDefaultObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDefaultObjectException(Throwable cause) {
        super(cause);
    }

    public NoDefaultObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
