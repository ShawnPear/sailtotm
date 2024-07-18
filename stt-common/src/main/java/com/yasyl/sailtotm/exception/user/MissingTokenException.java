package com.yasyl.sailtotm.exception.user;

public class MissingTokenException extends BaseException{
    public MissingTokenException() {
    }

    public MissingTokenException(String msg) {
        super(msg);
    }
}
