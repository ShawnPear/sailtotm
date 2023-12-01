package com.exception.user;

public class IdExistException extends BaseException {

    public IdExistException() {
    }

    public IdExistException(String fail) {
        super(fail);
    }
}
