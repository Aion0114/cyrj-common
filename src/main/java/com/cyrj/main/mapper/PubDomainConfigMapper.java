package com.cyrj.main.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.main.pojo.PubDomainConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author hzh
 * @version 创建时间：2018-10-25 14:36:43
 */
public interface PubDomainConfigMapper extends BaseMapper<PubDomainConfig> {

    /**
     * @param map
     * @return
     */
    public List<PubDomainConfig> getQueryUrl(Map<String, Object> map);

    List<Map> findListByTenantId(@Param("tenantId")Integer tenantId);
}