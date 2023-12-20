package com.exception.admin;

import com.exception.user.BaseException;

public class AccessRestrictionException extends BaseException {
    public AccessRestrictionException() {
    }

    public AccessRestrictionException(String msg) {
        super(msg);
    }
}
