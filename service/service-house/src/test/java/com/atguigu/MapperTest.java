package com.atguigu;


import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.mapper.*;
import com.atguigu.service.CommunityService;
import com.atguigu.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName com.atguigu.MapperTest
 * @Create 2023/6/1:23:10
 * @Author WCH
 * Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class MapperTest {
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private HouseBrokerMapper houseBrokerMapper;
    @Autowired
    private HouseUserMapper houseUserMapper;
    @Autowired
    private HouseImageMapper houseImageMapper;

    @Test
    public void test01(){
        System.out.println(dictMapper.selectByParentId(1L));
    }

    @Test
    public void test02(){
        Map<String,String> map = new HashMap<>();
        map.put("name","泰");
        System.out.println(communityMapper.findPageList(map));
    }

    @Test
    public void test03(){
        Map<String,String> map = new HashMap<>();
        map.put("address","试");
        System.out.println(communityService.findPage(map));
    }

    @Test
    public void test04(){
        System.out.println(houseMapper.findById(5));
    }

    @Test
    public void test05(){
        System.out.println(houseBrokerMapper.findHouseBrokerByHouseId(2));
    }

    @Test
    public void test06(){
        System.out.println(houseImageMapper.findHouseImageByHouseId(2,1));
    }

    @Test
    public void test07(){
        System.out.println(houseUserMapper.findHouseUserByHouseId(2));
    }



}
