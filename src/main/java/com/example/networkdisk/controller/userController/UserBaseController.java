package com.example.networkdisk.controller.userController;

import com.example.networkdisk.exception.NoLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * 用户登录权限控制类
 */
@Controller
public class UserBaseController {
    /**
     * 登录权限控制类，处理方法执行前执行该方法
     * NoLoginException：用于处理没有登录的异常
     */
    // @ModelAttribute： 注册一个非请求处理方法，继承该类的类在请求处理方法被调用前会先执行被注释的方法，类似于拦截器或过滤器
    @ModelAttribute
    public void isUserLogin(HttpSession session) throws NoLoginException {
        if (session.getAttribute("bUser") == null) {
            throw new NoLoginException("没有登录");
        }
    }
}
