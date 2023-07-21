package com.cyrj.pub.mapper;

import java.util.List;
import java.util.Map;

import com.cyrj.pub.pojo.StrategyConfig;
import org.apache.ibatis.annotations.Param;


public interface StrategyConfigMapper {
	
	public StrategyConfig getConfig(Map map);
	
	public StrategyConfig find(StrategyConfig config);
	
	//小程序开销售单打印参数
	public List<StrategyConfig> findByModule(Map map);

	List<StrategyConfig> findAll(@Param("delflag") Integer delflag);

	void update(Map parameter);

	StrategyConfig findById(@Param("id")Integer id);

	List<Map> findStrategyType(Map par);

	List<StrategyConfig> findByMod(Map par);

	List<Map> findDetailByConId(@Param("delflag")Integer delflag, @Param("stId")Integer stId);

	StrategyConfig getConfigByKey(Map<String, Object> con);
}
