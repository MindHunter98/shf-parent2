package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.en.HouseImageType;
import com.atguigu.en.HouseStatus;
import com.atguigu.entity.*;
import com.atguigu.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.controller.HouseController
 * @Create 2023/6/4:19:33
 * @Author WCH
 * Description:
 */
@Controller
@RequestMapping("/house")
public class HouseController extends BaseController {
    private static final String PAGE_INDEX = "house/index";
    private static final String PAGE_CREATE = "house/create";
    private static final String PAGE_EDIT = "house/edit";
    private static final String LIST_ACTION = "redirect:/house";
    private static final String PAGE_SHOW = "house/show";
    @Reference
    private HouseService houseService;
    @Reference
    private DictService dictService;
    @Reference
    private CommunityService communityService;
    @Reference
    private HouseImageService houseImageService;
    @Reference
    private HouseBrokerService houseBrokerService;
    @Reference
    private HouseUserService houseUserService;

    @RequestMapping
    public String index(@RequestParam Map<String, String> filters, Model model) {
        Map<String, Object> houseIndexInfo = houseService.findHouseIndexInfo(filters);
        model.addAllAttributes(houseIndexInfo);
        //3. 将搜索条件存入request域中
        model.addAttribute("filters", filters);
        //5. 返回小区列表页面的逻辑视图名
        return PAGE_INDEX;
    }

    @RequestMapping("/create")
    public String create(@RequestParam Map<String, String> filters, Model model) {
        //查询初始化列表
        Map<String, Object> initList = houseService.findInitList();
        //将initList存储到请求域中
        model.addAllAttributes(initList);
        //返回新增小区列表页面的逻辑视图名
        return PAGE_CREATE;
    }

    @PostMapping("/save")
    public String save(House house, Model model) {
        //1. 调用业务层的方法保存房源信息
        //保存之前手动给status字段赋值为未发布
        house.setStatus(HouseStatus.UNPUBLISHED.getCode());
        houseService.insert(house);
        //2. 显示成功页面
        return successPage("添加房源信息成功",model);
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        //1. 调用业务层的方法根据id查询房源信息
        House house = houseService.findById(id);
        //2. 将房源信息存入request域,这里的数据不包括复选框内的数据
        model.addAttribute("house",house);
        //3.调用业务层的方法查询复选框内的所有数据
        Map<String, Object> initList = houseService.findInitList();
        //4. 将复选框内的数据存入request域
        model.addAllAttributes(initList);
        //5. 显示修改页面
        return PAGE_EDIT;
    }

    @PostMapping("/update")
    //此处的house由前端的修改界面传入而来
    public String update(House house,Model model){
        //1. 调用业务层的方法更新房源信息
        houseService.update(house);
        //2. 显示成功页面
        return successPage("修改房源信息成功",model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        //1. 调用业务层的方法更新房源信息的is_deleted为0
        houseService.delete(id);
        //2. 显示删除后的页面
        return LIST_ACTION;
    }

    @GetMapping("/publish/{id}/{status}")
    public String publish(@PathVariable("id") Integer id,@PathVariable("status") Integer status){
        //1. 调用业务层的方法发布或者取消发布房源
        House house = new House();
        house.setId(Long.valueOf(id));
        house.setStatus(status);
        houseService.update(house);
        //2. 重定向访问房源首页
        return LIST_ACTION;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id,Model model){
        //1. 调用业务层的方法根据id查询hse_house中的所有数据(需要子查询hse_dict)
        House house = houseService.findById(id);
        model.addAttribute("house",house);
        //2. 调用业务层的方法根据id查询hse_community中的所有数据(需要子查询hse_dict)
        Community community = communityService.findById(Math.toIntExact(house.getCommunityId()));
        model.addAttribute("community",community);
        //3. 查询[房源]的房源图片列表
        List<HouseImage> houseImage1List = houseImageService.findHouseImageByHouseId(id, HouseImageType.HOUSE_SOURCE.getTypeCode());
        model.addAttribute("houseImage1List",houseImage1List);
        //4. 查询[房产]的房产图片
        List<HouseImage> houseImage2List = houseImageService.findHouseImageByHouseId(id, HouseImageType.HOUSE_PROPERTY.getTypeCode());
        model.addAttribute("houseImage2List",houseImage2List);
        //5. 查询[经纪人]的列表
        List<HouseBroker> houseBrokerList = houseBrokerService.findHouseBrokerByHouseId(id);
        model.addAttribute("houseBrokerList",houseBrokerList);
        //6. 查询[房东]的列表
        List<HouseUser> houseUserList = houseUserService.findHouseUserByHouseId(id);
        model.addAttribute("houseUserList",houseUserList);
        return PAGE_SHOW;
    }
}
