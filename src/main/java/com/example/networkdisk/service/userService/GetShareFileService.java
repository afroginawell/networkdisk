package com.example.networkdisk.service.userService;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface GetShareFileService {
    public String getShareFile(ShareFile shareFile, Model model, HttpSession session);
}
