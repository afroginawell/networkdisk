package com.example.networkdisk.controller;


import com.example.networkdisk.exception.NoLoginException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

import java.sql.SQLException;
import java.util.List;

/**
 * 统一处理异常类
 */
// @controllerAdvice是一个增强的Controller
// 可以实现三方面的功能：全局异常处理、全局数据绑定、全局数据预处理
// 使用@controllerAdvice注解的类是当前Spring Boot应用中所有类的统一异常处理类
// 该类中使用@ExceptionHandler注解的方法统一处理异常，不需要在每个Controller中逐一定义异常处理方法
// 对所有注解了@RequestMapping的控制器方法有效
@ControllerAdvice
public class GlobalExceptionHandleController {
    // @ExceptionHandler(value = Exception.class)注解用来处理异常
    // 如果Controller中有一个使用@ExceptionHandler注解修饰的方法，那么当Controller的任何方法抛出异常时，都由该方法处理异常。

    /**
     * 全局异常处理方法
     */
    // @ExceptionHandler注解的方法统一处理异常
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        String message;
        //数据库异常
        if (e instanceof SQLException) {
            message = "数据库异常";
        } else if (e instanceof NoLoginException) {
            message = "未登录异常";
        } else if (e instanceof BindException){
            List<FieldError> errors = ((BindException) e).getFieldErrors();
            message = "";
            for(FieldError error:errors){
                message += error.getDefaultMessage();
            }
        } else {
            message = e.toString();
        }
        model.addAttribute("errorMessage", message);
        return "error";
    }
}
