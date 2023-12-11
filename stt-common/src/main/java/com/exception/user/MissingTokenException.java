package com.exception.user;

public class MissingTokenException extends BaseException{
    public MissingTokenException() {
    }

    public MissingTokenException(String msg) {
        super(msg);
    }
}
