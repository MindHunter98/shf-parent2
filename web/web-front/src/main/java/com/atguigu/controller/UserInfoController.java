package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.UserInfo;
import com.atguigu.entity.vo.LoginVo;
import com.atguigu.entity.vo.RegisterVo;
import com.atguigu.result.Result;
import com.atguigu.result.ResultCodeEnum;
import com.atguigu.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @FileName com.atguigu.controller.UserInfoController
 * @Create 2023/6/8:11:32
 * @Author WCH
 * Description:
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private JedisPool jedisPool;
    @Reference
    private UserInfoService userInfoService;

    Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @RequestMapping("/sendCode/{phone}")
    public Result sendCode(@PathVariable("phone") String phone){
        // 1. 生成一个4位数字的随机验证码
        StringBuilder codeBuilder = new StringBuilder("");
        for (int i = 0; i < 4; i++) {
            codeBuilder.append((int)(Math.random()*10));
        }
        String code = codeBuilder.toString();
        // 2. 将验证码通过云短信发送给用户(直接打印出来)
        logger.info(phone+"的验证码为:{}",code);
        //3. 将验证码保存到redis中，并且设置过期时间
        Jedis jedis = jedisPool.getResource();
        jedis.setex("code:" + phone, 60 * 5, code);
        jedis.close();
        //4. 响应给客户端:验证码发送成功
        return Result.ok();
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        //1. 校验验证码是否正确
        // 获取jedis对象
        Jedis jedis = jedisPool.getResource();
        //2. 查询redis中的验证码
        String code = jedis.get("code:" + registerVo.getPhone());
        //3. 对比验证码
        if (registerVo.getCode().equals(code)) {
            //如果验证码正确,则调用业务层的方法执行注册
            return userInfoService.doRegister(registerVo);
        }else{
            //如果验证码不正确
            return Result.build(null, ResultCodeEnum.CODE_ERROR);
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo, HttpSession session){
        //1. 校验手机号是否存在
        Result result = userInfoService.doLogin(loginVo);
        //将登录状态保存到session中
        if (Objects.equals(result.getCode(), ResultCodeEnum.SUCCESS.getCode())) {
            //登录成功,将登录的用户信息保存到session中
            session.setAttribute("USER",result.getData());
        }
        return result;
    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session){
        //1. 将session摧毁
        session.invalidate();
        return Result.ok();
    }
}
