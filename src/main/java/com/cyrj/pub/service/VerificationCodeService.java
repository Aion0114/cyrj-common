package com.cyrj.pub.service;

import com.cyrj.common.service.BaseService;
import com.cyrj.pub.pojo.Serial;
import com.cyrj.pub.pojo.VerificationCode;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface VerificationCodeService extends BaseService<VerificationCode> {

    //创建验证码
    public Map<String, Object> add(String phoneNo,Integer type);

    //验证验证码是否正确
    public boolean isValid(String phoneNo, String code, Integer type);

    //验证手机号码是否正确
	public VerificationCode getByphoneNo(VerificationCode verificationCode);
}
