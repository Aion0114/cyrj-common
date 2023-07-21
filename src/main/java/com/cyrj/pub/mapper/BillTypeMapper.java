package com.cyrj.pub.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.pub.pojo.BillType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillTypeMapper extends BaseMapper<BillType>{

    BillType findByCode(BillType billType);

    //List<Map> findBillTypeList(@Param("delflag") Integer delflag, @Param("status") Integer status,  @Param("tenantId")Integer tenantId);
    List<Map> findBillTypeList(Map map);

    List<BillType> findBillTypeByCondition(@Param("inOrOutFlag") List<Integer> inOrOutFlag, @Param("delflag") Integer delflag, @Param("status") Integer status, @Param("tenantId")Integer tenantId);

    List<String> findNoCheckBillNo(Map<String, Object> parameter);

    List<String> findBillNoForBalance(Map<String, Object> parameter);

    List<BillType> selectList(BillType billType);
}