package com.atguigu.service;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Community;
import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.service.DictService
 * @Create 2023/6/1:23:22
 * @Author WCH
 * Description:
 */
public interface DictService{
    /**
     * 根据parent_id查询子节点列表
     * @param parentId
     * @return
     */
    List<Map<String, Object>> selectByParentId(Long parentId);

    /**
     * 根据父节点的dict_code查询子节点列表
     * @param dictCode
     * @return
     */
    List<Dict> selectByParentDictCode(String dictCode);

    /**
     * 根据父节点的id查询子节点列表
     * @param parentId
     * @return
     */
    List<Dict> selectDictListByParentId(Integer parentId);

}
