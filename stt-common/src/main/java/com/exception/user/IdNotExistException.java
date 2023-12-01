package com.exception.user;

public class IdNotExistException extends BaseException {

    public IdNotExistException() {
    }

    public IdNotExistException(String fail) {
        super(fail);
    }
}
