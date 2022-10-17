package com.example.networkdisk.controller.userController;

import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.entity.FileIndex;
import com.example.networkdisk.service.userService.MyNetworkDiskService;
import com.example.networkdisk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class MyNetworkDiskController extends UserBaseController{

    @Autowired
    private MyNetworkDiskService myNetworkDiskService;

    @RequestMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request, @RequestParam("uploadFile") MultipartFile uploadFile, Model model, HttpSession session) throws IOException {
        if(!uploadFile.isEmpty()){
            return myNetworkDiskService.uploadFile(request, uploadFile, model, session);
        }
        model.addAttribute("errorMessage","上传的文件路径为空，找不到上传文件!");
        return "error";
    }

    @RequestMapping("createDir")
    public String createDir(HttpSession session){
        return myNetworkDiskService.createDir(session);
    }

    @RequestMapping("openDir")
    public String openDir(HttpServletRequest request, HttpSession session){
        return myNetworkDiskService.openDir(request,session);
    }

    @RequestMapping("/renameFile")
    public String renameFile(HttpSession session, HttpServletRequest request){
        return myNetworkDiskService.renameFile(session,request);
    }

    @RequestMapping("/shareFile")
    public String shareFile(HttpServletRequest request, HttpSession session){
        return myNetworkDiskService.shareFile(request, session);
    }

    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, @RequestHeader("User-Agent") String userAgent) throws IOException {
        return myNetworkDiskService.downloadFile(request, userAgent);
    }

    @RequestMapping("/deleteFile")
    public String deleteFile(HttpServletRequest request, HttpSession session){
        return myNetworkDiskService.deleteFile(request,session);
    }
}
