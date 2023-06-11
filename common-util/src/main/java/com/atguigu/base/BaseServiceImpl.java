package com.atguigu.base;

import com.atguigu.util.CastUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @FileName com.atguigu.base.BaseServiceImpl
 * @Create 2023/5/31:19:26
 * @Author WCH
 * Description:
 */
public abstract class BaseServiceImpl<T> {

    public abstract BaseMapper<T> getBaseMapper();

    /**
     * 提取出来的分页查询
     * @param filters
     * @return
     */
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public PageInfo<T> findPage(Map<String, String> filters) {
        //1. 使用分页插件开启分页
        PageHelper.startPage(CastUtil.castInt(filters.get("pageNum"),1),
                CastUtil.castInt(filters.get("pageSize"),10));
        //2. 调用持久层的方法根据搜索条件进行搜索
        List<T> List = getBaseMapper().findPageList(filters);
        //10代表最多显示10个页码
        return new PageInfo<>(List,10);
    }

    /**
     * 新增
     * @param t
     * @return
     */
    @Transactional
    public boolean insert(T t) {
        return getBaseMapper().insert(t);
    }

    /**
     * 修改
     * @param t
     * @return
     */
    @Transactional
    public boolean update(T t) {
        return getBaseMapper().update(t);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional
    public boolean delete(Integer id) {
        return getBaseMapper().delete(id);
    }

    /**
     * 查询所有
     * @return 存储在List集合中
     */
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<T> findAll() {
        return getBaseMapper().findAll();
    }

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public T findById(Integer id) {
        return getBaseMapper().findById(id);
    }
}
