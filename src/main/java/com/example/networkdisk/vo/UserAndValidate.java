package com.example.networkdisk.vo;

import com.example.networkdisk.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserAndValidate {
    private User user;
    @NotBlank(message = "验证码必须输入！")
    private String validate;
}
