package cn.tycoding.service;

import java.util.List;

/**
 * 通用的service接口
 *
 * @auther TyCoding
 * @date 2018/9/22
 */
public interface BaseService<T> {

    /**
     * 查询所有
     *
     * @return
     */
    List<T> findAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    List<T> findById(Long id);

    /**
     * 新增
     *
     * @param t
     */
    void create(T t);

    /**
     * 更新
     *
     * @param t
     */
    void update(T t);

    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Long... ids);
}
