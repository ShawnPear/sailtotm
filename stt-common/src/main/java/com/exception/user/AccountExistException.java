package com.exception.user;

public class AccountExistException extends BaseException {
    public AccountExistException() {
    }

    public AccountExistException(String msg) {
        super(msg);
    }
}
