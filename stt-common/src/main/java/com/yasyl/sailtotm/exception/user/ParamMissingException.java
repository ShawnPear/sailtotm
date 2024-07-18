package com.yasyl.sailtotm.exception.user;

public class ParamMissingException extends BaseException {
    ParamMissingException() {
    }

    public ParamMissingException(String msg) {
        super(msg);
    }
}
