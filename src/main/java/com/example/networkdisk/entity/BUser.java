package com.example.networkdisk.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 * 用户实例类
 */
public class BUser {
    private String id;

    @Email(message="邮箱格式错误！")
    @NotBlank(message = "邮箱必须输入！")
    private String email;
    @Length(max = 20,min = 6,message = "密码长度在6到20之间！")
    @Pattern(regexp = "[0-9a-zA-Z]{1,}", message = "仅限字母和数字，字母不区分大小写！")
    private String pwd;
    @NotBlank(message = "验证码必须输入！")
    private String validateCode;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    @Override
    public String toString() {
        return "BUser{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", validateCode='" + validateCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
