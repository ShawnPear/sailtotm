package com.yasyl.sailtotm.exception.admin;

import com.yasyl.sailtotm.exception.user.BaseException;

public class AccessRestrictionException extends BaseException {
    public AccessRestrictionException() {
    }

    public AccessRestrictionException(String msg) {
        super(msg);
    }
}
