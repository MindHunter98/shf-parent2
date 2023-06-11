package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @FileName com.atguigu.controller.DictController
 * @Create 2023/6/2:00:07
 * @Author WCH
 * Description:
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    private static final String PAGE_INDEX = "dict/index";
    @Reference
    private DictService dictService;


    @RequestMapping("/findZnodes")
    public Result findZnodes(@RequestParam (value = "id",defaultValue = "0") Long id){
        //id其实就是携带的父节点id,如果没有携带，那么就赋默认值为0
        //根据父节点id查询子节点集合
        List<Map<String, Object>> mapList = dictService.selectByParentId(id);
        //将mapList封装到Result对象中,转换成json数据
        return Result.ok(mapList);
    }


    @RequestMapping("/findDictListByParentId/{parentId}")
    public Result findListByParentId(@PathVariable("parentId") Long parentId){
        //根据父节点id查询子节点集合
        //选择区域后的板块选择
        List<Map<String, Object>> mapList = dictService.selectByParentId(parentId);
        //将mapList封装到Result对象中,转换成json数据
        return Result.ok(mapList);
    }
}
