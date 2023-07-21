package com.cyrj.pub.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.pub.pojo.ExceptionLog;

public interface ExceptionLogMapper extends BaseMapper<ExceptionLog> {

    int insert(ExceptionLog record);

    int insertSelective(ExceptionLog record);
}