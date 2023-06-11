package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.en.UserStatus;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.vo.LoginVo;
import com.atguigu.entity.vo.RegisterVo;
import com.atguigu.mapper.UserInfoMapper;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import com.atguigu.util.MD5;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName com.atguigu.service.impl.UserInfoServiceImpl
 * @Create 2023/6/8:15:19
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public BaseMapper<UserInfo> getBaseMapper() {
        return userInfoMapper;
    }

    /**
     * 注册功能
     *
     * @param registerVo
     * @return
     */
    @Override
    public Result doRegister(RegisterVo registerVo) {
        //0. 判断手机号是否已经被注册了
        UserInfo dbUserInfo = userInfoMapper.findByPhone(registerVo.getPhone());
        if (dbUserInfo != null) {
            //手机号已经被注册了
            return Result.build(null, ResultCodeEnum.PHONE_REGISTER_ERROR);
        }
        //1. 判断昵称是否已经被占用
        dbUserInfo = userInfoMapper.findByNickName(registerVo.getNickName());
        if (dbUserInfo != null) {
            //昵称已经被占用
            return Result.build(null, ResultCodeEnum.NICKNAME_REGISTER_ERROR);
        }
        //2. 将RegisterVO对象转换成UserInfo对象
        UserInfo userInfo = new UserInfo();
        //2.1 属性拷贝
        BeanUtils.copyProperties(registerVo,userInfo);
        //2.2 设置status为1(正常)
        userInfo.setStatus(UserStatus.NORMAL.getStatusCode());
        //2.3 对密码进行加密
        userInfo.setPassword(MD5.encrypt(userInfo.getPassword()));
        //3. 调用持久层的方法保存用户信息
        userInfoMapper.insert(userInfo);
        return Result.ok();
    }

    /**
     * 登录功能
     *
     * @param loginVo
     */
    @Override
    public Result doLogin(LoginVo loginVo) {
        //1. 根据前端的手机号,在数据库中查找是否存在
        UserInfo dbUserInfo = userInfoMapper.findByPhone(loginVo.getPhone());
        if (dbUserInfo == null) {
            //手机号未被注册
            return Result.build(null, ResultCodeEnum.ACCOUNT_ERROR);
        }

        //2. 账号是否被锁定
        if(UserStatus.LOCKED.getStatusCode().equals(dbUserInfo.getStatus())){
            //是被锁定就返回被锁定
            return Result.build(null, ResultCodeEnum.ACCOUNT_LOCK_ERROR);
        }

        //3. 根据前端的密码,在数据库中查找是否存在
        //注意此时前端的密码需要先使用MD5加密之后再到数据库中查询
        if (!dbUserInfo.getPassword().equals(MD5.encrypt(loginVo.getPassword()))) {
            //不存在就返回密码不正确
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }
        //4. 登陆成功
        Map<String, Object> map = new HashMap<>();
        map.put("phone",dbUserInfo.getPhone());
        map.put("nickName",dbUserInfo.getNickName());
        return Result.ok(map);
    }
}
