package com.example.networkdisk.service.userService;

import com.example.networkdisk.entity.BUser;
import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.entity.FileIndex;
import com.example.networkdisk.entity.ShareFile;
import com.example.networkdisk.repository.FileRepository;
import com.example.networkdisk.util.MyUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.Filer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;

@Service
public class MyNetworkDiskServiceImpl implements MyNetworkDiskService{

    @Autowired
    private FileRepository fileRepository;

    @Override
    public String uploadFile(HttpServletRequest request, MultipartFile uploadFile, Model model, HttpSession session) throws IOException {
        // 生成文件唯一ID
        String fileid;
        List<FileDetail> list;
        do{
            fileid = MyUtil.createFileId();
            list = fileRepository.selectByFileid(fileid);
        }while(list.size() > 0);

        // 找到父目录id
        LinkedHashMap parentFile = (LinkedHashMap)session.getAttribute("parentFile");
        Set keys = parentFile.keySet();
        String parentid="";
        for(Iterator iter = keys.iterator(); iter.hasNext();) {
            // 只需要最后一个父目录id即可
            parentid = (String)iter.next();
        }
        if(parentid.isBlank()){
            model.addAttribute("errorMessage","父目录路径错误");
            return "error";
        }

        // 获取文件名和样式
        String name = uploadFile.getOriginalFilename();
        int index = name.lastIndexOf(".");
        String style = name.substring(index,name.length());
        name = name.substring(0,index);

        // 将文件内容转化成一个byte[]
        byte[] file = uploadFile.getBytes();

        // 获取文件的大小
        StringBuffer size = new StringBuffer();
        long bytes = Math.round(uploadFile.getSize() / 1024);
        size.append(bytes + " KB");

        FileDetail fileDetail = new FileDetail(fileid,parentid,name,MyUtil.toTimeStamp(new Date()),style,size.toString());
        FileIndex fileIndex = new FileIndex(fileid, file);
        int flag = fileRepository.insertFileDetail(fileDetail);
        if(flag != 1){
            model.addAttribute("errorMessage","文件详情插入文件错误!");
            return "error";
        }

        // 更新父目录的文件大小
        for(Iterator iter = keys.iterator(); iter.hasNext();) {
            parentid = (String)iter.next();
            List<FileDetail> fd = fileRepository.selectByFileid(parentid);
            long fdSize = Integer.valueOf(fd.get(0).getSize().split(" ")[0]);
            String nowSize = (fdSize+bytes)+" KB";
            Map<String,String> map = new HashMap<>();
            map.put("fileid",parentid);
            map.put("size",nowSize);
            flag = fileRepository.updateSizeByFileid(map);
            if(flag != 1){
                model.addAttribute("errorMessage","文件大小修改错误!");
                return "error";
            }
        }

        flag = fileRepository.insertFileIndex(fileIndex);
        if(flag != 1){
            model.addAttribute("errorMessage","文件索引插入文件错误!");
            return "error";
        }
        List<FileDetail> childFile = (List<FileDetail>) session.getAttribute("childFile");
        childFile.add(fileDetail);
        session.setAttribute("childFile",childFile);
        return "redirect:/user/toMyNetworkDisk";
    }

    @Override
    public String createDir(HttpSession session) {
        List<FileDetail> childFile = (List<FileDetail>) session.getAttribute("childFile");
        // 生成文件唯一ID
        String fileid;
        List<FileDetail> list;
        do{
            fileid = MyUtil.createFileId();
            list = fileRepository.selectByFileid(fileid);
        }while(list.size() > 0);

        // 找到父目录id
        LinkedHashMap parentFile = (LinkedHashMap)session.getAttribute("parentFile");
        Set keys = parentFile.keySet();
        String parentid="";
        for(Iterator iter = keys.iterator(); iter.hasNext();) {
            // 只需要最后一个父目录id即可
            parentid = (String)iter.next();
        }

        // 获取文件名和样式
        String name = "新建文件夹";
        String style = "目录";

        // 获取文件的大小
        StringBuffer size = new StringBuffer();
        size.append(0 + " KB");

        FileDetail fileDetail = new FileDetail(fileid,parentid,name,MyUtil.toTimeStamp(new Date()),style,size.toString());
        childFile.add(fileDetail);
        session.setAttribute("childFile",childFile);
        fileRepository.insertFileDetail(fileDetail);
        return "redirect:/user/toMyNetworkDisk";
    }

