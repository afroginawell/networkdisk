package com.example.networkdisk.repository;

import com.example.networkdisk.entity.BUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户相关的数据库映射接口
 */
@Mapper
public interface UserRepository {
    /**
     * 处理登录信息与数据库信息的匹配方法
     */
    public List<BUser> userLogin(BUser bUser);

    /**
     * 根据用户email邮箱进行查询
     */
    public List<BUser> selectUserByEmail(@Param("email") String email);

    /**
     * 根据用户ID进行查询
     */
    public List<BUser> selectUserById(@Param("id") String id);

    /**
     * 处理用户注册和数据库插入信息方法
     */
    public int register(BUser bUser);

    /**
     * 处理删除用户信息方法
     */
    public int deleteUser(BUser bUser);
}
