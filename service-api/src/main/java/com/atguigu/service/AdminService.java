package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Admin;

/**
 * @FileName com.atguigu.service.AdminService
 * @Create 2023/5/29:20:22
 * @Author WCH
 * Description:
 */
public interface AdminService extends BaseService<Admin> {
    //抽取一个基类BaseService,然后让AdminService和RoleService继承BaseService
}
