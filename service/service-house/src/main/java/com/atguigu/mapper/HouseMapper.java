package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.House;
import com.atguigu.entity.vo.HouseQueryVo;
import com.atguigu.entity.vo.HouseVo;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.mapper.HouseMapper
 * @Create 2023/6/4:19:35
 * @Author WCH
 * Description:
 */
public interface HouseMapper extends BaseMapper<House> {
    /**
     * 根据hse_house的Id查询房源数量
     * @param communityId
     * @return
     */
    Integer findHouseCountByCommunityId(Integer communityId);

    /**
     * 搜索前台房源列表
     * @param houseQueryVo
     * @return
     */
    List<HouseVo> findFrontPageList(HouseQueryVo houseQueryVo);
}
