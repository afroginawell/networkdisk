package com.example.networkdisk.repository;

import com.example.networkdisk.entity.FileDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface FileDetailRepository {
    public List<FileDetail> selectFileDetailByFileid(String fileid);

    public int insertFileDetail(FileDetail fileDetail);
}
