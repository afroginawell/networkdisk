package com.example.networkdisk.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 管理员实体类
 */
public class AUser {
    private String name;
    @NotBlank(message = "管理员ID必须输入！")
    private String id;

    @NotBlank(message = "密码必须输入！")
    private String pwd;
    @NotBlank(message = "验证码必须输入！")
    private String validateCode;
    @Validated

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
