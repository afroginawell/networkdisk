package com.example.networkdisk.service;

import com.example.networkdisk.entity.User;
import com.example.networkdisk.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 处理用户注册功能的业务逻辑
     * @param user
     * @return
     * @throws CustomException 自定义异常
     */
    public void insertUser(User user) throws CustomException;
}
