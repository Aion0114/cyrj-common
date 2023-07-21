package com.cyrj.pub.service.impl;

import com.cyrj.common.enumeration.DeleteFlag;
import com.cyrj.common.enumeration.StatusFlag;
import com.cyrj.pub.mapper.StrategyConfigMapper;
import com.cyrj.pub.pojo.StrategyConfig;
import com.cyrj.pub.service.StrategyConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class StrategyConfigServiceImpl implements StrategyConfigService{

    @Autowired
    StrategyConfigMapper strategyConfigMapper;

	@Override
	public StrategyConfig getConfig(Map map) {
		map.put("delflag", DeleteFlag.VALID.getCode());
		return strategyConfigMapper.getConfig(map);
	}

	
	/**
     * 判断是否选中
     * @param key
     * @return
     */
	@Override
    public boolean checkSelected(String key){

        StrategyConfig config = new StrategyConfig();
        config.setKey(key);
        config.setDelflag(DeleteFlag.VALID.getCode());
        config.setStatus(1);

        try {
			config = strategyConfigMapper.find(config);

			if(config != null && config.getCheck() == 1){
				return true;
			}else {
				return false;
			}
		}catch (Exception e){
			return false;
		}
    }


	@Override
	public String getValueByKey(String key) {
		StrategyConfig config = new StrategyConfig();
        config.setKey(key);
        config.setDelflag(DeleteFlag.VALID.getCode());
        config.setStatus(1);

        config = strategyConfigMapper.find(config);
        if(config != null)
        {
        	return config.getValue();
        }
		return null;
	}
	
	@Override
	public Map findByModule(Map map) {
		map.put("delflag", DeleteFlag.VALID.getCode());
		List<StrategyConfig> list = new ArrayList<StrategyConfig>();
		try {
			list = strategyConfigMapper.findByModule(map);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Map receiptMap =new HashMap();
		receiptMap.put("mainTitle", null);//小票标题
		receiptMap.put("subTitle", null);//小票副标题
		receiptMap.put("advertisingOne", null);//小票广告语1
		receiptMap.put("advertisingTwo", null);//小票广告语2
		receiptMap.put("receiptEnd", null);//小票尾
		receiptMap.put("leaveLines", null);//小票打印后走几行
		receiptMap.put("printBarCode", null);//小票是否打印条码
		receiptMap.put("printType", null);//小票是否显示单据类型
		receiptMap.put("qrCode", null);//小票是否打印单号二维码
		
		if(list != null && list.size() > 0) 
		{
			for(StrategyConfig s :list) 
			{
				if(StringUtils.isNotBlank(s.getKey())) 
				{
					String key = s.getKey();
					String value = s.getValue();
					if(key.equals("receipt.main.title")) 
					{
						receiptMap.put("mainTitle", value);
					}else if(key.equals("receipt.sub.title")) 
					{
						receiptMap.put("subTitle", value);
					}else if(key.equals("receipt.advertising.one")) 
					{
						receiptMap.put("advertisingOne", value);
					}else if(key.equals("receipt.advertising.two")) 
					{
						receiptMap.put("advertisingTwo", value);
					}else if(key.equals("receipt.end")) 
					{
						receiptMap.put("receiptEnd", value);
					}else if(key.equals("receipt.print.leave.lines")) 
					{
						receiptMap.put("leaveLines", value);
					}else if(key.equals("receipt.yesNo.print.barCode")) 
					{
						Integer check = s.getCheck();
						receiptMap.put("printBarCode", check);
					}else if(key.equals("receipt.yesNo.print.type")) 
					{
						Integer check = s.getCheck();
						receiptMap.put("printType", check);
					}else if(key.equals("receipt.yesNo.print.qrCode")) 
					{
						Integer check = s.getCheck();
						receiptMap.put("qrCode", check);
					}
				}
			}
		}
		return receiptMap;
	}



	/**
	 * 查找全部策略配置
	 * @return
	 */
	@Override
	public Map<String, Object> findAllConfig() {

		List<StrategyConfig> list = strategyConfigMapper.findAll(DeleteFlag.VALID.getCode());

		Map<String, Object> result = new HashMap<String, Object>();
		List<StrategyConfig> strategyConfigList = null;
		for(StrategyConfig strategyConfig : list){

			strategyConfigList = (List<StrategyConfig>) result.get(strategyConfig.getModule().toString());
			if(null == strategyConfigList){
				strategyConfigList = new ArrayList<StrategyConfig>();
			}
			strategyConfigList.add(strategyConfig);
			result.put(strategyConfig.getModule().toString(), strategyConfigList);

		}
		return result;
	}

	/**
	 * 查找全部策略配置与策略模块
	 * @return
	 */
	@Override
	public Map<String, Object> findAllConfigAndMod(String systemType) {

		Map par = new HashMap();
		par.put("status", StatusFlag.ENABLE.getCode());
		par.put("delflag",DeleteFlag.VALID.getCode());
		List<Map> strategyType = strategyConfigMapper.findStrategyType(par);

		Iterator it = strategyType.iterator();
		Map result = new HashMap();
		while (it.hasNext()){
			Map m = (Map) it.next();

			if("qgd".equals(systemType)){
				String code = m.get("code").toString();
				if("10".equals(code) || "11".equals(code) || "13".equals(code)){
					it.remove();
					continue;
				}
			}

			Map sc = new HashMap();
			sc.put("module", m.get("code"));
			sc.put("delflag",DeleteFlag.VALID.getCode());

			if("qgd".equals(strategyType)){
				sc.put("systemType",systemType);
				sc.put("valueStr","sale.onlyOne");
			}

			List<StrategyConfig> byMod = strategyConfigMapper.findByMod(sc);

			for (StrategyConfig s :
					byMod) {
				if (s.getType() == 2 || s.getType() == 6 || s.getType() == 7) {
					List<Map> detailByConId = strategyConfigMapper.findDetailByConId(DeleteFlag.VALID.getCode(), s.getId());
					s.setValList(detailByConId);
				}
			}

			m.put("byMod",byMod);
		}


		//List<StrategyConfig> list = strategyConfigMapper.findAll(DeleteFlag.VALID.getCode());

//		Map<String, Object> result = new HashMap<String, Object>();
//		List<StrategyConfig> strategyConfigList = null;
//		for(StrategyConfig strategyConfig : list){
//
//			strategyConfigList = (List<StrategyConfig>) result.get(strategyConfig.getModule().toString());
//			if(null == strategyConfigList){
//				strategyConfigList = new ArrayList<StrategyConfig>();
//			}
//			strategyConfigList.add(strategyConfig);
//			result.put(strategyConfig.getModule().toString(), strategyConfigList);
//
//		}
		result.put("result",strategyType);
		return result;
	}

	/**
	 * 更新策略配置
	 * @param parameter
	 * @param employeeId
	 * @return
	 */
	@Override
	public Map<String, Object> update(Map<String, Object> parameter,Integer employeeId) {
		Map<String, Object> result = new HashMap<>();
		Integer id = (Integer) parameter.get("id");
		parameter.put("modifier", employeeId);
		strategyConfigMapper.update(parameter);
		StrategyConfig strategyConfig = strategyConfigMapper.findById(id);
		result.put("strategyConfig", strategyConfig);
		return result;
	}
    
}
