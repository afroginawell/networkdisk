package com.example.networkdisk.controller.userController;

import com.example.networkdisk.entity.AUser;
import com.example.networkdisk.entity.BUser;
import com.example.networkdisk.service.userService.UserLoginService;
import com.example.networkdisk.service.userService.UserRegisterService;
import com.example.networkdisk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;

/**
 * 用户注册控制器类
 */
@Controller
@RequestMapping("/user")
public class UserRegisterController {
    @Autowired
    private UserRegisterService userRegisterService;

    /**
     * 注册预处理方法
     */
    @RequestMapping("/toUserRegister")
    public String toUserRegister(@ModelAttribute("bUser") BUser bUser) {
        return "user/userRegister";
    }

    /**
     * 注册处理方法
     */
    @RequestMapping("/userRegister")
    public String UserRegister(@ModelAttribute("bUser") @Validated BUser bUser, HttpSession session, Model model) {
        return userRegisterService.register(bUser, session, model);
    }
}
