package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Admin;
import com.atguigu.service.AdminService;
import com.atguigu.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @FileName com.atguigu.service.impl.AdminServiceImpl
 * @Create 2023/5/29:20:22
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = AdminService.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    //抽取一个基类BaseServiceImpl，然后让AdminServiceImpl和RoleServiceImpl继承BaseServiceImpl，重写getBaseMapper()方法
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public BaseMapper<Admin> getBaseMapper() {
        return adminMapper;
    }
}

