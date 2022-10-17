package com.example.networkdisk.service.userService;

import com.example.networkdisk.entity.BUser;
import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.entity.ShareFile;
import com.example.networkdisk.repository.FileRepository;
import com.example.networkdisk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class GetShareFileServiceImpl implements GetShareFileService{

    @Autowired
    private FileRepository fileRepository;
    @Override
    public String getShareFile(ShareFile shareFile, Model model, HttpSession session) {
        List<ShareFile> file = fileRepository.selectShareFile(shareFile.getShareid());
        if(file.size() != 1){
            model.addAttribute("errorMessage","文件分享ID无效!");
            return "error";
        }
        shareFile = file.get(0);
        // 判断文件分享是否过期，过期删除
        if(MyUtil.toDate(shareFile.getTime()).compareTo(new Date()) <= 0){
            int flag = fileRepository.deleteShareFile(shareFile);
            if(flag != 1){
                model.addAttribute("errorMessage","分享文件失效，但无法删除!");
                return "error";
            }
            model.addAttribute("errorMessage","分享文件失效!");
            return "error";
        }
        // 判断是否为指定用户
        String[] ids = shareFile.getAccessid().split("/");
        String userid = ((BUser)session.getAttribute("bUser")).getId();
//        if(ids.length != 0){    // 设置了特定人才能访问
//            int count = 0;
//            for(String id:ids){
//                if(!id.equals(userid)){
//                    count++;
//                }
//            }
//            if(count == ids.length){
//                model.addAttribute("errorMessage","无权限访问该分享文件!");
//                return "error";
//            }
//        }
        // 在访问权限内，或者没有设置访问限制
        // 根据分享文件的id，查询分享文件的详细信息
        List<FileDetail> fileDetail = fileRepository.selectByFileid(shareFile.getFileid());
        if(fileDetail.size() != 1){
            model.addAttribute("errorMessage","分享文件出现问题，无法访问!");
            return "error";
        }
        session.setAttribute("shareFileDetail",fileDetail.get(0));
        session.setAttribute("shareFile",shareFile);
        return "redirect:/user/toGetShareFile";
    }
}
