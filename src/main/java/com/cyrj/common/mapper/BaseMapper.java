package com.cyrj.common.mapper;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;


public interface BaseMapper<T> {

    /**
     * <p>
     * 查询（根据 t 查询）
     * </p>
     *
     * @param t
     * @return 实体
     */
    T getById(T t);

    /**
     * <p>
     * 查询（根据 t查询）
     * </p>
     *
     * @param t
     * @return 实体
     */
    T getByObj(T t);

    /**
     * <p>
     * 查询（根据 map 条件）
     * </p>
     *
     * @param map
     * @return 实体
     */
    T getMapByObj(Map<String, Object> map);

    /**
     * <p>
     * 查询（根据 map 条件）
     * </p>
     *
     * @param map
     * @return map对象
     */
    Map<String, Object> getMapByMap(Map<String, Object> map);


    /**
     * <p>
     * 查询（根据 map 条件）
     * </p>
     *
     * @param  map 对象
     * @return 返回实体对象集合
     */
    List<T> findListObjByMap(Map<String, Object> map);

    /**
     * <p>
     * 查询（根据 map 条件）
     * </p>
     *
     * @param  map 对象
     * @return 返回map对象集合
     */
    List<Map<String, Object>> findListByMap(Map<String, Object> map);
    
    public int findCountByMap(Map map);


    /**
     * 插入一条记录
     *
     * @param t 实体对象
     * @return 返回记录id
     */
    int add(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param t 实体对象
     * @return 修改成功记录数
     */
    int update(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * <p>
     * 根据 t 实体对象，删除记录
     * </p>
     *
     * @param  t 对象
     * @return 删除成功记录数
     */
    int delete(T t);

    /**
     * <p>
     * 根据 map 条件，删除记录
     * </p>
     *
     * @param  map 对象
     * @return 删除成功记录数
     */
    int delete(Map<String, Object> map);
}
