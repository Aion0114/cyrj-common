package com.cyrj.pub.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cyrj.common.util.StringUtil;
import com.cyrj.pub.mapper.PubOddLogMapper;
import com.cyrj.pub.mapper.PubOddNumbersMapper;
import com.cyrj.pub.mapper.StrategyConfigMapper;
import com.cyrj.pub.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyrj.common.enumeration.DeleteFlag;
import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.pub.mapper.SerialMapper;
import com.cyrj.pub.service.SerialService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 
 * @author hzh
 * @version 创建时间：2018-08-04 14:00:50
 */
@Service("serialService")
public class SerialServiceImpl extends BaseServiceImpl<Serial> implements SerialService {
	

    @Autowired
    SerialMapper serialMapper;

    @Autowired
    StrategyConfigMapper strategyConfigMapper;

    @Autowired
    PubOddNumbersMapper pubOddNumbersMapper;

    @Autowired
    PubOddLogMapper pubOddLogMapper;

    @Override
    public String getMaxCode(String maxCode,String typeCode,int defaultNum){

        if (null==maxCode|| "".equals(maxCode)){

            StringBuilder sb = new StringBuilder();
            for(int i=1 ; i<defaultNum ; i++){
                sb.append("0");
            }
            sb.append("1");

            maxCode = typeCode + sb.toString();
        }else{
            List<String> list = new ArrayList();
            Pattern p = Pattern.compile("\\D+|\\d+");
            Matcher m = p.matcher( maxCode );
            while ( m.find() ) {
                list.add(m.group());
            }
            if(list.size() > 1) {
                if (list.get(list.size() - 1).matches("\\d+")) {
                    Integer maxNum = Integer.valueOf(list.get(list.size() - 1)) + 1;
                    StringBuilder sb = new StringBuilder();
                    if (list.get(list.size() - 1).length() - maxNum.toString().length() > 0) {
                        for (int i = 0; i < list.get(list.size() - 1).length() - maxNum.toString().length(); i++) {
                            sb.append("0");
                        }
                    }
                    sb.append(maxNum.toString());
                    list.remove(list.size() - 1);
                    list.add(sb.toString());
                } else {
                    list.add("1");
                }
                StringBuffer code = new StringBuffer();
                for (String s : list) {
                    code.append(s);
                }
                maxCode = code.toString();
            }else {
                StringBuilder sb = new StringBuilder(maxCode);
                sb = sb.delete(0, typeCode.length());
                if(StringUtil.isEmpty(sb.toString())){
                    for(int i=1 ; i<defaultNum ; i++){
                        sb.append("0");
                    }
                    sb.append("1");

                    maxCode = sb.toString();
                }else {
                    maxCode = sb.toString();
                    maxCode = generateByAsc(maxCode);
                }
                maxCode = typeCode + maxCode;
            }
        }
        return maxCode;
    }

