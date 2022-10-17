package com.example.networkdisk.controller.userController;

import com.example.networkdisk.entity.BUser;
import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.entity.ShareFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserOperationController extends UserBaseController{

    @RequestMapping("/userOperation")
    public String userOperation(HttpSession session) {
        session.setAttribute("currentModel",1);
        return "user/userOperation";
    }

    @RequestMapping("/toMyNetworkDisk")
    public String toMyNetworkDisk(HttpSession session){
        session.setAttribute("currentModel",1);
        return "user/userOperation";
    }

    @RequestMapping("/myShareFile")
    public String myShareFile(HttpSession session){
        session.setAttribute("currentModel",2);
        return "user/userOperation";
    }

    @RequestMapping("/toGetShareFile")
    public String toGetShareFile(@ModelAttribute("shareFile") ShareFile shareFile, HttpSession session){
        session.setAttribute("currentModel",3);
        session.setAttribute("shareFileDetail",new FileDetail());
        session.setAttribute("shareFile",new ShareFile());
        return "user/userOperation";
    }

    @RequestMapping("/recycleBin")
    public String recycleBin(HttpSession session){
        session.setAttribute("currentModel",4);
        return "user/userOperation";
    }

}
