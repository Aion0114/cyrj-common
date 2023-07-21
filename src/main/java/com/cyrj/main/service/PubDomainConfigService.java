package com.cyrj.main.service;

import com.cyrj.common.service.BaseService;
import com.cyrj.main.pojo.PubDomainConfig;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author hzh
 * @version 创建时间：2018-10-25 14:36:43
 */
public interface PubDomainConfigService extends BaseService<PubDomainConfig> {

    /**
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<PubDomainConfig> getQueryUrl(Map<String, Object> map, Integer pageNum, Integer pageSize);

    List<Map> findListByTenantId(Integer tenantId);
}