package com.example.networkdisk.service.userService;

import com.example.networkdisk.entity.BUser;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * 用户注册逻辑业务层接口
 */
public interface UserRegisterService {
    /**
     * 实现注册逻辑业务的方法
     */
    public String register(BUser bUser, HttpSession session, Model model);
}
