package com.example.networkdisk.repository;

import com.example.networkdisk.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {
    public List<User> selectUserByEmail(@Param("email") String email);

    public List<User> selectUserById(@Param("id") String id);

    public int insertUser(User user);
}
