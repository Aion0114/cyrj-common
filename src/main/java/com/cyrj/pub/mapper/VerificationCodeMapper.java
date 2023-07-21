package com.cyrj.pub.mapper;

import com.cyrj.common.mapper.BaseMapper;
import com.cyrj.pub.pojo.Serial;
import com.cyrj.pub.pojo.VerificationCode;

public interface VerificationCodeMapper extends BaseMapper<VerificationCode> {

	VerificationCode findByPhoneNo(VerificationCode verificationCode);
}
