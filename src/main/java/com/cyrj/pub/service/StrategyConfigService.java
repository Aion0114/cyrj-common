package com.cyrj.pub.service;

import java.util.Map;

import com.cyrj.pub.pojo.StrategyConfig;


public interface StrategyConfigService {

	/**
	 * 获取系统配置信息
	 * @param map
	 * @return
	 */
	public StrategyConfig getConfig(Map map);
	
	/**
	 * 根据唯一的key判断是否选中
	 * @param key
	 * @return
	 */
	boolean checkSelected(String key);
	
	public String getValueByKey(String key);
	
	//小程序开销售单打印参数
	public Map findByModule(Map map);

	Map<String, Object> findAllConfig();

	Map<String, Object> update(Map<String, Object> parameter,Integer employeeId);

	Map<String, Object> findAllConfigAndMod(String systemType);

}