    /**
     * 生成单据编号
     * @param billType
     * @param tenantId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public String generatorBillNo(String billType,Integer tenantId,Integer employeeId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String date = getDate();
        Integer num = 4;
        String oddSplit ="";
        String oddSuffix ="";//后缀
        PubOddNumbers pub = getPubOddNumbers(billType,tenantId,organizationId,null);
        if(pub !=null){
            num =pub.getSerialLength()==null? 3:pub.getSerialLength();
            oddSplit =pub.getOddSplit() ==null? "":pub.getOddSplit();
            oddSuffix =pub.getOddSuffix() ==null?"":pub.getOddSuffix();
            date = pub.getOddCode() ==null? date:pub.getOddCode();
//            billType =pub.getInterlockMark();
            billType = pub.getOddPrefix();
        }
        Serial serial = new Serial();
        serial.setSerialPrefix(date);
        serial.setType(billType);
        serial.setDelflag(DeleteFlag.VALID.getCode());
        serial.setTenantId(tenantId);

        serial = serialMapper.getByObj(serial);
        if(null == serial){
            serial = new Serial();
            serial.setSerialPrefix(date);
            serial.setType(billType);
            serial.setCreator(employeeId);
            serial.setModifier(employeeId);
            serial.setCreateTime(new Date());
            serial.setModifiTime(new Date());
            serial.setDelflag(DeleteFlag.VALID.getCode());
            serial.setTenantId(tenantId);
            serial.setSerialNo(1);
            serial.setStatus(1);
            try {
                serialMapper.add(serial);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        }
        Map map = new HashMap();
        map.put("id", serial.getId());
        int serialNo = serial.getSerialNo();
        int i = 0;
        while(true) {
            map.put("serialNo", serialNo);
            int result = 0;
            try {
                result = serialMapper.updateSerialNo(map);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
            if(result > 0)
            {
                serial.setSerialNo(serialNo);
                break;
            }
            map.remove("serialNo");
            serialNo++;
            i++;
            if(i > 10)
            {
                return "";
            }
        }
        String code = fillNo(serial.getSerialNo(),num);
        if(pub !=null){
            // 把code插入连锁日记表
            code = billType+date+oddSplit+code;
//            code = pub.getInterlockMark()+oddSplit+billType+date+oddSplit+code;
            if(!StringUtil.isEmpty(oddSuffix)){
                code +=oddSplit+oddSuffix;
            }
            adOdddLog(pub,organizationId,code);
        }else{
            code = billType+date+code;
        }

        return code;
    }
    
    /**
     * 根据不同日期生成单据编号
     * @param billType
     * @param tenantId
     * @return
     * @throws NoSuchAlgorithmException 
     * @throws UnsupportedEncodingException 
     */
    @Override
    public String generatorDateBillNo(String billType,Integer tenantId,String date,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Integer num = 4;
        String oddSplit ="";
        String oddSuffix ="";//后缀
        PubOddNumbers pub = getPubOddNumbers(billType,tenantId,organizationId,date);
        if(pub !=null){
            num =pub.getSerialLength()==null? 3:pub.getSerialLength();
            oddSplit =pub.getOddSplit() ==null? "":pub.getOddSplit();
            oddSuffix =pub.getOddSuffix() ==null?"":pub.getOddSuffix();
            date = pub.getOddCode() ==null? date:pub.getOddCode();
//            billType =pub.getInterlockMark();
            billType = pub.getOddPrefix();
        }

        Serial serial = new Serial();
        serial.setSerialPrefix(date);
        serial.setType(billType);
        serial.setDelflag(DeleteFlag.VALID.getCode());
        serial.setTenantId(tenantId);

        serial = serialMapper.getByObj(serial);
        if(null == serial){
            serial = new Serial();
            serial.setSerialPrefix(date);
            serial.setType(billType);
            serial.setDelflag(DeleteFlag.VALID.getCode());
            serial.setTenantId(tenantId);
            serial.setSerialNo(1);
            serial.setCreateTime(new Date());
            serial.setModifiTime(new Date());
            serial.setStatus(1);
            try {
                serialMapper.add(serial);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        }
        
        Map map = new HashMap();
        map.put("id", serial.getId());
        int serialNo = serial.getSerialNo();
        int i = 0;
        while(true) {
        	map.put("serialNo", serialNo);
            int result = 0;
            try {
                result = serialMapper.updateSerialNo(map);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
            if(result > 0)
        	{
        		serial.setSerialNo(serialNo);
        		break;
        	}
        	map.remove("serialNo");
        	serialNo++;
        	i++;
        	if(i > 10)
        	{
        		return "";
        	}
        }

        String code = fillNo(serial.getSerialNo(),num);
        if(pub !=null){
            // 把code插入连锁日记表
            code = billType+date+oddSplit+code;
//            code = pub.getInterlockMark()+oddSplit+billType+date+oddSplit+code;
            if(!StringUtil.isEmpty(oddSuffix)){
                code +=oddSplit+oddSuffix;
            }
            adOdddLog(pub,organizationId,code);
        }else{
            code = billType+date+code;
        }

        return code;
    }

    /**
     * 六大单据根据主表创建时间生成单据编号
     * @param billType
     * @param fillDate
     * @param tenantId
     * @param employeeId
     * @return
     */
    @Override
    public String generatorBillNo(String billType, Integer tenantId,Integer employeeId, String fillDate,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Integer num = 4;
        String oddSplit ="";
        String oddSuffix ="";//后缀
        PubOddNumbers pub = getPubOddNumbers(billType,tenantId,organizationId,null);
        if(pub !=null){
            num =pub.getSerialLength()==null? 3:pub.getSerialLength();
            oddSplit =pub.getOddSplit() ==null? "":pub.getOddSplit();
            oddSuffix =pub.getOddSuffix() ==null?"":pub.getOddSuffix();
            fillDate = pub.getOddCode() ==null? fillDate:pub.getOddCode();
//            billType =pub.getInterlockMark();
            billType = pub.getOddPrefix();
        }


        Serial serial = new Serial();
        serial.setSerialPrefix(fillDate);
        serial.setType(billType);
        serial.setDelflag(DeleteFlag.VALID.getCode());
        serial.setTenantId(tenantId);

        serial = serialMapper.getByObj(serial);
        if(null == serial){
            serial = new Serial();
            serial.setSerialPrefix(fillDate);
            serial.setType(billType);
            serial.setCreator(employeeId);
            serial.setModifier(employeeId);
            serial.setCreateTime(new Date());
            serial.setModifiTime(new Date());
            serial.setDelflag(DeleteFlag.VALID.getCode());
            serial.setTenantId(tenantId);
            serial.setSerialNo(1);
            serial.setStatus(1);
            try {
                serialMapper.add(serial);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        }
        Map map = new HashMap();
        map.put("id", serial.getId());
        int serialNo = serial.getSerialNo();
        int i = 0;
        while(true) {
            map.put("serialNo", serialNo);
            int result = 0;
            try {
                result = serialMapper.updateSerialNo(map);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
            if(result > 0)
            {
                serial.setSerialNo(serialNo);
                break;
            }
            map.remove("serialNo");
            serialNo++;
            i++;
            if(i > 10)
            {
                return "";
            }
        }
        String code = fillNo(serial.getSerialNo(), num);
        if(pub !=null){
            // 把code插入连锁日记表
            code = billType+fillDate+oddSplit+code;
//            code = pub.getInterlockMark()+oddSplit+billType+fillDate+oddSplit+code;
            if(!StringUtil.isEmpty(oddSuffix)){
                code +=oddSplit+oddSuffix;
            }
            adOdddLog(pub,organizationId,code);
        }else{
            code = billType+fillDate+code;
        }

        return code;
    }

    /**
     * 生成单据编号
     * @param billType
     * @param tenantId
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @Override
    public String generatorBillNo(String billType,Integer tenantId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String date = getDate();
        Integer num = 4;
        String oddSplit ="";
        String oddSuffix ="";//后缀
        PubOddNumbers pub = getPubOddNumbers(billType,tenantId,organizationId,null);
        if(pub !=null){
            num =pub.getSerialLength()==null? 3:pub.getSerialLength();
            oddSplit =pub.getOddSplit() ==null? "":pub.getOddSplit();
            oddSuffix =pub.getOddSuffix() ==null?"":pub.getOddSuffix();
            date = pub.getOddCode() ==null? getDate():pub.getOddCode();
//            billType =pub.getInterlockMark();
            billType = pub.getOddPrefix();
        }


        Serial serial = new Serial();
        serial.setSerialPrefix(date);
        serial.setType(billType);
        serial.setDelflag(DeleteFlag.VALID.getCode());
        serial.setTenantId(tenantId);

        serial = serialMapper.getByObj(serial);
        if(null == serial){
            serial = new Serial();
            serial.setSerialPrefix(date);
            serial.setType(billType);
            serial.setDelflag(DeleteFlag.VALID.getCode());
            serial.setTenantId(tenantId);
            serial.setSerialNo(1);
            serial.setCreateTime(new Date());
            serial.setModifiTime(new Date());
            serial.setStatus(1);
            serialMapper.add(serial);
        }
        Map map = new HashMap();
        map.put("id", serial.getId());
        int serialNo = serial.getSerialNo();
        int i = 0;
        while(true) {
        	map.put("serialNo", serialNo);
        	int result = serialMapper.updateSerialNo(map);
        	if(result > 0)
        	{
        		serial.setSerialNo(serialNo);
        		break;
        	}
        	map.remove("serialNo");
        	serialNo++;
        	i++;
        	if(i > 10)
        	{
        		return "";
        	}
        }
        String code = fillNo(serial.getSerialNo(), num);
        if(pub !=null){
            // 把code插入连锁日记表
            code = billType+date+oddSplit+code;
//            code = pub.getInterlockMark()+oddSplit+billType+date+oddSplit+code;
            if(!StringUtil.isEmpty(oddSuffix)){
                code +=oddSplit+oddSuffix;
            }
            adOdddLog(pub,organizationId,code);
        }else{
            code = billType+date+code;
        }


        return code;
    }

    /**
     * 生成单据编号 多余信息显示
     * @param billType
     * @param tenantId
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    @Override
    public String generatorBillNo(String billType,String surplus,Integer tenantId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String date = getDate();
        Integer num = 4;
        String oddSplit ="";
        String oddSuffix ="";//后缀
        PubOddNumbers pub = getPubOddNumbers(billType,tenantId,organizationId,null);
        if(pub !=null){
            num =pub.getSerialLength()==null? 3:pub.getSerialLength();
            oddSplit =pub.getOddSplit() ==null? "":pub.getOddSplit();
            oddSuffix =pub.getOddSuffix() ==null?"":pub.getOddSuffix();
            date = pub.getOddCode() ==null? getDate():pub.getOddCode();
//            billType =pub.getInterlockMark();
            billType = pub.getOddPrefix();
        }

        billType = billType+surplus;

        Serial serial = new Serial();
        serial.setSerialPrefix(date);
        serial.setType(billType);
        serial.setDelflag(DeleteFlag.VALID.getCode());
        serial.setTenantId(tenantId);

        serial = serialMapper.getByObj(serial);
        if(null == serial){
            serial = new Serial();
            serial.setSerialPrefix(date);
            serial.setType(billType);
            serial.setDelflag(DeleteFlag.VALID.getCode());
            serial.setTenantId(tenantId);
            serial.setSerialNo(1);
            serial.setCreateTime(new Date());
            serial.setModifiTime(new Date());
            serial.setStatus(1);
            serialMapper.add(serial);
        }
        Map map = new HashMap();
        map.put("id", serial.getId());
        int serialNo = serial.getSerialNo();
        int i = 0;
        while(true) {
            map.put("serialNo", serialNo);
            int result = serialMapper.updateSerialNo(map);
            if(result > 0)
            {
                serial.setSerialNo(serialNo);
                break;
            }
            map.remove("serialNo");
            serialNo++;
            i++;
            if(i > 10)
            {
                return "";
            }
        }
        String code = fillNo(serial.getSerialNo(), num);
        if(pub !=null){
            // 把code插入连锁日记表
            code = billType+date+oddSplit+code;
//            code = pub.getInterlockMark()+oddSplit+billType+date+oddSplit+code;
            if(!StringUtil.isEmpty(oddSuffix)){
                code +=oddSplit+oddSuffix;
            }
            adOdddLog(pub,organizationId,code);
        }else{
            code = billType+date+code;
        }


        return code;
    }

    /**
     * 获取连锁规则信息
     * date 如果时间变化就传入，其他传入null
     * */
    public PubOddNumbers getPubOddNumbers(String billType,Integer tenantId,Integer organizationId,String date) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String,Object> org = new HashMap<>();
        org.put("delflag",DeleteFlag.VALID.getCode());
        org.put("organizationId",organizationId);
        org.put("billType",billType);
        // 判断是否开启连锁标识
        StrategyConfig config = null;
        if(organizationId !=null && organizationId >0){
            config =new StrategyConfig();
            config.setDelflag(DeleteFlag.VALID.getCode());
            config.setKey("basic.odd.number");
            config = strategyConfigMapper.find(config);
        }
//        if(config ==null){
//            return null;
//        }

        PubOddNumbers odd =  pubOddNumbersMapper.getOddNumberOrg(org);
        if(odd ==null){
            return null;
        }
        if(odd.getId() ==null){
            org.put("numbersNo",1);
            odd = pubOddNumbersMapper.getOddNumberOrg(org);

            if(odd == null || odd.getId() ==null){
                return null;
            }
        }


        String prefix = "";


        if(config !=null && config.getCheck() ==1){
//            odd = pubOddNumbersMapper.getOddNumberOrg(org);
            if(odd.getInterlockMark() == null){
//                return null;
            }else{
//                if(odd.getId() ==null){
//                    org.put("numbersNo",1);
//                    odd = pubOddNumbersMapper.getOddNumberOrg(org);
//
//                    if(odd == null || odd.getId() ==null){
//                        return null;
//                    }
//                }
                // 联锁标识-前缀-日期-流水号-后缀
//                oddSplit =odd.getOddSplit() ==null? "":odd.getOddSplit();
                prefix = odd.getInterlockMark();
//                prefix += odd.getOddPrefix() ==null?"":odd.getOddPrefix();
//                odd.setOddPrefix(prefix);
//                String oddCode = "";
//                if(date !=null){
//                    oddCode += oddSplit+date;
//                }else{
//                    oddCode +=odd.getDateMode() ==null?"":oddSplit+odd.getDateMode();
//                }
//
//                odd.setOddCode(oddCode);

            }

        }

        // 联锁标识-前缀-日期-流水号-后缀
        String oddSplit =odd.getOddSplit() ==null? "":odd.getOddSplit();
        if(!StringUtil.isEmpty(prefix)){
            prefix += oddSplit;
        }
        prefix += odd.getOddPrefix() ==null?"":odd.getOddPrefix();
        odd.setOddPrefix(prefix);
        String oddCode = "";
        if(date !=null){
            oddCode += oddSplit+date;
        }else{
            oddCode +=odd.getDateMode() ==null?"":oddSplit+odd.getDateMode();
        }

        odd.setOddCode(oddCode);



        return odd;
    }
    /**
     * 添加一条记录到单号生成记录表
     * -1代表没开启机构标识
     * */
    public void adOdddLog(PubOddNumbers pub,Integer organizationId,String code) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        PubOddLog log = new PubOddLog();
        log.setDelflag(DeleteFlag.VALID.getCode());
        log.setOrganizationId(organizationId);
        log.setCreateTime(new Date());
        log.setModifiTime(new Date());
        log.setPubOddId(pub.getId());
        log.setPubOddId(pub.getId());
        log.setBillId(pub.getBillId());
        log.setBillName(pub.getBillName());
        log.setOddNumbers(code);
        pubOddLogMapper.add(log);
    }

    /**
     * organizationId 机构ID
     * 开启连锁标识必传
     * */
    @Override
	public String generatorSchemeNo(String billType,Integer tenantId,Integer organizationId) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        Integer num = 3;
        String oddSplit ="";
        String oddSuffix ="";//后缀
        String date = getDate();
        PubOddNumbers pub = getPubOddNumbers(billType,tenantId,organizationId,null);
        if(pub !=null){
            num =pub.getSerialLength()==null? 3:pub.getSerialLength();
            oddSplit =pub.getOddSplit() ==null? "":pub.getOddSplit();
            oddSuffix =pub.getOddSuffix() ==null?"":pub.getOddSuffix();
            date = pub.getOddCode() ==null? getDate():pub.getOddCode();
//            billType = pub.getInterlockMark();
            billType = pub.getOddPrefix();
        }

        Serial serial = new Serial();
        serial.setSerialPrefix(date);
        serial.setType(billType);
        serial.setDelflag(DeleteFlag.VALID.getCode());
        serial.setTenantId(tenantId);

        serial = serialMapper.getByObj(serial);
        if(null == serial){
            serial = new Serial();
            serial.setSerialPrefix(date);
            serial.setType(billType);
            serial.setDelflag(DeleteFlag.VALID.getCode());
            serial.setTenantId(tenantId);
            serial.setSerialNo(1);
            serial.setCreateTime(new Date());
            serial.setModifiTime(new Date());
            serial.setStatus(1);
            serialMapper.add(serial);
        }
        Map map = new HashMap();
        map.put("id", serial.getId());
        int serialNo = serial.getSerialNo();
        int i = 0;
        while(true) {
        	map.put("serialNo", serialNo);
        	int result = serialMapper.updateSerialNo(map);
        	if(result > 0)
        	{
        		serial.setSerialNo(serialNo);
        		break;
        	}
        	map.remove("serialNo");
        	serialNo++;
        	i++;
        	if(i > 10)
        	{
        		return "";
        	}
        }

        String code = fillNo(serial.getSerialNo(), num);

        if(pub !=null){
            // 把code插入连锁日记表
            code = billType+date+oddSplit+code;
            if(!StringUtil.isEmpty(oddSuffix)){
                code +=oddSplit+oddSuffix;
            }
            adOdddLog(pub,organizationId,code);
        }else{
            code = billType+date+code;
        }
        return code;
	}
    
    /**
     * 左位补零
     * @param serialNo
     * @param num
     * @return
     */
    private String fillNo(Integer serialNo, Integer num){

        StringBuilder no = new StringBuilder();
        int length = serialNo.toString().length();
        for(int i=0 ; i<num-length ; i++){
            no.append("0");
        }
        no.append(serialNo);
        return no.toString();
    }
    
    /**
     * 获取当前日期
     * @return
     */
    private String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        return date;
    }


