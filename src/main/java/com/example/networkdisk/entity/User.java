package com.example.networkdisk.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * User对应数据库usertable表
 */
@Entity // 说明类的性质，实例
@Table(name = "usertable") // 类对应的数据库表名
@Data // lombok的快捷生成注解
public class User {
    @Id // 主键注解
    @Temporal(TemporalType.TIMESTAMP)   // 将java的Date类型映射为符合数据库的TimeStamp类型（日期+时间）
    @Column(name = "id", nullable = false)
    private String id;
    @Email(message="邮箱格式错误！")
    @NotBlank(message = "邮箱必须输入！")
    @Column(name = "email", nullable = false)
    private String email;
    @Length(max = 20,min = 6,message = "密码长度在6到20之间！")
    @Pattern(regexp = "[0-9a-zA-Z]{1,}", message = "仅限字母和数字，字母不区分大小写！")
    @Column(name = "pwd", nullable = false)
    private String pwd;
    @Column(name = "name", nullable = false)
    private String name = "用户"; //设置默认值
}
