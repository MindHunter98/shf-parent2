package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseService;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.HouseImage;
import com.atguigu.mapper.HouseImageMapper;
import com.atguigu.service.HouseImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @FileName com.atguigu.service.impl.HouseImageServiceImpl
 * @Create 2023/6/6:21:15
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = HouseImageService.class)
public class HouseImageServiceImpl extends BaseServiceImpl<HouseImage> implements HouseImageService {
    @Autowired
    private HouseImageMapper houseImageMapper;
    @Override
    public BaseMapper<HouseImage> getBaseMapper() {
        return houseImageMapper;
    }

    /**
     * 根据houseId查询房屋图片列表,根据type查询房源图片(1)或房产图片(2)
     *
     * @param houseId
     * @param type
     * @return
     */
    @Override
    public List<HouseImage> findHouseImageByHouseId(Integer houseId, Integer type) {
        return houseImageMapper.findHouseImageByHouseId(houseId,type);
    }
}
