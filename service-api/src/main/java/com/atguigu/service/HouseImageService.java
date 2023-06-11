package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.HouseImage;

import java.util.List;

/**
 * @FileName com.atguigu.service.HouseImageMapper
 * @Create 2023/6/6:16:23
 * @Author WCH
 * Description:
 */
public interface HouseImageService extends BaseService<HouseImage> {
    /**
     * 根据houseId查询房屋图片列表,根据type查询房源图片(1)或房产图片(2)
     * @param houseId
     * @param type
     * @return
     */
    List<HouseImage> findHouseImageByHouseId(Integer houseId, Integer type);
}
