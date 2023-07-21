package com.cyrj.sys.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.sys.pojo.Development;
import com.cyrj.sys.pojo.DevelopmentService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DevelopmentMapper {

    @Select("select id,clientId,appName,appType,bindService,checkMode,iptable,accessToken,encryptVal,delflag from sys_develop where clientid = #{clientId} and delflag=1 limit 1")
    Development findByClientId(Map<String, Object> map);

//    @Select("select count(*) from sys_developservice where id in (${path}) and delflag=1 and #{modulePath} like CONCAT('%',modulePath,'%') ")
//    int findByDevelopService(@Param("path") String path,@Param("modulePath") String modulePath);

    @Select({"select group_concat(modulePath) modulePath from sys_developservice where id in (${path}) and delflag=1 limit 1"})
    Map findByDevelopService(@Param("path") String path);
}
