package com.cyrj.pub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.pub.mapper.PubConfigMapper;
import com.cyrj.pub.service.PubConfigService;
import com.cyrj.pub.pojo.PubConfig;


/**
 * 
 * @author hzh
 * @version 创建时间：2019-08-20 09:55:59
 */
@Service("pubConfigService")
public class PubConfigServiceImpl extends BaseServiceImpl<PubConfig> implements PubConfigService {

    @Autowired
    PubConfigMapper pubConfigMapper;
}