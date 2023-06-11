package com.atguigu.base;

import org.springframework.ui.Model;

/**
 * @FileName com.atguigu.base.BaseController
 * @Create 2023/5/31:20:09
 * @Author WCH
 * Description:
 */
public class BaseController {
    //抽取一个基类BaseController，用来处理新增和修改成功之后显示成功页面
    public static final String PAGE_SUCCESS = "common/successPage";

    public String successPage(String message, Model model){
        //1. 将保存成功的信息存储到model中
        model.addAttribute("messagePage",message);
        //2. 返回成功页面的逻辑视图
        return PAGE_SUCCESS;
    }

}
