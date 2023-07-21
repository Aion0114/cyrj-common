package com.cyrj.pub.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyrj.common.config.RedisUtil;
import com.cyrj.pub.mapper.PubPermissionsMapper;
import com.cyrj.pub.service.PubPermissionsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author hzh
 * @version 创建时间：2019-08-20 09:55:59
 */
@Service("pubPermissionsService")
public class PubPermissionsServiceImpl implements PubPermissionsService {

    @Autowired
    PubPermissionsMapper pubPermissionsMapper;
    /**
     * 机构权限id集合
     * @param map
     * @return
     */
    @Override
    public List<Integer> queryOrganizationIds(Map map) {
        if (map.containsKey("userId")) {
            String redisKey = "";
            Integer userId = Integer.parseInt(map.get("userId").toString());
            if(map.containsKey("tenantId")) {
                Integer tenantId = Integer.parseInt(map.get("tenantId").toString());
                redisKey = "per-organizationIds-" + tenantId + "-" + userId;
                List<Integer> valueObject = getJson(redisKey);
                if (valueObject != null) {
                    return valueObject;
                }
            }
            List<Integer> organizationIds = pubPermissionsMapper.queryOrganizationIds(userId);
            if(StringUtils.isNotEmpty(redisKey))
            {
                RedisUtil.createForValue(redisKey,organizationIds);
            }
            return organizationIds;
        }
        return null;
    }

    /**
     * 仓库权限id集合
     * @param map
     * @return
     */
    @Override
    public List<Integer> queryStorehouseIds(Map map){
        if (map.containsKey("userId")) {
            String redisKey = "";
            Integer userId = Integer.parseInt(map.get("userId").toString());
            if(map.containsKey("tenantId")) {
                Integer tenantId = Integer.parseInt(map.get("tenantId").toString());
                redisKey = "per-storehouseIds-" + tenantId + "-" + userId;
                List<Integer> valueObject = getJson(redisKey);
                if (valueObject != null) {
                    return valueObject;
                }
            }
            List<Integer> storehouseIds = pubPermissionsMapper.queryStorehouseIds(userId);
            if(StringUtils.isNotEmpty(redisKey))
            {
                RedisUtil.createForValue(redisKey,storehouseIds);
            }
            return storehouseIds;
        }
        return null;
    }

    /**
     * 商品标签权限
     * @param map
     * @return
     */
    @Override
    public List<Integer> queryGoodsLabelRelevanceIds(Map map){
        if (map.containsKey("userId")) {
            String redisKey = "";
            Integer userId = Integer.parseInt(map.get("userId").toString());
            return pubPermissionsMapper.queryGoodsLabelRelevanceIds(userId);
        }
        return null;
    }

    /**
     * 客户标签权限
     * @param map
     * @return
     */
    @Override
    public List<Integer> queryCustomerLabelRelevanceIds(Map map){
        if (map.containsKey("userId")) {
//            String redisKey = "";
            Integer userId = Integer.parseInt(map.get("userId").toString());
//            if(map.containsKey("tenantId")) {
//                Integer tenantId = Integer.parseInt(map.get("tenantId").toString());
//                redisKey = "per-customerIds-" + tenantId + "-" + userId;
//                List<Integer> valueObject = getJson(redisKey);
//                if (valueObject != null) {
//                    return valueObject;
//                }
//            }
            List<Integer> list = pubPermissionsMapper.queryCustomerLabelRelevanceIds(userId);
//            if(StringUtils.isNotEmpty(redisKey))
//            {
//                RedisUtil.createForValue(redisKey,list);
//            }
            return list;
        }
        return null;
    }

    /**
     * 供应商标签权限
     * @param map
     * @return
     */
    @Override
    public List<Integer> querySupplierLabelRelevanceIds(Map map){
        if (map.containsKey("userId")) {
            String redisKey = "";
            Integer userId = Integer.parseInt(map.get("userId").toString());
            if(map.containsKey("tenantId")) {
                Integer tenantId = Integer.parseInt(map.get("tenantId").toString());
                redisKey = "per-supplierIds-" + tenantId + "-" + userId;
                List<Integer> valueObject = getJson(redisKey);
                if (valueObject != null && valueObject.size() > 0) {
                    return valueObject;
                }
            }
            List<Integer> list = pubPermissionsMapper.querySupplierLabelRelevanceIds(userId);
            if(StringUtils.isNotEmpty(redisKey))
            {
                RedisUtil.createForValue(redisKey,list);
            }
            return list;
        }
        return null;
    }

    /**
     * 员工标签权限
     * @param map
     * @return
     */
    @Override
    public List<Integer> queryEmployeeLabelRelevanceIds(Map map){
        if (map.containsKey("userId")) {
            String redisKey = "";
            Integer userId = Integer.parseInt(map.get("userId").toString());
            if(map.containsKey("tenantId")) {
                Integer tenantId = Integer.parseInt(map.get("tenantId").toString());
                redisKey = "per-employeeIds-" + tenantId + "-" + userId;
                List<Integer> valueObject = getJson(redisKey);
                if (valueObject != null) {
                    return valueObject;
                }
            }
            List<Integer> list = pubPermissionsMapper.queryEmployeeLabelRelevanceIds(userId);
            if(StringUtils.isNotEmpty(redisKey))
            {
                RedisUtil.createForValue(redisKey,list);
            }
            return list;
        }
        return null;
    }

    /**
     * 客户/商品标签获取客户/商品Id(不根据用户)
     * @param map
     * @return
     */
    @Override
    public List<Integer> queryLabelRelevanceIds(Map map){
        List<Integer> goodsLabelIds = null;
        List<Integer> customerLabelIds = null;
        if(map.containsKey("goodsLabelIds"))
        {
            goodsLabelIds = (List<Integer>)map.get("goodsLabelIds");
        }
        if(map.containsKey("customerLabelIds"))
        {
            customerLabelIds = (List<Integer>)map.get("customerLabelIds");
        }
        return pubPermissionsMapper.queryLabelRelevanceIds(goodsLabelIds,customerLabelIds);
    }

    /**
     * 货位
     * @param map
     * @return
     */
    @Override
    public List<Integer> queryGoodsContainer(Map map){
        Integer warehouseMerge = null;
        Integer storehouseId = null;
        String goodsIds = null;
        if(map.containsKey("warehouseMerge"))
        {
            warehouseMerge = (Integer)map.get("warehouseMerge");
        }
        if(map.containsKey("storehouseId"))
        {
            storehouseId = (Integer)map.get("storehouseId");
        }
        if(map.containsKey("goodsIds"))
        {
            goodsIds = map.get("goodsIds").toString();
        }
        return pubPermissionsMapper.queryGoodsContainer(warehouseMerge,storehouseId,goodsIds);
    }

    /**
     * 获取指定员工姓名
     * @param empIds
     * @return
     */
    @Override
    public List<Map> findEmployeeNameById(String empIds) {
        return pubPermissionsMapper.findEmployeeNameById(empIds);
    }

    public List<Integer> getJson(String redisKey)
    {
        String value = RedisUtil.getValue(redisKey);
        if(null == value){
            return  null;
        }else{
            List<Integer> list = JSONArray.parseArray(value,Integer.class);
            return list;
        }
    }
}
