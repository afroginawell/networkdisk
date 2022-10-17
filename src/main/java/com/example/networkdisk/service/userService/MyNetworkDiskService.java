package com.example.networkdisk.service.userService;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface MyNetworkDiskService {
    public String uploadFile(HttpServletRequest request, MultipartFile uploadFile, Model model, HttpSession session) throws IOException;

    public String createDir(HttpSession session);

    public String openDir(HttpServletRequest request, HttpSession session);

    public String renameFile(HttpSession session, HttpServletRequest request);

    public String shareFile(HttpServletRequest request, HttpSession session);

    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, String userAgent) throws IOException;

    public String deleteFile(HttpServletRequest request, HttpSession session);
}
