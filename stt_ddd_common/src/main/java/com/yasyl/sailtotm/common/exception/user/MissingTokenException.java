package com.yasyl.sailtotm.common.exception.user;

public class MissingTokenException extends BaseException{
    public MissingTokenException() {
    }

    public MissingTokenException(String msg) {
        super(msg);
    }
}
