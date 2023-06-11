package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.HouseUser;
import com.atguigu.mapper.HouseUserMapper;
import com.atguigu.service.HouseUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @FileName com.atguigu.service.impl.HouseUserServiceImpl
 * @Create 2023/6/6:22:56
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = HouseUserService.class)
public class HouseUserServiceImpl extends BaseServiceImpl<HouseUser> implements HouseUserService {
    @Autowired
    private HouseUserMapper houseUserMapper;
    @Override
    public BaseMapper<HouseUser> getBaseMapper() {
        return houseUserMapper;
    }

    /**
     * 根据houseId查询房东列表
     *
     * @param houseId
     * @return
     */
    @Override
    public List<HouseUser> findHouseUserByHouseId(Integer houseId) {
        return houseUserMapper.findHouseUserByHouseId(houseId);
    }
}
