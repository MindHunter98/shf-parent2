package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.UserInfo;

/**
 * @FileName com.atguigu.mapper.UserInfoMapper
 * @Create 2023/6/8:15:18
 * @Author WCH
 * Description:
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    /**
     * 根据前端传输的手机号,到数据库中查找用户,判断用户是否存在(手机号是否被注册)
     * @param phone
     * @return
     */
    UserInfo findByPhone(String phone);

    /**
     * 根据前端传输的用户名,到数据库中查找用户,判断用户是否存在(用户名是否被注册)
     * @param nickName
     * @return
     */
    UserInfo findByNickName(String nickName);
}
