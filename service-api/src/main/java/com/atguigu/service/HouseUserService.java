package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.HouseUser;

import java.util.List;

/**
 * @FileName com.atguigu.service.HouseUserService
 * @Create 2023/6/6:16:24
 * @Author WCH
 * Description:
 */
public interface HouseUserService extends BaseService<HouseUser> {

    /**
     * 根据houseId查询房东列表
     * @param houseId
     * @return
     */
    List<HouseUser> findHouseUserByHouseId(Integer houseId);
}