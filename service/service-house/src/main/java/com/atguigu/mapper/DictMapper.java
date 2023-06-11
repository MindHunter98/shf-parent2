package com.atguigu.mapper;

import com.atguigu.base.BaseMapper;
import com.atguigu.entity.Dict;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.mapper.DictMapper
 * @Create 2023/6/1:23:03
 * @Author WCH
 * Description:
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 根据父节点id查询子节点列表
     * @param parentId
     * @return
     */
    List<Dict> selectByParentId(Long parentId);

    /**
     * 根据父节点的dict_code查询子节点列表
     * @param dictCode
     * @return
     */
    List<Dict> selectByParentDictCode(String dictCode);

}
