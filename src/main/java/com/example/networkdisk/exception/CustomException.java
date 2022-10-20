package com.example.networkdisk.exception;

public class CustomException extends Exception {
    public CustomException() {
        super("未知错误，请检查程序!");
    }

    public CustomException(String message) {
        super(message);
    }
}
