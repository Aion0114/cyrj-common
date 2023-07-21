package com.cyrj.sys.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.sys.pojo.QdtenantInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author hzh
 * @version 创建时间：2019-03-26 13:38:52
 */
public interface QdtenantInfoMapper extends BaseMapper<QdtenantInfo> {

    List<Map<String, Object>> findListInfo(Map map);

    int updateStatus(Map map);

    int updateInvaDate(Map map);

    public int getTenantByPhone(@Param("phoneNo") String phone, @Param("delflag") Integer delflag);

    public  Map<String, Object> checkDomainByAcc(Map map);

    public QdtenantInfo getTenantByAcount(@Param("account") String accout, @Param("delflag") Integer delflag,@Param("systemType") String systemType);

	public void insert(QdtenantInfo tenantInfo);

    public void del(@Param("id") Integer id, @Param("delflag") Integer delflag);

    void upTenVVbyId(@Param("clientverSion") String clientverSion,@Param("version") String version,@Param("labelIdStr") String labelIdStr);
    
    public int updateIsFacility(@Param("id") Integer id);

    Integer updateOnlinePcNum(Map<String, Object> number);

    Integer updatePartnerNum(Map map);

    void updatePartnerNumStatus(Map map);

    QdtenantInfo tenantByPhone(QdtenantInfo qdtenantInfo);

    List<Map<String, Object>> findDbNameInfo(Map map);

    List<QdtenantInfo> findIdByAccount(@Param("accountList") List<String> accountList);

    void updateByAccount(Map map);

    List<QdtenantInfo> findQdtenantInfoParentId(@Param("parentId") Integer parentId, @Param("id") Integer id);

    void updateInvaDateParentId(Map<String,Object> mapValid);

    List<QdtenantInfo> findSaAmount(@Param("ids")String tenantIds);

    QdtenantInfo findRenewData(Map map);

    //租户系统平台类型总数统计
    Map<String,Object> totalSystemTypeStatistics();

    //根据区域（递归区域）信息查询租户系统平台类型统计数据
    List<Map<String, Object>> countProvinceAreaToTypeList();

    //根据省份编号获取下级城市的租户信息列表
    List<Map<String, Object>> countProvinceTenantList();

    /**
     * 获取需要更新脚本的租户数据库
     * @return
     */
    List<QdtenantInfo> findTenantDbName();

    Integer getTenantCount(HashMap map);

    int getTenantByAccount(@Param("account") String dbAccount, @Param("delflag") Integer delflag);
}