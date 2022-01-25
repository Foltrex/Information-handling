package com.epam.infohandling.exception;

import java.io.IOException;

public class DataException extends IOException {
    public DataException() {
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(Throwable cause) {
        super(cause);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
