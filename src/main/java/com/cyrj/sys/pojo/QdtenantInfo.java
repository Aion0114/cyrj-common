package com.cyrj.sys.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class QdtenantInfo {
    private Integer id;

    private String account;

    private String password;

    private String serverIp;

    private Integer serverPort;

    private String dbName;

    private String dbAccount;

    private String dbPassword;

    private String companyName;

    private Date validDate;

    private Date invaDate;

    private String phoneNo;

    private String areaCode;

    private String areaName;

    private Integer industryId;

    private String industryName;

    private String email;

    private String address;

	private String longitude;// 地址经度

	private String latitude;// 地址纬度

    private Integer status;

    private Date lastLoginDate;

    private Date created;

    private Date updated;

    private Integer delflag;

    private String isGiven;

    private Integer shareId; // 判断是否通过二维码分享注册的，是的话则保存分享人租户id，不是的则为null'
 
    private String systemType;  // 系统平台类型，如C8、B2、......

    private Integer isLesso; // 是否从联塑获取发货单   1 是 0 否

    private String remark; // 备注
    
    private String lessoCode;//联塑编号
    
    private String edition;//版本(业务字段与数据库无对应)
    
    private String managerId; // 业务经理ID
    
    private String scienceId; // 技术顾问ID
    
    private String serviceId; // 专属客服ID
    
    private String managerName; // 业务经理名称
    
    private String scienceName; //基数顾问名称
    
    private String serviceName; //专属客服名称
    
    private String departmentName;//部门名称  
    
    private String departId;//部门ID       

    private String managerPhone;
    
    private String servicePhone;
    
    private String sciencePhone;
    
    private String infoQQ;
    
    private String departmentPhone;
    
    private String helpmeNum;
    
    private String statusName;     
    
    private String scienceIcon; 
    
    private String serviceIcon; 
    
    private String managerIcon; 
    
    private int tenantType; //客户类型

	private String tenantTypeName; //客户类型名称

	private Integer onlinePcNum;//电脑端允许同时在线人数

	private String clientverSion;//版本号

	private String version;//版本

	private String labelStr;//标签

	private String offAccExpires;//公众号到期提醒时间

	private Boolean isExpires;//到期是否已审核

	private Date isExpiresDate;//点击时间

	private String taxpayerName;

	private String taxpayerNum;

	private String offAccRemark;
	
	private Integer isFacility;//是否授信(悦金采授信)

	private String creditCode;

	private String userName; // 用户名称

	private String nickName; // 用户昵称

	private String trueName; // 用户姓名

	private Date renewalEndTime;//到期时间

	private BigDecimal renewalAmount;//客户当前续费金额

	private String renewalRemark;//续费备注

	private Integer appPeopleNum; // 手机端在线用户数

	private String renwYear;
	private String renwMont;

	private Integer openFlag;

	private Integer compUserId;

	private Integer closeRenewFlag;

	private Integer parentId;//父级id

	private Integer closingFlag;//是否结档 0否 1是

	private Integer workOrderFlag;

	private Integer isInvoice; //增加发票功能 0 否 1 是

	private BigDecimal samoney; //应用基础金额

	private String serviceQQ;//服务qq号

	private Integer onlinePay;//是是否在线支付续费

	private Integer openingStatus;//开库状态 1已完成 2 开库中 3开库错误

	public String getServiceQQ() {
		return serviceQQ;
	}

	public void setServiceQQ(String serviceQQ) {
		this.serviceQQ = serviceQQ;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getClosingFlag() {
		return closingFlag;
	}

	public void setClosingFlag(Integer closingFlag) {
		this.closingFlag = closingFlag;
	}

	public Integer getAppPeopleNum() {
		return appPeopleNum;
	}

	public void setAppPeopleNum(Integer appPeopleNum) {
		this.appPeopleNum = appPeopleNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getScienceIcon() {
		return scienceIcon;
	}

	public void setScienceIcon(String scienceIcon) {
		this.scienceIcon = scienceIcon;
	}

	public String getServiceIcon() {
		return serviceIcon;
	}

	public void setServiceIcon(String serviceIcon) {
		this.serviceIcon = serviceIcon;
	}

	public String getManagerIcon() {
		return managerIcon;
	}

	public void setManagerIcon(String managerIcon) {
		this.managerIcon = managerIcon;
	}

	public String getInfoQQ() {
		return infoQQ;
	}

	public void setInfoQQ(String infoQQ) {
		this.infoQQ = infoQQ;
	}

	public String getDepartmentPhone() {
		return departmentPhone;
	}

	public void setDepartmentPhone(String departmentPhone) {
		this.departmentPhone = departmentPhone;
	}

	public String getHelpmeNum() {
		return helpmeNum;
	}

	public void setHelpmeNum(String helpmeNum) {
		this.helpmeNum = helpmeNum;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getSciencePhone() {
		return sciencePhone;
	}

	public void setSciencePhone(String sciencePhone) {
		this.sciencePhone = sciencePhone;
	}

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getScienceName() {
		return scienceName;
	}

	public void setScienceName(String scienceName) {
		this.scienceName = scienceName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getScienceId() {
		return scienceId;
	}

	public void setScienceId(String scienceId) {
		this.scienceId = scienceId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getLessoCode() {
		return lessoCode;
	}

	public void setLessoCode(String lessoCode) {
		this.lessoCode = lessoCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbAccount() {
		return dbAccount;
	}

	public void setDbAccount(String dbAccount) {
		this.dbAccount = dbAccount;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getInvaDate() {
		return invaDate;
	}

	public void setInvaDate(Date invaDate) {
		this.invaDate = invaDate;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getDelflag() {
		return delflag;
	}

	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}

	public String getIsGiven() {
		return isGiven;
	}

	public void setIsGiven(String isGiven) {
		this.isGiven = isGiven;
	}

	public Integer getShareId() {
		return shareId;
	}

	public void setShareId(Integer shareId) {
		this.shareId = shareId;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public Integer getIsLesso() {
		return isLesso;
	}

	public void setIsLesso(Integer isLesso) {
		this.isLesso = isLesso;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public int getTenantType() {
		return tenantType;
	}

	public void setTenantType(int tenantType) {
		this.tenantType = tenantType;
	}

	public String getTenantTypeName() {
		return tenantTypeName;
	}

	public void setTenantTypeName(String tenantTypeName) {
		this.tenantTypeName = tenantTypeName;
	}

	public Integer getOnlinePcNum() {
		return onlinePcNum;
	}

	public void setOnlinePcNum(Integer onlinePcNum) {
		this.onlinePcNum = onlinePcNum;
	}

	public String getClientverSion() {
		return clientverSion;
	}

	public void setClientverSion(String clientverSion) {
		this.clientverSion = clientverSion;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLabelStr() {
		return labelStr;
	}

	public void setLabelStr(String labelStr) {
		this.labelStr = labelStr;
	}

	public String getOffAccExpires() {
		return offAccExpires;
	}

	public void setOffAccExpires(String offAccExpires) {
		this.offAccExpires = offAccExpires;
	}

	public Boolean getExpires() {
		return isExpires;
	}

	public void setExpires(Boolean expires) {
		isExpires = expires;
	}

	public Date getIsExpiresDate() {
		return isExpiresDate;
	}

	public void setIsExpiresDate(Date isExpiresDate) {
		this.isExpiresDate = isExpiresDate;
	}

	public String getTaxpayerName() {
		return taxpayerName;
	}

	public void setTaxpayerName(String taxpayerName) {
		this.taxpayerName = taxpayerName;
	}

	public String getTaxpayerNum() {
		return taxpayerNum;
	}

	public void setTaxpayerNum(String taxpayerNum) {
		this.taxpayerNum = taxpayerNum;
	}

	public String getOffAccRemark() {
		return offAccRemark;
	}

	public void setOffAccRemark(String offAccRemark) {
		this.offAccRemark = offAccRemark;
	}

	public Integer getIsFacility() {
		return isFacility;
	}

	public void setIsFacility(Integer isFacility) {
		this.isFacility = isFacility;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public Date getRenewalEndTime() {
		return renewalEndTime;
	}

	public void setRenewalEndTime(Date renewalEndTime) {
		this.renewalEndTime = renewalEndTime;
	}

	public BigDecimal getRenewalAmount() {
		return renewalAmount;
	}

	public void setRenewalAmount(BigDecimal renewalAmount) {
		this.renewalAmount = renewalAmount;
	}

	public String getRenewalRemark() {
		return renewalRemark;
	}

	public void setRenewalRemark(String renewalRemark) {
		this.renewalRemark = renewalRemark;
	}

	public String getRenwYear() {
		return renwYear;
	}

	public void setRenwYear(String renwYear) {
		this.renwYear = renwYear;
	}

	public String getRenwMont() {
		return renwMont;
	}

	public void setRenwMont(String renwMont) {
		this.renwMont = renwMont;
	}

	public Integer getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(Integer openFlag) {
		this.openFlag = openFlag;
	}

	public Integer getCloseRenewFlag() {
		return closeRenewFlag;
	}

	public void setCloseRenewFlag(Integer closeRenewFlag) {
		this.closeRenewFlag = closeRenewFlag;
	}

	public Integer getWorkOrderFlag() {
		return workOrderFlag;
	}

	public void setWorkOrderFlag(Integer workOrderFlag) {
		this.workOrderFlag = workOrderFlag;
	}

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public BigDecimal getSamoney() {
		return samoney;
	}

	public void setSamoney(BigDecimal samoney) {
		this.samoney = samoney;
	}

	public Integer getOnlinePay() {
		return onlinePay;
	}

	public void setOnlinePay(Integer onlinePay) {
		this.onlinePay = onlinePay;
	}

	public Integer getCompUserId() {
		return compUserId;
	}

	public void setCompUserId(Integer compUserId) {
		this.compUserId = compUserId;
	}

	public Integer getOpeningStatus() {
		return openingStatus;
	}

	public void setOpeningStatus(Integer openingStatus) {
		this.openingStatus = openingStatus;
	}
}