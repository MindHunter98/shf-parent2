package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.en.DictCode;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;
import com.atguigu.service.CommunityService;
import com.atguigu.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.controller.CommunityController
 * @Create 2023/6/2:19:52
 * @Author WCH
 * Description:
 */

@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    private static final String PAGE_INDEX = "community/index";
    private static final String PAGE_CREATE = "community/create";
    private static final String PAGE_EDIT = "community/edit";
    private static final String LIST_ACTION = "redirect:/community";
    @Reference
    private CommunityService communityService;
    @Reference
    private DictService dictService;

    @RequestMapping
    public String index(@RequestParam Map<String,String> filters,Model model){
        //1. 调用业务层的方法分页查询Admin
        PageInfo<Community> page = communityService.findPage(filters);
        //2. 将分页数据存入请求域中
        model.addAttribute("page",page);
        //3. 将搜索条件存入request域中
        //如果搜索条件中没有areaId和plateId，则给filters中添加默认值为0
        filters.putIfAbsent("areaId","0");
        filters.putIfAbsent("plateId","0");
        model.addAttribute("filters",filters);
        //4. 查询北京的所有区域信息,存入request域中
        //调用DictService的方法根据父节点的dictCode查询子节点列表
        List<Dict> areaList = dictService.selectByParentDictCode(DictCode.BEIJING.getCode());
        model.addAttribute("areaList",areaList);
        //5. 返回小区列表页面的逻辑视图名
        return PAGE_INDEX;
    }

    /**
     * 访问新增页面,并且异步访问dict字典,使得可以选择区域和板块
     * @param model
     * @return
     */
    @RequestMapping("/create")
    public String create(Model model){
        //1. 查询北京的所有区域信息,存入request域中
        //调用DictService的方法根据父节点的dictCode查询子节点列表
        List<Dict> areaList = dictService.selectByParentDictCode(DictCode.BEIJING.getCode());
        model.addAttribute("areaList",areaList);
        //2. 返回小区列表页面的逻辑视图名
        return PAGE_CREATE;
    }

    /**
     * 填写完新增页面后,点击提交,访问/community/save
     * @param community
     * @param model
     * @return
     */
    @PostMapping("/save")
    public String save(Community community,Model model){
        //1. 调用业务层的方法新增小区信息
        communityService.insert(community);
        //2. 返回逻辑视图
        return successPage("新增小区信息成功",model);
    }

    /**
     * 访问修改页面
     * @param id
     * @return
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        //1. 调用findById方法查询路径传入id的数据
        Community community = communityService.findById(id);
        model.addAttribute("community",community);
        //2. 调用DictService的方法根据父节点的dictCode查询子节点列表
        List<Dict> areaList = dictService.selectByParentDictCode(DictCode.BEIJING.getCode());
        model.addAttribute("areaList",areaList);
        //3. 返回修改小区列表页面的逻辑视图名
        return PAGE_EDIT;
    }

    /**
     * 修改修改页面
     * @param community
     * @param model
     * @return
     */
    @PostMapping("/update")
    public String update(Community community, Model model){
        //1. 调用update方法将输入的数据提交
        communityService.update(community);
        //2. 返回修改成功的逻辑视图名
        return successPage("修改小区信息成功",model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        //1. 调用delete方法删除小区信息
        communityService.delete(id);
        //2. 返回删除成功的页面
        return LIST_ACTION;
    }

}
