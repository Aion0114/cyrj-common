package com.cyrj.pub.service.impl;

import com.cyrj.common.constant.SystemKey;
import com.cyrj.common.constant.ValidationMessge;
import com.cyrj.common.db.DataSourceHolder;
import com.cyrj.common.enumeration.DeleteFlag;
import com.cyrj.common.service.impl.BaseServiceImpl;
import com.cyrj.common.util.SMSUtil;
import com.cyrj.common.util.UUIDUtil;
import com.cyrj.pub.mapper.VerificationCodeMapper;
import com.cyrj.pub.pojo.Serial;
import com.cyrj.pub.pojo.VerificationCode;
import com.cyrj.pub.service.SerialService;
import com.cyrj.pub.service.VerificationCodeService;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("verificationCodeService")
public class VerificationCodeServiceImpl extends BaseServiceImpl<VerificationCode> implements VerificationCodeService {

    @Autowired
    VerificationCodeMapper verificationCodeMapper;

    /**
     * 创建验证码
     * @return
     */
    @Override
    public Map<String, Object> add(String phoneNo,Integer type){
    	DataSourceHolder.setDataSource(null);
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setDelflag(DeleteFlag.VALID.getCode());
        verificationCode.setPhoneNo(phoneNo);
        verificationCode.setType(type);
        verificationCode.setInvalidTime(new Date());
        verificationCode = verificationCodeMapper.getByObj(verificationCode);

        if(null == verificationCode){
            String code = UUIDUtil.getNumUUID(4);
            verificationCode = new VerificationCode();
            verificationCode.setDelflag(DeleteFlag.VALID.getCode());
            verificationCode.setPhoneNo(phoneNo);
            verificationCode.setType(type);
            verificationCode.setVerificationCode(code);
            Date now = new Date();
            Date afterDate = new Date(now.getTime() + 300000);
            verificationCode.setCreateTime(now);
            verificationCode.setInvalidTime(afterDate);
            try {
                verificationCodeMapper.add(verificationCode);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e);
            } catch (NoSuchAlgorithmException e) {
                System.out.println(e);
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if(SMSUtil.sendCodeMsg(verificationCode.getPhoneNo(),verificationCode.getVerificationCode())){
            result.put("verificationCode", verificationCode.getVerificationCode());
        }else{
            result.put(SystemKey.ERROR_KEY, ValidationMessge.USER_MSG_CODE_SEND_ERROR);
        }
        return result;

    }

    @Override
    public boolean isValid(String phoneNo, String code, Integer type) {
    	DataSourceHolder.setDataSource(null);
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setDelflag(DeleteFlag.VALID.getCode());
        verificationCode.setPhoneNo(phoneNo);
        verificationCode.setType(type);
        verificationCode.setVerificationCode(code);
        verificationCode.setInvalidTime(new Date());
        verificationCode = verificationCodeMapper.getByObj(verificationCode);
        
        if(null == verificationCode){
            return false;
        }else
            if(type.compareTo(1) != 0){ //找回密码验证不用删除
                verificationCode.setDelflag(0);
                verificationCodeMapper.delete(verificationCode);
            }

            return true;

    }

    //验证手机号码是否是管理员手机号码
	@Override
	public VerificationCode getByphoneNo(VerificationCode verificationCode) {		
		return verificationCodeMapper.findByPhoneNo(verificationCode);
	}

}
