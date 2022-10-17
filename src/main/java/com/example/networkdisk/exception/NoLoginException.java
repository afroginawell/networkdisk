package com.example.networkdisk.exception;

import java.io.Serial;

/**
 * 未登录异常类
 */
public class NoLoginException extends Exception {
    // 定义程序序列化ID,序列化ID，相当于身份认证.
    // 主要用于程序的版本控制，保持不同版本的兼容性，在程序版本升级时避免程序报出版本不一致的错误。
    @Serial
    private static final long serialVersionUID = 1L;

    public NoLoginException() {
        super();
    }

    public NoLoginException(String message) {
        super(message);
    }
}
