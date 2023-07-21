package com.cyrj.pub.service.impl;

import com.cyrj.common.enumeration.DisableStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyrj.common.enumeration.DeleteFlag;
import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.pub.mapper.BillTypeMapper;
import com.cyrj.pub.pojo.BillType;
import com.cyrj.pub.service.BillTypeService;

import java.util.List;
import java.util.Map;


@Service
public class BillTypeServiceImpl extends BaseServiceImpl implements BillTypeService {

    @Autowired
    BillTypeMapper billTypeMapper;

    @Override
    public List<Map> getAllBillTypeList(Map map) {

        //Employee employee = getByUsername(userName);
        map.put("delflag", DeleteFlag.VALID.getCode());
        map.put("status", DisableStatus.ENABLE.getCode());
        List<Map> billTypeList = billTypeMapper.findBillTypeList(map);
        return billTypeList;
    }

    /**
     * 获取单据类型前缀
     * @param billTypeCode
     * @return
     */
    @Override
    public String getTypePrefix(String billTypeCode) {

        BillType billType = new BillType();
        billType.setCode(billTypeCode);
        billType.setDelflag(DeleteFlag.VALID.getCode());
        billType = billTypeMapper.getByObj(billType);
        String prefix = billType.getPrefix();
        return prefix;

    }

    @Override
    public BillType findByCode(String billTypeCode) {

        BillType billType = new BillType();
        billType.setCode(billTypeCode);
        billType.setDelflag(DeleteFlag.VALID.getCode());
        billType = billTypeMapper.getByObj(billType);
        return billType;
    }
}
