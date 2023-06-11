package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.util.FileUtil;
import com.atguigu.util.QiniuUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @FileName com.atguigu.controller.AdminController
 * @Create 2023/5/29:20:22
 * @Author WCH
 * Description:
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private static final String PAGE_UPLOAD_SHOW = "admin/upload";
    public static final String PAGE_INDEX = "admin/index";
    public static final String PAGE_EDIT = "admin/edit";
    public static final String LIST_ACTION = "redirect:/admin";
    @Reference
    private AdminService adminService;

    //根据分页查找所有用户
    @RequestMapping
    public String index(@RequestParam Map<String,String> filters, Model model){
        //1. 调用业务层的方法分页查询Admin
        PageInfo<Admin> pageInfo = adminService.findPage(filters);
        //2. 将查询到的adminList存储到请求域中
        model.addAttribute("page",pageInfo);
        //3. 将搜索条件存储到请求域中
        model.addAttribute("filters",filters);
        //4. 返回逻辑视图
        return PAGE_INDEX;
    }

    //根据Id查找用户,跳转到编辑页面,一般是和修改一起使用
    @GetMapping("/find/{id}")
    public String findById(@PathVariable("id") Integer id,Model model){
        //1. 调用业务层的方法根据id查询Admin
        Admin admin = adminService.findById(id);
        //2. 将查询到的admin存储到请求域中
        model.addAttribute("admin",admin);
        //3. 返回逻辑视图
        return PAGE_EDIT;
    }

    //添加用户
    @PostMapping("/insert")
    public String insert(Admin admin,Model model){
        //1. 调用业务层的方法新增用户Admin
        adminService.insert(admin);
        /*//2. 将保存成功的信息存储到Model中
        model.addAttribute("messagePage","新增admin信息成功");
        //3. 返回逻辑视图
        return PAGE_SUCCESS;*/
        return successPage("新增admin信息成功",model);
    }

    //删除用户
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        //1. 调用业务层的方法删除Admin
        adminService.delete(id);
        //2. 重定向到/admin
        return LIST_ACTION;
    }

    //修改用户
    @PostMapping("/update")
    public String update(Admin admin,Model model){
        //1. 调用业务层的方法修改Admin
        adminService.update(admin);
        /*//2. 将修改成功的信息存储到Model
        model.addAttribute("messagePage","修改admin信息成功");
        //3. 返回成功页面的逻辑视图
        return PAGE_SUCCESS;*/
        return successPage("修改admin信息成功",model);
    }

    @GetMapping("/uploadShow/{id}")
    public String uploadShow(@PathVariable("id") Integer id,Model model){
        //将id存入请求域
        model.addAttribute("id",id);
        //显示头像上传页面
        return PAGE_UPLOAD_SHOW;
    }

    @PostMapping("/upload/{id}")
    public String upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") Integer id, Model model) throws IOException {
        //1. 将客户端上传的头像存入七牛云:要求文件名唯一、并且要有两级随机目录
        //1.1 获取唯一文件名
        String uuidName = FileUtil.getUUIDName(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        //1.2 随机的两级目录
        String randomDirPath = FileUtil.getRandomDirPath(2, 2);
        //1.3 拼接图片在七牛云的存储路径
        String filePath = randomDirPath + uuidName;
        //1.4 上传到七牛云
        QiniuUtils.upload2Qiniu(multipartFile.getBytes(),filePath);
        //2. 修改admin的head_url
        //2.1 获取图片的访问路径
        String url = QiniuUtils.getUrl(filePath);
        Admin admin = new Admin();
        admin.setId(Long.valueOf(id));
        admin.setHeadUrl(url);
        //2.2 修改admin
        adminService.update(admin);
        //3. 显示成功页面
        return successPage("头像上传成功",model);
    }

}
