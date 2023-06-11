package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @FileName com.atguigu.controller.RoleController
 * @Create 2023/5/30:20:49
 * @Author WCH
 * Description:
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private static final String PAGE_INDEX = "role/index";
    private static final String PAGE_EDIT = "role/edit";
    private static final String PAGE_SUCCESS = "common/successPage";
    private static final String LIST_ACTION = "redirect:/role";
    @Reference
    public RoleService roleService;

    /**
     * 根据分页查找所有用户,以及搜索用户
     * @param filters
     * @param model
     * @return
     */
    @RequestMapping
    public String index(@RequestParam Map<String,String> filters, Model model){
        //1. 调用业务层的方法分页查询Role
        PageInfo<Role> pageInfo = roleService.findPage(filters);
        //2. 将查询到的adminList存储到请求域中
        model.addAttribute("page",pageInfo);
        //3. 将搜索条件存储到请求域中
        model.addAttribute("filters",filters);
        //4. 返回逻辑视图
        return PAGE_INDEX;
    }

    /**
     * 新增用户
     * @param role
     * @param model
     * @return
     */
    @PostMapping("save")
    public String insert(Role role,Model model){
        //1. 调用业务层的方法新增用户
        roleService.insert(role);
        //2. 将成功信息存储到请区域中
        model.addAttribute("messagePage","新增用户成功");
        //3. 返回成功视图
        return PAGE_SUCCESS;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        //1. 调用业务层的方法删除用户
        roleService.delete(id);
        //2. 返回用户列表视图
        return LIST_ACTION;
    }

    /**
     * 修改用户信息
     * @param role
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(Role role,Model model){
        //1. 调用业务层修改用户的方法
        roleService.update(role);
        //2. 将修改成功的信息存入共享域
        model.addAttribute("messagePage","修改用户成功");
        //3. 返回用户视图
        return PAGE_SUCCESS;
    }

    /**
     * 根据Id查找用户,与修改用户搭配使用
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String findById(@PathVariable("id") Integer id,Model model){
        //1. 调用业务层根据Id查询用户
        Role role = roleService.findById(id);
        //2. 将查询的用户存入共享域
        model.addAttribute("role",role);
        //3. 返回到编辑视图
        return PAGE_EDIT;
    }

}
