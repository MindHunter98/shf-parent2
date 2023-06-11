package com.atguigu.service;


import com.atguigu.base.BaseService;
import com.atguigu.entity.HouseBroker;

import java.util.List;

/**
 * @FileName com.atguigu.service.HouseBrokerService
 * @Create 2023/6/6:16:23
 * @Author WCH
 * Description:
 */
public interface HouseBrokerService extends BaseService<HouseBroker> {
    /**
     * 根据houseId查询房产经纪人列表
     * @param houseId
     * @return
     */
    List<HouseBroker> findHouseBrokerByHouseId(Integer houseId);
}
