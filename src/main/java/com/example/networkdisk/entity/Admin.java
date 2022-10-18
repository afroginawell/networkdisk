package com.example.networkdisk.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Admin类对应数据库admintable表
 */
@Entity // 说明类的性质，实例
@Table(name = "admintable") // 类对应的数据库表名
@Data   // lombok的快捷生成注解
public class Admin {
    @Id // 主键注解
    @GeneratedValue(strategy = GenerationType.AUTO) // 主键生成的策略，AUTO指由程序控制生成
    @NotBlank(message = "管理员ID必须输入！")
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "name", nullable = false)
    private String name = "管理员";    // 默认值设置
    @NotBlank(message = "密码必须输入！")
    @Column(name = "pwd", nullable = false)
    private String pwd;
}
