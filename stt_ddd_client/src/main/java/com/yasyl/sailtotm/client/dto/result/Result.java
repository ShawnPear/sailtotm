package com.yasyl.sailtotm.client.dto.result;

import com.yasyl.sailtotm.common.constant.MessageConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * 后端统一返回结果
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; //编码：1成功，0和其它数字为失败
    private String message; //错误信息
    private T data; //数据

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    public static <T> Result<T> status(Boolean status, String success, String error, T data) {
        if (status == null || !status) {
            return Result.error(error);
        }
        return Result.success(data, success);
    }

    public static <T> Result<T> status(Boolean status, String success, String error) {
        if (status == null || !status) {
            return Result.error(error);
        }
        return Result.success(success);
    }

    public static <T> Result<T> status(Boolean status) {
        String error = MessageConstant.FAIL;
        String success = MessageConstant.SUCCESS;
        if (status == null || !status) {
            return Result.error(error);
        }
        return Result.success(success);
    }

    public static <T> Result<T> dataDetect(Boolean status, String success, String error, T data) {
        if (status == null || !status) {
            return Result.success(data, error);
        }
        return Result.success(data, success);
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(String msg) {
        Result result = new Result();
        result.message = msg;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> success(T object, String msg) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.message = msg;
        result.code = 1;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.message = msg;
        result.code = 0;
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg) {
        Result result = new Result();
        result.message = msg;
        result.code = 0;
        return result;
    }

}
