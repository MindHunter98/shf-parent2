package com.atguigu.base;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.base.BaseService
 * @Create 2023/5/31:19:21
 * @Author WCH
 * Description:
 */
public interface BaseService<T> {
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
     * 根据ID查找
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 分页查询
     * @param filters
     * @return
     */
    PageInfo<T> findPage(Map<String, String> filters);
}
