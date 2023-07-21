package com.cyrj.language.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.language.pojo.LangBasics;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * @author tf
 * @version 创建时间：2019-09-16 15:03:10
 */
public interface LangBasicsMapper extends BaseMapper<LangBasics> {
	
	// 根据langKey获取ID
	LangBasics getLangBasicsById(@Param("langKey") String langKey, @Param("delflag") Integer delflag);
}