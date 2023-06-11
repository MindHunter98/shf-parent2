package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.HouseBroker;
import com.atguigu.entity.HouseImage;
import com.atguigu.mapper.HouseBrokerMapper;
import com.atguigu.mapper.HouseImageMapper;
import com.atguigu.service.HouseBrokerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @FileName com.atguigu.service.impl.HouseBrokerServiceImpl
 * @Create 2023/6/6:22:50
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = HouseBrokerService.class)
public class HouseBrokerServiceImpl extends BaseServiceImpl<HouseBroker> implements HouseBrokerService {
    @Autowired
    private HouseBrokerMapper houseBrokerMapper;
    @Override
    public BaseMapper<HouseBroker> getBaseMapper() {
        return houseBrokerMapper;
    }

    /**
     * 根据houseId查询房产经纪人列表
     *
     * @param houseId
     * @return
     */
    @Override
    public List<HouseBroker> findHouseBrokerByHouseId(Integer houseId) {
        return houseBrokerMapper.findHouseBrokerByHouseId(houseId);
    }
}
