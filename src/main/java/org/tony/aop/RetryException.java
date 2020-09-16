package org.tony.aop;

public class RetryException extends RuntimeException {
    public RetryException(String message) {
        super(message);
    }
}
