package com.yasyl.sailtotm.common.exception.admin;

import com.yasyl.sailtotm.exception.user.BaseException;

public class AccessRestrictionException extends BaseException {
    public AccessRestrictionException() {
    }

    public AccessRestrictionException(String msg) {
        super(msg);
    }
}
