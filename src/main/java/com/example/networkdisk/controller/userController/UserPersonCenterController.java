package com.example.networkdisk.controller.userController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserPersonCenterController extends UserBaseController{

    @RequestMapping("/personCenter")
    public String personCenter(){
        System.out.println("personCenter controller");
        return "user/personCenter";
    }
}
