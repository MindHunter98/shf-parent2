package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.vo.LoginVo;
import com.atguigu.entity.vo.RegisterVo;
import com.atguigu.result.Result;

/**
 * @FileName com.atguigu.service.UserInfoService
 * @Create 2023/6/8:15:07
 * @Author WCH
 * Description:
 */
public interface UserInfoService extends BaseService<UserInfo> {
    /**
     * 注册功能
     * @param registerVo
     * @return
     */
    Result doRegister(RegisterVo registerVo);

    /**
     * 登录功能
     * @param loginVo
     */
    Result doLogin(LoginVo loginVo);
}
