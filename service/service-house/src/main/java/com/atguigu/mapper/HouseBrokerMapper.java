package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseBroker;

import java.util.List;

/**
 * @FileName com.atguigu.mapper.houseBrokerMapper
 * @Create 2023/6/6:16:14
 * @Author WCH
 * Description:
 */
public interface HouseBrokerMapper extends BaseMapper<HouseBroker> {
    void deleteByHouseId(Integer id);

    /**
     * 根据houseId查询房产经纪人列表
     * @param houseId
     * @return
     */
    List<HouseBroker> findHouseBrokerByHouseId(Integer houseId);

}