    /**
     * 生成基础资料模块流水号
     * @param typeCode
     * @param maxCode
     * @return
     */
    @Override
    public String generatorDatSerialNo(String typeCode, String maxCode, int defaultNum){
        if(typeCode == null)
        {
            typeCode = "";
        }
        if (null==maxCode|| "".equals(maxCode)){

            StringBuilder sb = new StringBuilder();
            for(int i=1 ; i<defaultNum ; i++){
                sb.append("0");
            }
            sb.append("1");

            maxCode = typeCode + sb.toString();
        }else{
            StringBuilder sb = new StringBuilder(maxCode);

            sb = sb.delete(0, typeCode.length());
            if(StringUtil.isEmpty(sb.toString())){
                for(int i=1 ; i<defaultNum ; i++){
                    sb.append("0");
                }
                sb.append("1");

                maxCode = sb.toString();
            }else {
                maxCode = sb.toString();
                maxCode = generateByAsc(maxCode);
            }
            maxCode = typeCode + maxCode;
        }
        return maxCode;
    }

    /**
     * 通过asc码增长生成code
     * 通过国际标准的asc码进行转换
     * @param code
     * @return
     */
    public String generateByAsc(String code){

        int[] arr = generateAscArr(code);
        //是否需要进位
        boolean carry = false;

        for(int i=code.length()-1 ; i>=0 ; i--){

            int value = arr[i];

            if(value>=57){
                carry = true;
                arr[i] = 48;
            }else{

                //
/*               if(value>=57 && value<=64){
                   arr[i] = 65;
               }else if(value>=90 && value<=96){
                   arr[i] = 97;
               }else{*/
                arr[i] = value + 1;
                /*               }*/
                carry = false;
                break;
            }
        }

        //进位操作
        if(carry){
            int[] temp = arr;
            arr = new int[temp.length+1];
            arr[0] = 49;

            for(int i=0 ; i<temp.length ; i++){
                arr[i+1] = temp[i];
            }
        }

        code = generateStringArr(arr);
        return code;

    }

    /**
     * 将asc数组转成字符串
     * @param arr
     * @return
     */
    private String generateStringArr(int[] arr){

        StringBuilder sb = new StringBuilder("");

        for(int i=0 ; i<arr.length ; i++){
            sb.append((char)arr[i]);
        }
        return sb.toString();
    }

    /**
     * 将字符串转成asc数组
     * @param code
     * @return
     */
    private int[] generateAscArr(String code){

        int[] result = new int[code.length()];
        char[] codeArr = code.toCharArray();

        for(int i=0 ; i<codeArr.length ; i++){
            result[i] = codeArr[i];
        }

        return result;
    }

}