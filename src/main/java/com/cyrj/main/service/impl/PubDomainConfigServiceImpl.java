package com.cyrj.main.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.main.mapper.PubDomainConfigMapper;
import com.cyrj.main.pojo.PubDomainConfig;
import com.cyrj.main.service.PubDomainConfigService;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author hzh
 * @version 创建时间：2018-10-25 14:36:43
 */
@Service("pubDomainConfigService")
public class PubDomainConfigServiceImpl extends BaseServiceImpl<PubDomainConfig> implements PubDomainConfigService {

    @Autowired
    PubDomainConfigMapper pubDomainConfigMapper;

    @Override
    public PageInfo<PubDomainConfig> getQueryUrl(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PubDomainConfig> list = pubDomainConfigMapper.getQueryUrl(map);
        PageInfo<PubDomainConfig> pageInfo = new PageInfo<PubDomainConfig>(list);
        return pageInfo;
    }

    @Override
    public List<Map> findListByTenantId(Integer tenantId) {
        return pubDomainConfigMapper.findListByTenantId(tenantId);
    }
}