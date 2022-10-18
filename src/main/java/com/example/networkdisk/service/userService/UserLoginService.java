package com.example.networkdisk.service.userService;

import com.example.networkdisk.entity.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * 用户登录逻辑业务层接口
 */
public interface UserLoginService {
    /**
     * 实现登录逻辑业务的方法
     */
    public String login(User user, HttpSession session, Model model);
}
