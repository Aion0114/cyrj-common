package com.cyrj.pub.service;

import com.cyrj.common.service.BaseService;
import com.cyrj.pub.pojo.ExceptionLog;

import java.util.Map;

/**
 * Created by ChuWang on 2018/6/14.
 */
public interface ExceptionLogService extends BaseService<ExceptionLog> {

    void createExceptionLog(Map parameter);
}
