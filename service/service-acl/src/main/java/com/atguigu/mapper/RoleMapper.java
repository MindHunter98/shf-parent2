package com.atguigu.mapper;
import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Role;

/**
 * @FileName com.atguigu.mapper.RoleMapper
 * @Create 2023/5/30:19:37
 * @Author WCH
 * Description:角色管理
 */
public interface RoleMapper extends BaseMapper<Role> {
    //抽取一个基类BaseMapper,让AdminMapper和RoleMapper继承BaseMapper
}
