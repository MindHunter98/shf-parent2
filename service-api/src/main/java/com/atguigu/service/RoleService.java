package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.Role;

/**
 * @FileName com.atguigu.service.RoleService
 * @Create 2023/5/30:20:48
 * @Author WCH
 * Description:
 */
public interface RoleService extends BaseService<Role> {
    //抽取一个基类BaseService,然后让AdminService和RoleService继承BaseService
}
