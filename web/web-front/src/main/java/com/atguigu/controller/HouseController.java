package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.vo.HouseQueryVo;
import com.atguigu.entity.vo.HouseVo;
import com.atguigu.result.Result;
import com.atguigu.service.HouseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @FileName com.atguigu.controller.HouseController
 * @Create 2023/6/7:21:07
 * @Author WCH
 * Description:
 */
@RestController
@RequestMapping("/house")
public class HouseController {
    @Reference
    private HouseService houseService;
    /**
     * 根据条件查询房源分页信息
     * @param pageNum
     * @param pageSize
     * @param houseQueryVo
     * @return
     */
    @RequestMapping("/list/{pageNum}/{pageSize}")
    public Result findPage(@PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize,
                           @RequestBody HouseQueryVo houseQueryVo){
        //1. 调用业务层的方法查询房源的分页信息
        PageInfo<HouseVo> page = houseService.findListPage(pageNum,pageSize,houseQueryVo);
        return Result.ok(page);
    }

    @RequestMapping("/info/{houseId}")
    public Result info(@PathVariable("houseId") Integer houseId){
        //1. 远程调用业务层的方法查询房源详情
        Map<String,Object> map = houseService.findHouseDetail(houseId);
        //2. 远程调用会员服务查询当前会员是否关注了这个房源 TODO
        //默认设置为false
        map.put("isFollow",false);
        return Result.ok(map);
    }

}
