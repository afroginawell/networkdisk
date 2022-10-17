package com.example.networkdisk.repository;

import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.entity.FileIndex;
import com.example.networkdisk.entity.ShareFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FileRepository {

    public List<FileDetail> selectByParentid(@Param("parentid") String parentid);
    public int insertFileDetail(FileDetail fileDetail);

    public int insertFileIndex(FileIndex fileIndex);
    public List<ShareFile> selectShareFile(@Param("shareid") String shareid);

    public int deleteShareFile(ShareFile shareFile);

    public List<FileDetail> selectByFileid(@Param("fileid") String fileid);

    public int updateSizeByFileid(Map map);

    public int updateNamebyFileid(Map map);

    public int insertShareFile(ShareFile shareFile);

    public List<FileIndex> selectFileById(@Param("id") String id);

    public int deleteFileIndexByFileid(@Param("fileid") String fileid);

    public int deleteFileDetailByFieid(@Param("fileid") String fileid);
}
