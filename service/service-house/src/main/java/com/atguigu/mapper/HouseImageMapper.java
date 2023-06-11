package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @FileName com.atguigu.mapper.houseImageMapper
 * @Create 2023/6/6:16:13
 * @Author WCH
 * Description:
 */
public interface HouseImageMapper extends BaseMapper<HouseImage> {
    void deleteByHouseId(Integer id);

    /**
     * 根据houseId查询房屋图片列表,根据type查询房源图片(1)或房产图片(2)
     * @param houseId
     * @param type
     * @return
     */
    List<HouseImage> findHouseImageByHouseId(@Param("houseId") Integer houseId, @Param("type") Integer type);
}
