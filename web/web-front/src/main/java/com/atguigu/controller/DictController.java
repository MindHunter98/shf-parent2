package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.entity.Dict;
import com.atguigu.result.Result;
import com.atguigu.service.DictService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @FileName com.atguigu.controller.DictController
 * @Create 2023/6/7:16:42
 * @Author WCH
 * Description:
 */
@RestController
@RequestMapping("/dict")
public class DictController {

    @Reference
    private DictService dictService;


    @GetMapping("/findDictListByParentDictCode/{dictCode}")
    public Result findDictListByParentDictCode(@PathVariable("dictCode")String dictCode){
        //1. 远程调用业务层的方法根据父节点的dictCode查询子节点列表
        List<Dict> dictList = dictService.selectByParentDictCode(dictCode);
        //2. 将dictList封装到Result对象中，并返回
        return Result.ok(dictList);
    }

    @GetMapping("/findDictListByParentId/{parentId}")
    public Result findDictListByParentId(@PathVariable("parentId")Integer parentId){
        List<Dict> dictList = dictService.selectDictListByParentId(parentId);
        return Result.ok(dictList);
    }
}
