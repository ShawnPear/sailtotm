package com.yasyl.sailtotm.common.exception.repo;

import com.yasyl.sailtotm.common.exception.user.BaseException;

/**
 * @program: SailToTm
 * @description: Redis错误
 * @author: wujubin
 * @create: 2024-07-20 00:19
 **/
public class RedisException extends BaseException {
    public RedisException() {
    }

    public RedisException(String msg) {
        super(msg);
    }
}
