package com.example.networkdisk.service.userService;

import com.example.networkdisk.entity.BUser;
import com.example.networkdisk.entity.FileDetail;
import com.example.networkdisk.repository.FileRepository;
import com.example.networkdisk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录业务逻辑层的具体实现
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileRepository fileRepository;

    /**
     * 登录业务逻辑实现方法
     */
    @Override
    public String login(BUser bUser, HttpSession session, Model model) {
        // 获取session中的validateCode属性
        String validateCode = (String) session.getAttribute("validateCode");
        if (!validateCode.equalsIgnoreCase(bUser.getValidateCode())) {
            model.addAttribute("errorMessage", "验证码错误！");
            return "error";
        }
        List<BUser> list = userRepository.userLogin(bUser);
        if (list.size() > 0) {
            // 将查询到的用户信息存入session中
            session.setAttribute("bUser", list.get(0));
            // 将父文件存入session中
            List<FileDetail> fd = fileRepository.selectByParentid(list.get(0).getId());
            Map<String,String> parentFile =  new LinkedHashMap<>();
            parentFile.put(fd.get(0).getFileid(),"我的网盘");
            session.setAttribute("parentFile",parentFile);
            // 将父文件对应的子文件存入session中,第一次查询的是根目录的数据，第二次使用根目录的文件ID进行查找
            List<FileDetail> childFile= fileRepository.selectByParentid(fd.get(0).getFileid());
            session.setAttribute("childFile", childFile);
            // 重定向到用户操作界面
            return "redirect:/user/userOperation";
        }
        model.addAttribute("errorMessage", "用户名或密码错误！");
        return "error";
    }
}
