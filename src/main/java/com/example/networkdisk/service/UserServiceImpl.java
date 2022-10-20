package com.example.networkdisk.service;

import com.example.networkdisk.entity.User;
import com.example.networkdisk.exception.CustomException;
import com.example.networkdisk.repository.UserRepository;
import com.example.networkdisk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    private Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    @Override
    public void insertUser(User user) throws CustomException {
        // 判断邮箱是否可用

        if(userRepository.selectUserByEmail(user.getEmail()).size() != 0){
            logger.log(Level.WARNING, "被注册过的邮箱又被注册:"+user.getEmail());
            throw new CustomException("邮箱已经被注册!");
        }

        // 生成用户的唯一ID
        String id;
        do { // 如果11位随机数字字符串作为用户的ID已经存在，重新生成。
            id = MyUtil.createUserId();
        } while (userRepository.selectUserById(id).size() > 0);
        user.setId(id);

        // 往用户表中插入数据
        if(userRepository.insertUser(user) != 1){
            logger.log(Level.WARNING, "用户表增加信息错误，增加的信息为:"+user.toString());
            throw new CustomException("用户表插入注册信息异常!");
        }
    }
}
