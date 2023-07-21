package com.cyrj.common.pojo;

import java.io.Serializable;
import java.util.Map;

public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    /** 搜索值 */
    private String keyword;
    
    /** 备注 */
    private String remark;


	private Integer delflag;

    /** 请求参数 */
    private Map<String, Object> params;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
    
    
}
