package com.example.networkdisk.controller.userController;

import com.example.networkdisk.service.userService.GetShareFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class GetShareFileController extends UserBaseController{

    @Autowired
    private GetShareFileService getShareFileService;

    @RequestMapping("/getShareFile")
    public String getShareFile(@ModelAttribute("shareFile") ShareFile shareFile, Model model, HttpSession session){
        if(shareFile.getShareid().length() != 11 || !shareFile.getShareid().matches("[a-zA-Z]+")){ //
            model.addAttribute("errorMessage","文件分享ID错误！");
            return "error";
        }
        return getShareFileService.getShareFile(shareFile,model,session);
    }
}
