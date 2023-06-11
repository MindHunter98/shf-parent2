package com.atguigu.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Role;
import com.atguigu.service.RoleService;
import com.atguigu.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @FileName com.atguigu.service.impl.RoleServiceImpl
 * @Create 2023/5/30:20:49
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    //抽取一个基类BaseServiceImpl，然后让AdminServiceImpl和RoleServiceImpl继承BaseServiceImpl，重写getBaseMapper()方法
    @Autowired
    public RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getBaseMapper() {
        return roleMapper;
    }
}
