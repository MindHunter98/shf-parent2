package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Admin;

/**
 * @FileName com.atguigu.mapper.AdminMapper
 * @Create 2023/5/29:16:49
 * @Author WCH
 * Description:用户管理
 */
public interface AdminMapper extends BaseMapper<Admin> {
    //抽取一个基类BaseMapper,让AdminMapper和RoleMapper继承BaseMapper
}
