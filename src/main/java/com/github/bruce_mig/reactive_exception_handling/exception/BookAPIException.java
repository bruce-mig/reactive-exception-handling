package com.github.bruce_mig.reactive_exception_handling.exception;

public class BookAPIException extends RuntimeException {
    public BookAPIException(String message) {
        super(message);
    }
}
