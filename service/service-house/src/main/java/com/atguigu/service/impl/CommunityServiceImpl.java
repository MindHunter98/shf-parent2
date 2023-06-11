package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Community;
import com.atguigu.mapper.CommunityMapper;
import com.atguigu.mapper.HouseMapper;
import com.atguigu.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @FileName com.atguigu.service.impl.CommunityServiceImpl
 * @Create 2023/6/2:17:42
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = CommunityService.class)
public class CommunityServiceImpl extends BaseServiceImpl<Community> implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public BaseMapper<Community> getBaseMapper() {
        return communityMapper;
    }

    @Override
    public boolean delete(Integer id){
        //1. 判断要删除的小区是否有房源，如果有，则不能删除
        //1.1 根据小区id查询房源的数量，如果数量大于0，则说明这个小区下有房源，则不能删除
        if (houseMapper.findHouseCountByCommunityId(id) > 0) {
            //不能删
            throw new RuntimeException("community has house,can not delete");
        }
        //否则,能删除
        return super.delete(id);
    }

}
