package com.cyrj.sys.service.impl;

import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.sys.mapper.TenantInfoHistoryMapper;
import com.cyrj.sys.pojo.TenantInfoHistory;
import com.cyrj.sys.service.TenantInfoHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenantInfoHistoryService")
public class TenantInfoHistoryServiceImpl extends BaseServiceImpl<TenantInfoHistory> implements TenantInfoHistoryService {

    @Autowired
    private TenantInfoHistoryMapper tenantInfoHistoryMapper;

}
