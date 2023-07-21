package com.cyrj.pub.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.pub.pojo.PubOddNumbers;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author tf
 * @version 创建时间：2020-02-26 17:35:34
 */
public interface PubOddNumbersMapper extends BaseMapper<PubOddNumbers> {

    List<Map<String, Object>> oddNumbersPageMap(Map<String, Object> map);
    /**
     * 根据机构ID和单据类型名称查询规则
     * */
    PubOddNumbers getOddNumberOrg(Map<String, Object> org);

    /**
     * 根据单据类型查询唯一
     * */
    PubOddNumbers getBillOddNumber(PubOddNumbers pubOddNumbers);
}