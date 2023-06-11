package com.atguigu.service;

import com.atguigu.base.BaseService;
import com.atguigu.entity.House;
import com.atguigu.entity.vo.HouseQueryVo;
import com.atguigu.entity.vo.HouseVo;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @FileName com.atguigu.service.HouseService
 * @Create 2023/6/4:19:37
 * @Author WCH
 * Description:
 */
public interface HouseService extends BaseService<House> {
    /**
     * 查询房源首页的信息
     *
     * @param filter
     * @return
     */
    Map<String, Object> findHouseIndexInfo(Map<String, String> filter);

    Map<String, Object> findInitList();

    /**
     * 用户前台查看房源分页信息
     * @param pageNum
     * @param pageSize
     * @param houseQueryVo
     * @return
     */
    PageInfo<HouseVo> findListPage(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo);

    /**
     * 查询房源详情
     * @param houseId
     * @return
     */
    Map<String, Object> findHouseDetail(Integer houseId);
}