    @Override
    public String openDir(HttpServletRequest request, HttpSession session){
        // 获取请求url中的fileid
        String fileid = request.getParameter("fileid");
        String name = request.getParameter("name");
        // 更新父目录
        LinkedHashMap<String, String> parentFile = (LinkedHashMap<String, String>) session.getAttribute("parentFile");
        parentFile.put(fileid , name);
        session.setAttribute("parentFile", parentFile);
        // 更新子文件
        List<FileDetail> childFile = fileRepository.selectByParentid(fileid);
        session.setAttribute("childFile",childFile);
        // 重新返回用户操作界面
        return "redirect:/user/toMyNetworkDisk";
    }

    @Override
    public String renameFile(HttpSession session, HttpServletRequest request) {
        String fileid = request.getParameter("fileid");
        String newName = request.getParameter("newName");
        Map<String,String> map = new HashMap<>();
        map.put("fileid",fileid);
        map.put("name",newName);
        int flag = fileRepository.updateNamebyFileid(map);
        List<FileDetail> childFile = (List<FileDetail>) session.getAttribute("childFile");
        for(FileDetail fd:childFile){
            if(fd.getFileid().equals(fileid)){
                fd.setName(newName);
                break;
            }
        }
        return "redirect:/user/toMyNetworkDisk";
    }

    @Override
    public String shareFile(HttpServletRequest request, HttpSession session) {
        String shareid = MyUtil.createShareId();
        BUser bUser = (BUser) session.getAttribute("bUser");
        ShareFile sf = new ShareFile(request.getParameter("fileid"),bUser.getId(),shareid,MyUtil.getAfterSevenDays(),"");
        int flag = fileRepository.insertShareFile(sf);
        return "redirect:/user/toMyNetworkDisk?shareid="+shareid;
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, String userAgent) throws IOException {
        List<FileIndex> fileIndex = fileRepository.selectFileById(request.getParameter("fileid"));
        byte[] file = fileIndex.get(0).getFile();
        String path = request.getServletContext().getRealPath("/uploadFiles/");
        List<FileDetail> fileDetail = fileRepository.selectByFileid(request.getParameter("fileid"));
        //构建将要下载的文件对象
        String filename = request.getParameter("fileid") + fileDetail.get(0).getStyle();
//        File downFile = new File(path + File.separator + filename);
        //ok表示HTTP中的状态是200
        ResponseEntity.BodyBuilder builder =  ResponseEntity.ok();
        //内容长度
        builder.contentLength(file.length);
        //application/octet-stream：二进制流数据（最常见的文件下载）
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用URLEncoder.encode对文件名进行编码
        filename = URLEncoder.encode(filename,"UTF-8");
        /**
         * 设置实际的响应文件名，告诉浏览器文件要用于“下载”和“保存”。
         * 不同的浏览器，处理方式不同，根据浏览器的实际情况区别对待。
         */
        if(userAgent.indexOf("MSIE") > 0) {
            //IE浏览器，只需要用UTF-8字符集进行URL编码
            builder.header("Content-Disposition", "attachment; filename=" + filename);
        }else {
            /**非IE浏览器，如FireFox、Chrome等浏览器，则需要说明编码的字符集
             * filename后面有个*号，在UTF-8后面有两个单引号
             */
            builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + filename);
        }
        return builder.body(file);
    }

    @Override
    public String deleteFile(HttpServletRequest request, HttpSession session){
        String fileid = request.getParameter("fileid");

        // 如果文件在分享文件里，这里就会出错
        fileRepository.deleteFileIndexByFileid(fileid);
        fileRepository.deleteFileDetailByFieid(fileid);
        List<FileDetail> fileDetail = (List<FileDetail>) session.getAttribute("childFile");
        for(FileDetail fd:fileDetail){
            if(fd.getFileid().equals(fileid)){
                fileDetail.remove(fd);
                break;
            }
        }
        return "redirect:/user/toMyNetworkDisk";
    }
}
// 文件名正则校验：[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+
