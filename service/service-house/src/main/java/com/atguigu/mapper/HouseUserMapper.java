package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.HouseImage;
import com.atguigu.entity.HouseUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @FileName com.atguigu.mapper.houseUserMapper
 * @Create 2023/6/6:16:14
 * @Author WCH
 * Description:
 */
public interface HouseUserMapper extends BaseMapper<HouseUser> {
    void deleteByHouseId(Integer id);

    /**
     * 根据houseId查询房东列表
     * @param houseId
     * @return
     */
    List<HouseUser> findHouseUserByHouseId(Integer houseId);
}
