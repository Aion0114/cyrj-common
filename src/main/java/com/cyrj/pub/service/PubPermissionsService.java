package com.cyrj.pub.service;

import java.util.List;
import java.util.Map;

public interface PubPermissionsService {

    /**
     * 机构权限id集合
     * @param map
     * @return
     */
    List<Integer> queryOrganizationIds(Map map);

    /**
     * 仓库权限id集合
     * @param map
     * @return
     */
    List<Integer> queryStorehouseIds(Map map);

    /**
     * 商品标签权限
     * @param map
     * @return
     */
    List<Integer> queryGoodsLabelRelevanceIds(Map map);

    /**
     * 客户标签权限
     * @param map
     * @return
     */
    List<Integer> queryCustomerLabelRelevanceIds(Map map);

    /**
     * 供应商标签权限
     * @param map
     * @return
     */
    public List<Integer> querySupplierLabelRelevanceIds(Map map);

    /**
     * 供应商标签权限
     * @param map
     * @return
     */
    public List<Integer> queryEmployeeLabelRelevanceIds(Map map);

    /**
     * 客户/商品标签获取客户/商品Id(不根据用户)
     * @param map
     * @return
     */
    List<Integer> queryLabelRelevanceIds(Map map);

    /**
     * 货位
     * @param map
     * @return
     */
    List<Integer> queryGoodsContainer(Map map);

    /**
     * 获取指定员工姓名
     * @param empIds
     * @return
     */
    List<Map> findEmployeeNameById(String empIds);
}
