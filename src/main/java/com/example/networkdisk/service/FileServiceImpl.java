package com.example.networkdisk.service;

import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.exception.CustomException;
import com.example.networkdisk.repository.FileDetailRepository;
import com.example.networkdisk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileServiceImpl implements FileService{
    @Autowired
    private FileDetailRepository fileDetailRepository;
    @Override
    public void insertFile(String parentid) throws CustomException{
        insertFile(parentid, "我的网盘", "根目录");
    }
    @Override
    public void insertFile(String parentid, String name, String style) throws CustomException{
        // 用户是无法修改userid的，作为参数传进来就说明这个userid是有效的
        // 生成文件ID
        String fileid;
        do{
            fileid = MyUtil.createFileId();
        }while(fileDetailRepository.selectFileDetailByFileid(fileid).size() > 0);
        FileDetail fileDetail = new FileDetail();
        fileDetail.setFileid(fileid);
        fileDetail.setName(name);
        fileDetail.setParentid(parentid);
        fileDetail.setSize("0 KB");
        fileDetail.setStyle(style);
        if(fileDetailRepository.insertFileDetail(fileDetail) != 1){
            Logger logger = Logger.getLogger(FileServiceImpl.class.getName());
            logger.log(Level.WARNING,"文件详情表增加信息错误，增加信息为:"+fileDetail.toString());
            throw new CustomException("文件详情表增加信息错误!");
        }
    }
}
