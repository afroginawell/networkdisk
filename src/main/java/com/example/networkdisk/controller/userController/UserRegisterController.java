package com.example.networkdisk.controller.userController;

import com.example.networkdisk.exception.CustomException;
import com.example.networkdisk.service.FileService;
import com.example.networkdisk.service.UserService;
import com.example.networkdisk.vo.UserAndValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 主要用于响应userRegister页面的请求
 * 大致为两种链接请求，user/toUserRegister和user/userRegister
 */
@Controller
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    private Logger logger = Logger.getLogger(UserRegisterController.class.getName());

    /**
     * 注册预处理方法
     * 1. 通过@ModelAttibute往返回的视图中添加UserAndValidate和ErrorMessage两个模型
     * 2. 通过return返回添加了模型的视图
     */
    @RequestMapping("/toUserRegister")
    public String toUserRegister(@ModelAttribute("userAndValidate") @Validated UserAndValidate userAndValidate) {
        // 返回添加了模型的视图
        return "user/userRegister";
    }

    /**
     * 注册处理方法
     * 1. 通过@ModelAttribute注解获取UserAndValidate和ErrorMessage两个模型
     * 2. 进行数据处理，然后返回新的视图
     */
    @RequestMapping("/userRegister")
    public String userRegister(@ModelAttribute("userAndValidate") @Validated UserAndValidate userAndValidate, HttpSession session) throws CustomException {
        // @Validate异常处理，写在了全局异常控制器GlobalExceptionController中
        // 判断验证码是否正确
        if (!userAndValidate.getValidate().equals(session.getAttribute("validateCode"))) {
            logger.log(Level.WARNING, "验证码错误，输入的验证码为：" + userAndValidate.getValidate() + " ，正确的验证码为:" + session.getAttribute("validateCode"));
            throw new CustomException("验证码错误!");
        }
        // 无返回值，如果出现异常，在insertUser中就会处理，不需要在这里再做处理
        userService.insertUser(userAndValidate.getUser());
        // 无返回值，如果出现异常，在insertFile中就会处理，不需要在这里再做处理
        fileService.insertFile(userAndValidate.getUser().getId());
        // 执行返回操作说明之前没有出现错误，注册成功会重定向到toLogin页面，并且会出现弹窗提示注册成功
        return "redirect:toLogin?message=registerSuccess";
    }
}
