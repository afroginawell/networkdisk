package com.example.networkdisk.controller;

import com.example.networkdisk.entity.AUser;
import com.example.networkdisk.entity.BUser;
import com.example.networkdisk.service.userService.UserLoginService;
import com.example.networkdisk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 登录信息统一处理类
 */
@Controller
public class LoginController {

    @Autowired
    private UserLoginService userLoginService;
//    @Autowired
//    private AdminLoginService adminLoginService;

    /**
     * 运行login页面前应该先运行toLogin页面，将对应的属性添加进model中
     *
     * @param aUser 对应管理员登录信息包存对象
     * @param bUser 对应用户登录信息包存对象
     * @return
     */
    // @ModelAttribute作用有两个：一是将请求参数的输入封装到user对象中；一是创建UserForm实例，以user为键值存储在Model对象中
    // toLogin没有请求参数，所以就是后面一个作用，以bUser为键值存储在Model对象中
    @RequestMapping("/toLogin")
    public String toUserLogin(@ModelAttribute("aUser") AUser aUser, @ModelAttribute("bUser") BUser bUser) {
        //@ModelAttribute("aUser")与th:object="${aUser}"相对应
        //@ModelAttribute("bUser")与th:object="${bUser}"相对应
        return "login";
    }

    /**
     * 处理用户登录请求
     */
    // 之所以有aUser作为参数只是为了防止页面中的请求参数无绑定而已
    // @Validated要和BindingResult一起用，告诉系统哪些变量需要检查
    @RequestMapping("/userLogin")
    public String userLogin(@ModelAttribute("bUser") @Validated BUser bUser, @ModelAttribute("aUser") AUser aUser,
                            BindingResult rs, HttpSession session, Model model) {
        return userLoginService.login(bUser, session, model);
    }

    /**
     * 处理管理员登录请求
     */
    // 之所以有bUser作为参数只是为了防止页面中的请求参数无绑定而已
    @RequestMapping("/adminLogin")
    public String adminLogin(@ModelAttribute("aUser") @Validated AUser aUser, @ModelAttribute("bUser") BUser bUser,
                             BindingResult rs, HttpSession session, Model model) {
//        return adminLoginService.login(aUser, session, model);
        model.addAttribute("errorMessage","未开发");
        return "error";
    }
}
