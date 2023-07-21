package com.cyrj.sys.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.sys.pojo.TenantInfoHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 
 * @author xrq
 * @version 创建时间：2021-12-09 17:21:16
 */
public interface TenantInfoHistoryMapper extends BaseMapper<TenantInfoHistory> {

    List<String> columnInfo(Map<String, Object> parameter);

    List<Map<String,Object>> findDateMap(@Param("findSql") String findSql);

    Map<String,Object> findMainSql(@Param("findSql") String findSql);

    List<String> findStoredProcedure(@Param("dbName") String dbName);

    Map<String,Object> findProcedureSql(@Param("dbName") String dbName, @Param("procedure") String procedure);

    Long findCountBySql(@Param("findSql") String findSql);

    Map<String,Object> createTableSql(@Param("findSql") String findSql);

    List<String> findTableInfo(@Param("findSql") String findSql);

    void saveStoredProcedure(String sql);

}