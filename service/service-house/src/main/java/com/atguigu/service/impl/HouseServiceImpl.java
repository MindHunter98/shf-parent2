package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.en.DictCode;
import com.atguigu.en.HouseImageType;
import com.atguigu.entity.*;
import com.atguigu.entity.vo.HouseQueryVo;
import com.atguigu.entity.vo.HouseVo;
import com.atguigu.mapper.*;
import com.atguigu.service.HouseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.service.impl.HouseServiceImpl
 * @Create 2023/6/4:19:36
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = HouseService.class)
public class HouseServiceImpl extends BaseServiceImpl<House> implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private CommunityMapper communityMapper;
    @Autowired
    private DictMapper dictMapper;
    @Autowired
    private HouseImageMapper houseImageMapper;
    @Autowired
    private HouseBrokerMapper houseBrokerMapper;
    @Autowired
    private HouseUserMapper houseUserMapper;

    @Override
    public BaseMapper<House> getBaseMapper() {
        return houseMapper;
    }

    /**
     * 查询房源首页的信息
     *
     * @param filters
     * @return
     */
    @Override
    public Map<String, Object> findHouseIndexInfo(Map<String, String> filters) {
        //1. 查询房源的分页信息
        PageInfo<House> page = this.findPage(filters);
        //2. 查询初始化列表
        Map<String, Object> map = initList();
        map.put("page", page);
        //3. 将搜索条件存储到map中
        map.put("filters", filters);
        return map;
    }

    @Override
    public Map<String, Object> findInitList() {
        return initList();
    }

    /**
     * 用户前台查看房源分页信息
     *
     * @param pageNum
     * @param pageSize
     * @param houseQueryVo
     * @return
     */
    @Override
    public PageInfo<HouseVo> findListPage(Integer pageNum, Integer pageSize, HouseQueryVo houseQueryVo) {
        //1. 开启分页
        PageHelper.startPage(pageNum,pageSize);
        //2. 调用持久层的方法进行搜索房源列表
        List<HouseVo> houseVoList = houseMapper.findFrontPageList(houseQueryVo);
        return new PageInfo<>(houseVoList,10);
    }

    /**
     * 查询房源详情
     *
     * @param houseId
     * @return
     */
    @Override
    public Map<String, Object> findHouseDetail(Integer houseId) {
        //1. 查询房源详情
        House house = houseMapper.findById(houseId);
        //2. 查询小区详情
        Community community = communityMapper.findById(Math.toIntExact(house.getCommunityId()));
        //3. 查询经纪人列表
        List<HouseBroker> houseBrokerList = houseBrokerMapper.findHouseBrokerByHouseId(houseId);
        //4. 查询房东列表
        List<HouseUser> houseUserList = houseUserMapper.findHouseUserByHouseId(houseId);
        //5. 查询房源图片
        List<HouseImage> houseImage1List = houseImageMapper.findHouseImageByHouseId(houseId, HouseImageType.HOUSE_SOURCE.getTypeCode());
        //6. 创建一个map,将数据存入map集合中
        Map<String,Object> map = new HashMap<>();
        map.put("house",house);
        map.put("community",community);
        map.put("houseBrokerList",houseBrokerList);
        map.put("houseUserList",houseUserList);
        map.put("houseImage1List",houseImage1List);
        return map;
    }


    /**
     * 查询初始化列表，并且返回Map,只包括列表不包括分页信息
     */
    private Map<String, Object> initList() {
        //1. 查询房源中所有的小区
        List<Community> communityList = communityMapper.findAll();
        //2. 查询所有户型
        List<Dict> houseTypeList = dictMapper.selectByParentDictCode(DictCode.HOUSETYPE.getCode());
        //3. 查询所有楼层
        List<Dict> floorList = dictMapper.selectByParentDictCode(DictCode.FLOOR.getCode());
        //4. 查询所有建筑结构
        List<Dict> buildStructureList = dictMapper.selectByParentDictCode(DictCode.BUILDSTRUCTURE.getCode());
        //5. 查询所有朝向
        List<Dict> directionList = dictMapper.selectByParentDictCode(DictCode.DIRECTION.getCode());
        //6. 查询所有装修情况
        List<Dict> decorationList = dictMapper.selectByParentDictCode(DictCode.DECORATION.getCode());
        //7. 查询所有房屋用途
        List<Dict> houseUseList = dictMapper.selectByParentDictCode(DictCode.HOUSEUSE.getCode());

        Map<String, Object> map = new HashMap<>();
        map.put("communityList", communityList);
        map.put("houseTypeList", houseTypeList);
        map.put("floorList", floorList);
        map.put("buildStructureList", buildStructureList);
        map.put("directionList", directionList);
        map.put("decorationList", decorationList);
        map.put("houseUseList", houseUseList);
        return map;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean delete(Integer id) {
        //1. 根据房源id删除房源的图片
        houseImageMapper.deleteByHouseId(id);
        //2. 根据房源id删除房源与经纪人的关联信息
        houseBrokerMapper.deleteByHouseId(id);
        //3. 根据房源id删除房源与房东的关联信息
        houseUserMapper.deleteByHouseId(id);
        //4. 根据id删除房源
        return super.delete(id);
    }
}
