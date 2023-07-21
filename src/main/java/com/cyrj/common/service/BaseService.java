package com.cyrj.common.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.cyrj.common.pojo.BillMultiUnit;
import com.github.pagehelper.PageInfo;

public interface BaseService<T> {

	public T getById(int id);
	
	public T getById(T t);

    public T getByObj(T t);

    public Map<String, Object> getMapById(T t);

    public T getMapByObj(Map<String, Object> map);

    public Map<String, Object> getMapByMap(Map<String, Object> map);

    public List<T> findListObjByMap(Map<String, Object> map);

    public List<Map<String, Object>> findListByMap(Map<String, Object> map);

    /**
     * <p>
     * 分页查询（根据 map 条件）
     * </p>
     *
     * @param  map 对象
     * @return 返回分页实体对象
     */
    PageInfo<T> findListPageObjByMap(Map<String, Object> map,Integer pageNum,Integer pageSize);

    /**
     * <p>
     * 分页查询（根据 map 条件）
     * </p>
     *
     * @param  map 对象
     * @return 返回分页实体对象
     */
    PageInfo<Map<String, Object>> findListPageByMap(Map<String, Object> map,Integer pageNum,Integer pageSize);

    /**
     * 插入一条记录
     *
     * @param t 实体对象
     * @return 返回记录id
     */
    Map<String, Object> add(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param t 实体对象
     * @return 修改成功记录数
     */
    Map<String, Object> update(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    
    public Map save(T t) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    /**
     * <p>
     * 根据 t 实体对象，删除记录
     * </p>
     *
     * @param  t 对象
     * @return 返回操作结果
     */
    Map<String, Object> delete(T t);

    /**
     * <p>
     * 根据 map 条件，删除记录
     * </p>
     *
     * @param  map 对象
     * @return 返回操作结果
     */
    Map<String, Object> delete(Map<String, Object> map);

    public BillMultiUnit convertMultiUnit(BillMultiUnit multiUnit);
    
    
}
