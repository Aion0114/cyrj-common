package com.cyrj.pub.service;


import com.cyrj.pub.pojo.BillType;

import java.util.List;
import java.util.Map;

public interface BillTypeService {

    public List<Map> getAllBillTypeList(Map map);

    String getTypePrefix(String billTypeCode);

    BillType findByCode(String billTypeCode);

}
