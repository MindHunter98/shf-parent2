package com.atguigu.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.base.BaseMapper;
import com.atguigu.base.BaseServiceImpl;
import com.atguigu.entity.Dict;
import com.atguigu.mapper.DictMapper;
import com.atguigu.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @FileName com.atguigu.service.impl.DictServiceImpl
 * @Create 2023/6/1:23:31
 * @Author WCH
 * Description:
 */
@Service(interfaceClass = DictService.class)
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {
    @Autowired
    private DictMapper dictMapper;
    @Override
    public BaseMapper<Dict> getBaseMapper() {
        return dictMapper;
    }


    /**
     * 根据parent_id查询子节点列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Map<String, Object>> selectByParentId(Long parentId) {
        //1. 调用持久层的方法根据父节点id查询子节点列表
        List<Dict> dictList = dictMapper.selectByParentId(parentId);
        //2. 进行数据的转换将dictList转成List<Map<String, Object>>:包含三个键值对id,name,isParent
        if (CollectionUtils.isNotEmpty(dictList)) {
            //3. 使用Stream进行映射
            return dictList.stream()
                    .map(dict -> {
                        //3.1 创建Map集合
                        Map<String,Object> map= new HashMap<>();
                        //3.2 设置键值对
                        map.put("id",dict.getId());
                        map.put("name",dict.getName());
                        //判断当前dict是否是父节点:以当前dict的id作为parent_id查询子节点列表，如果子节点列表不为空，则说明当前dict是父节点
                        /*if (CollectionUtils.isNotEmpty(dictMapper.selectByParentId(dict.getId()))) {
                            map.put("isParent",true);
                        }else {
                            map.put("isParent",false);
                        }*/
                        map.put("isParent",CollectionUtils.isNotEmpty(dictMapper.selectByParentId(dict.getId())));
                        //3.3 返回map
                        return map;
                    }).collect(Collectors.toList());
        }
        //如果没有子节点，则返回一个空集合
        return new ArrayList<>();
    }

    /**
     * 根据父节点的dict_code查询子节点列表
     *
     * @param dictCode
     * @return
     */
    @Override
    public List<Dict> selectByParentDictCode(String dictCode) {
        List<Dict> dictList = dictMapper.selectByParentDictCode(dictCode);
        return dictList;
    }

    /**
     * 根据父节点的id查询子节点列表
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Dict> selectDictListByParentId(Integer parentId) {
        List<Dict> dictList = dictMapper.selectByParentId(Long.valueOf(parentId));
        return dictList;
    }

}

