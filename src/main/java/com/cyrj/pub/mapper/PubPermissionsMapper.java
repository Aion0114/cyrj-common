package com.cyrj.pub.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PubPermissionsMapper {

    /**
     * 机构权限id集合
     * @param userId
     * @return
     */
    @Select({"<script> ",
            "select DISTINCT art.taskId from auth_role_task art,auth_user_role aur ",
            "where art.roleId = aur.roleId and aur.delflag = 1 ",
            "  and art.relationType = 1 and art.delflag = 1 and aur.userId = #{userId}  ",
            "</script>"})
    List<Integer> queryOrganizationIds(Integer userId);

    /**
     * 仓库权限id集合
     * @param userId
     * @return
     */
    @Select({"<script> ",
            "SELECT DISTINCT a.taskId from auth_role_task a,auth_user_role b ",
            " WHERE a.roleId = b.roleId AND a.relationType = 2 AND a.delflag = 1",
            " AND b.userId = #{userId} AND b.delflag = 1  ",
            "</script>"})
    List<Integer> queryStorehouseIds(Integer userId);

    /**
     * 商品标签权限
     * @param userId
     * @return
     */
    @Select({"<script> ",
            "SELECT plr.relevanceId FROM ",
            "pub_label_relevance plr ",
            "JOIN auth_user_other auo ON auo.relationId = plr.labelId ",
            "AND auo.userId = #{userId} ",
            "AND auo.type = 1 ",
            "AND auo.delflag = 1 ",
            "JOIN pub_label pl ON plr.labelId = pl.id ",
            "WHERE pl.labelType = 1 ",
            "AND plr.delflag = 1 ",
            "GROUP BY plr.relevanceId ",
            "</script>"})
    List<Integer> queryGoodsLabelRelevanceIds(Integer userId);

    /**
     * 客户标签权限
     * @param userId
     * @return
     */
    @Select({"<script> ",
            "SELECT plr.relevanceId FROM ",
            "pub_label_relevance plr ",
            "JOIN auth_user_other auo ON auo.relationId = plr.labelId ",
            "AND auo.userId = #{userId} ",
            "AND auo.type = 0 ",
            "AND auo.delflag = 1 ",
            "JOIN pub_label pl ON plr.labelId = pl.id ",
            "WHERE pl.labelType = 4 ",
            "AND plr.delflag = 1 ",
            "GROUP BY plr.relevanceId ",
            "</script>"})
    List<Integer> queryCustomerLabelRelevanceIds(Integer userId);

    /**
     * 供应商标签权限
     * @param userId
     * @return
     */
    @Select({"<script> ",
            "SELECT plr.relevanceId FROM ",
            "pub_label_relevance plr ",
            "JOIN auth_user_other auo ON auo.relationId = plr.labelId ",
            "AND auo.userId = #{userId} ",
            "AND auo.type = 2 ",
            "AND auo.delflag = 1 ",
            "JOIN pub_label pl ON plr.labelId = pl.id ",
            "WHERE pl.labelType = 2 ",
            "AND plr.delflag = 1 ",
            "GROUP BY plr.relevanceId ",
            "</script>"})
    List<Integer> querySupplierLabelRelevanceIds(Integer userId);


    /**
     * 员工标签权限
     * @param userId
     * @return
     */
    @Select({"<script> ",
            "SELECT plr.relevanceId FROM ",
            "pub_label_relevance plr ",
            "JOIN auth_user_other auo ON auo.relationId = plr.labelId ",
            "AND auo.userId = #{userId} ",
            "AND auo.type = 0 ",
            "AND auo.delflag = 1 ",
            "JOIN pub_label pl ON plr.labelId = pl.id ",
            "WHERE pl.labelType = 3 ",
            "AND plr.delflag = 1 ",
            "GROUP BY plr.relevanceId ",
            "</script>"})
    List<Integer> queryEmployeeLabelRelevanceIds(Integer userId);

    /**
     * 客户/商品标签获取客户/商品Id(不根据用户)
     * @return
     */
    @Select({"<script> ",
            "select relevanceId from pub_label_relevance lr ",
            "left join pub_label l on lr.labelId = l.id ",
            "where lr.delflag = 1 ",
            "and l.delflag = 1 and l.labelType = #{labelType} and l.id in ",
            "<if test='labelType == 1'> ",
            "<foreach collection='goodsLabelIds' index='index' item='item' open='(' separator=',' close=')'> ",
            "#{item} ",
            "</foreach> ",
            "</if> ",
            "<if test='labelType == 4'> ",
            "<foreach collection='customerLabelIds' index='index' item='item' open='(' separator=',' close=')'> ",
            "#{item} ",
            "</foreach> ",
            "</if> ",
            "group by relevanceId ",
            "</script>"})
    List<Integer> queryLabelRelevanceIds(List<Integer> goodsLabelIds,List<Integer> customerLabelIds);

    /**
     * 货位
     * @return
     */
    @Select({"<script> ",
            "<if test='warehouseMerge != null and warehouseMerge ==1'> ",
            "SELECT ",
            "GROUP_CONCAT(containerCode) containerCode, ",
            "GROUP_CONCAT(containerName) containerName, ",
            "goodsId ",
            " FROM (</if> ",
            "SELECT GROUP_CONCAT(co.`code`) containerCode,GROUP_CONCAT(co.`name`) containerName,da.goodsId ",
            "<if test='storehouseId != null '> ",
            ",co.storehouseId ",
            "</if> ",
            "from dat_container co ",
            "JOIN dat_container_goods da on co.id = da.containerId and da.delflag =1 ",
            "where co.delflag =1 ",
            "and da.goodsId in (${goodsIds}) ",
            "GROUP BY da.goodsId ",
            "<if test='storehouseId != null '> ",
            ",co.storehouseId ",
            "</if> ",
            "<if test='warehouseMerge != null and warehouseMerge ==1'> ",
            ") a GROUP BY a.goodsId ",
            "</if> ",
            "</script>"})
    List<Integer> queryGoodsContainer(Integer warehouseMerge,Integer storehouseId,String goodsIds);

    /**
     * 根据员工id获取员工姓名
     * @param empIds
     * @return
     */
    @Select({"<script> ","select id,name from DAT_EMPLOYEE where delflag = 1 and id in(${empIds})","</script>"})
    List<Map> findEmployeeNameById(@Param("empIds")String empIds);
}
