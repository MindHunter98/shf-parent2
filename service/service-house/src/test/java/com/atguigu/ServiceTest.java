package com.atguigu;

import com.atguigu.mapper.DictMapper;
import com.atguigu.mapper.HouseBrokerMapper;
import com.atguigu.mapper.HouseUserMapper;
import com.atguigu.service.DictService;
import com.atguigu.service.HouseImageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @FileName com.atguigu.ServiceTest
 * @Create 2023/6/2:00:01
 * @Author WCH
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class ServiceTest {
    @Autowired
    private DictService dictService;
    @Autowired
    private HouseImageService houseImageService;
    @Autowired
    private HouseUserMapper houseUserMapper;
    @Autowired
    private HouseBrokerMapper houseBrokerMapper;

    @Test
    public void test01(){
        System.out.println(dictService.selectByParentId(10000L));
    }

    @Test
    public void test02(){

    }
}
