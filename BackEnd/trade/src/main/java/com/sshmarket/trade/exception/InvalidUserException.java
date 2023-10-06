package com.sshmarket.trade.exception;

public class InvalidUserException extends BusinessException {

    public InvalidUserException(String message) {
        super(403, message);
    }
}
