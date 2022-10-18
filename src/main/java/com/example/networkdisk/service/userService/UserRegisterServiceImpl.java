package com.example.networkdisk.service.userService;

import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.repository.FileRepository;
import com.example.networkdisk.repository.UserRepository;
import com.example.networkdisk.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 用户注册业务逻辑层类
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;

    /**
     * 用户注册业务逻辑层方法
     */
    @Override
    public String register(BUser bUser, HttpSession session, Model model) {
        // 获取session中的validateCode属性
        String validateCode = (String) session.getAttribute("validateCode");
        if (!validateCode.equalsIgnoreCase(bUser.getValidateCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "error";
        }
        if (!validateCode.equalsIgnoreCase(bUser.getValidateCode())) {
            System.out.println("验证码错误，但继续执行");
        }
        // 查询邮箱是否可用
        List<BUser> list = userRepository.selectUserByEmail(bUser.getEmail());
        if (list.size() > 0) {
            model.addAttribute("errorMessage", "该邮箱已被注册，请选择其他的邮箱再进行注册！");
            return "error";
        }
        // 生成11位随机数字字符串作为唯一用户ID
        String id;
        do { // 如果11位随机数字字符串作为用户的ID已经存在，重新生成。
            id = MyUtil.createUserId();
            list = userRepository.selectUserById(id);
        } while (list.size() > 0);
        bUser.setId(id);
        // 用户昵称
        bUser.setName("用户昵称可以在个人中心设置");
        // 用户密码中的字母全部设置为小写
        bUser.setPwd(bUser.getPwd().toLowerCase());
        // 数据插入成功返回，插入数据的条数；插入失败，直接报错异常
        int flag = userRepository.register(bUser);
        if (flag == 1) {    // 表示注册成功，插入1条数据
            // 在数据库中新建用户文件夹
            String fileid;
            List<FileDetail> list2;
            do {
                fileid = MyUtil.createFileId();
                list2 = fileRepository.selectByFileid(fileid);
            }while (list2.size() > 0);
            FileDetail fileDetail = new FileDetail(fileid, list.get(0).getId(), "我的网盘", MyUtil.toTimeStamp(new Date()), "根目录", "0");
            flag = fileRepository.insertFileDetail(fileDetail);
            if (flag == 1) {
                // 重定向到登录视图
                return "redirect:/toLogin";
            }
            model.addAttribute("errorMessage", "创建用户文件夹失败！");
            return "error";
        }
        model.addAttribute("errorMessage", "用户名或密码错误！");
        return "error";
    }
}
