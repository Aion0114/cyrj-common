package com.cyrj.pub.service.impl;

import com.cyrj.common.db.DataSourceHolder;
import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.pub.mapper.ExceptionLogMapper;
import com.cyrj.pub.pojo.ExceptionLog;
import com.cyrj.pub.service.ExceptionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by ChuWang on 2018/6/14.
 */
@Service("exceptionLogService")
public class ExceptionLogServiceImpl extends BaseServiceImpl<ExceptionLog> implements ExceptionLogService {

    @Autowired
    ExceptionLogMapper exceptionLogMapper;

    @Override
    public void createExceptionLog(Map parameter) {
        DataSourceHolder.setDataSource(null);
        ExceptionLog log = new ExceptionLog();
        log.setCreateTime(new Date());
        if (null != parameter.get("exceptionName")) log.setExceptionName(parameter.get("exceptionName").toString());
        if (null != parameter.get("exceptionMsg")) log.setExceptionMsg(parameter.get("exceptionMsg").toString());
        if (null != parameter.get("exceptionLocation"))
            log.setExceptionLocation(parameter.get("exceptionLocation").toString());
        if (null != parameter.get("requestType")) log.setRequestType(parameter.get("requestType").toString());
        if (null != parameter.get("requestUrl")) log.setRequestUrl(parameter.get("requestUrl").toString());
        if (null != parameter.get("requestAdr")) log.setRequestAdr(parameter.get("requestAdr").toString());
        exceptionLogMapper.insert(log);
    }
}
