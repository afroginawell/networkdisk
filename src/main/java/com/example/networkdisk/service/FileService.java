package com.example.networkdisk.service;

import com.example.networkdisk.exception.CustomException;
import org.springframework.stereotype.Service;

@Service
public interface FileService {
    public void insertFile(String parentid) throws CustomException;
    public void insertFile(String parentid, String name, String style) throws CustomException;
//    public void insertFile(String userid, String name, byte[] file);

}
