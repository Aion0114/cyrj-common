package com.cyrj.sys.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.cyrj.common.constant.SystemConstant;
import com.cyrj.common.constant.SystemKey;
import com.cyrj.common.constant.ValidationMessge;
import com.cyrj.common.db.DataSourceHolder;
import com.cyrj.common.enumeration.DeleteFlag;
import com.cyrj.common.enumeration.DisableStatus;
import com.cyrj.common.enumeration.UserStatus;
import com.cyrj.common.enumeration.UserType;
import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.common.util.MD5Encoder;
import com.cyrj.common.util.StringUtil;
import com.cyrj.pub.pojo.Employee;
import com.cyrj.sys.mapper.QdtenantInfoMapper;
import com.cyrj.sys.pojo.QdtenantInfo;
import com.cyrj.sys.service.QdtenantInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author hzh
 * @version 创建时间：2019-03-26 13:38:52
 */
@Service("qdtenantInfoService")
public class QdtenantInfoServiceImpl extends BaseServiceImpl<QdtenantInfo> implements QdtenantInfoService {

    @Autowired
    QdtenantInfoMapper qdtenantInfoMapper;

    @Override
    public List<Map<String, Object>> findListInfo(Map map) {
        // TODO Auto-generated method stub
        return qdtenantInfoMapper.findListInfo(map);
    }

    @Override
    public PageInfo<Map<String, Object>> findListPageByMaps(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        if (!map.containsKey("delflag")) {
            map.put("delflag", DeleteFlag.VALID.getCode());
        }
        PageInfo<Map<String, Object>> page = null;
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<Map<String, Object>> list = qdtenantInfoMapper.findListByMap(map);
            StringBuilder tenantIds=new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1)// 当循环到最后一个的时候 就不添加逗号,
                {
                    tenantIds.append(list.get(i).get("id"));
                } else {
                    tenantIds.append(list.get(i).get("id"));
                    tenantIds.append(",");
                }
            }
            List<QdtenantInfo> saAmount = qdtenantInfoMapper.findSaAmount(tenantIds.toString());
            Map saAmountMap = new HashMap();
            if (saAmount.size()>0 &&saAmount!=null){
                for (QdtenantInfo sa:saAmount){
                    saAmountMap.put(sa.getId(),sa.getSamoney());
                }
            }
            for (Map l:list){
                if(saAmountMap.containsKey(l.get("id"))){
                    l.put("saAmount",saAmountMap.get(l.get("id")));
                }
            }
            page = new PageInfo<>(list);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return page;
    }

    @Override
    public Map<String, Object> updateStatus(Map map) {
        Map<String, Object> resultMap = new HashMap<>();
        int result = qdtenantInfoMapper.updateStatus(map);
        if (result <= 0) {
            resultMap.put("error", "保存错误");
        } else {
            // 城市合伙人处理
            if (map.get("status") != null && (map.get("status").equals("0") || map.get("status").equals("1"))) {

                Integer count = qdtenantInfoMapper.updatePartnerNum(map);
                if(count !=null && count >=1){
                    qdtenantInfoMapper.updatePartnerNumStatus(map);
                }
            }
        }
        resultMap.put("obj", map);
        return resultMap;
    }

    @Override
    public void upTenantInfo(String clientverSion,String version,String labelIdStr) {
         qdtenantInfoMapper.upTenVVbyId(clientverSion,version,labelIdStr);
    }

    @Override
    public Map findRenewData(Map map) throws ParseException {
        Map reveMap=new HashMap();
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        QdtenantInfo renewData = qdtenantInfoMapper.findRenewData(map);
        Date renew = df.parse(df.format(renewData.getInvaDate()));
        reveMap.put("invaDate",df.format(renewData.getInvaDate()));
        reveMap.put("renewNum",daysBetween(renew,date));
        return reveMap;
    }

    @Override
    public Map<String, Object> totalSystemTypeStatistics() {
        return qdtenantInfoMapper.totalSystemTypeStatistics();
    }

    @Override
    public List<Map<String, Object>> systemTypeStatistics() {
        return qdtenantInfoMapper.countProvinceAreaToTypeList();
    }

    @Override
    public List<Map<String, Object>> provinceStatistics() {
        List<Map<String, Object>> list = qdtenantInfoMapper.countProvinceTenantList();
        try{
            //返回数据根据租户数量进行降序排序（从多到少）
            Collections.sort(list, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    JSONArray array1 = JSONUtil.parseArray(o1.get("systemTypeList"));
                    JSONArray array2 = JSONUtil.parseArray(o2.get("systemTypeList"));

                    Integer sort1 = array1.size();
                    Integer sort2 = array2.size();
                    return sort2.compareTo(sort1);
                }
            });
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Map<String, Object> createUserInfo(QdtenantInfo qdtenantInfo) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, Object> result = new HashMap<String, Object>();
        // 校验手机号是否重复
        if (!checkTenantPhoneUniqueness(qdtenantInfo.getPhoneNo())) {
            result.put(SystemKey.ERROR_KEY, ValidationMessge.USER_REGISTER_TELEPHONE_DUPLICATE);
            return result;
        }
        // 校验用户名是否重复
        if (!checkTenantNameUniqueness(qdtenantInfo.getAccount())) {
            result.put(SystemKey.ERROR_KEY, ValidationMessge.USER_REGISTER_USERNAME_DUPLICATE);
            return result;
        }
        // 保存管理服务器用户信息
        String password = MD5Encoder.getEncryptedPwd(qdtenantInfo.getPassword());
        qdtenantInfo.setPassword(password);
        // 租户状态
        qdtenantInfo.setStatus(DisableStatus.ENABLE.getCode());
        qdtenantInfo.setDelflag(DeleteFlag.VALID.getCode());
        qdtenantInfo.setSystemType(qdtenantInfo.getSystemType());
        qdtenantInfo.setCreated(new Date());
        qdtenantInfo.setUpdated(new Date());
        qdtenantInfoMapper.add(qdtenantInfo);
        result.put("tenant", qdtenantInfo);
        return result;
    }

    /**
     * 校验手机号码是否重复
     *
     * @param phone
     * @return
     */
    public boolean checkTenantPhoneUniqueness(String phone) {
        HashMap map = new HashMap();
        map.put("phoneNo",phone);
        Integer count = qdtenantInfoMapper.getTenantCount(map);

        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 校验租户名是否重复
     *
     * @param account
     * @return
     */
    public boolean checkTenantNameUniqueness(String account) {
        HashMap map = new HashMap();
        map.put("account",account);
        Integer count = qdtenantInfoMapper.getTenantCount(map);
        if (count > 0) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void deleteTenant(QdtenantInfo qdtenantInfo) {
        qdtenantInfoMapper.del(qdtenantInfo.getId(), DeleteFlag.DELETE.getCode());
    }

    /**
     * 计算天数
     * @param smdate 数据库日期
     * @param bdate  结束日期
     * @return
     * @throws ParseException
     */
    public static int daysBetween(Date smdate,Date bdate) throws ParseException
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time1-time2)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

}