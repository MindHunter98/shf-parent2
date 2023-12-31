package com.atguigu.entity.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * 登录对象
 */
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;
}
