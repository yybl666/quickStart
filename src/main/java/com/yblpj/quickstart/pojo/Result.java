package com.yblpj.quickstart.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <E> Result<E> success(E data) {
        return new Result<E>(0, "操作成功", data);
    }

    public static <E> Result<E> success() {
        return new Result<E>(0, "操作成功", null);
    }

    public static <E> Result<E> error(String msg) {
        return new Result<E>(1, msg, null);
    }
}
