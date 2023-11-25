package com.exception.user;

public class EmailFormatErrorException extends BaseException {

    public EmailFormatErrorException() {
    }

    public EmailFormatErrorException(String msg) {
        super(msg);
    }
}
