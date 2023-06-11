package com.atguigu.base;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.base.BaseMapper
 * @Create 2023/5/31:11:24
 * @Author WCH
 * Description:
 */
public interface BaseMapper<T> {
    /**
     * 新增
     * @param t
     * @return
     */
    boolean insert(T t);

    /**
     * 修改
     * @param t
     * @return
     */
    boolean update(T t);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 查询所有
     * @return 存储在List集合中
     */
    List<T> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 根据条件搜索分页列表
     * @param filters
     * @return
     */
    List<T> findPageList(Map<String, String> filters);
}
