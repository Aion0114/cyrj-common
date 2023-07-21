package com.cyrj.common.constant;

/**
 * 系统内部校验错误信息 遵循规则 模块名-错误描述 例子：比如销售模块出现了单号重复问题 销售模块就是 sale 单号重复
 * saleno_is_duplicate 那么此错误信息应该命名为下 sale_soaleno_is_duplicate 模块命名 USER -- 用户模块
 * BASIC -- 基础资料模块 PURCHASE -- 采购模块 SALE -- 销售模块 INVENTORY -- 库存模块
 *
 * 后续模块请持续添加
 * 
 * @author ly
 * @date 2017-12-06
 *
 */
public final class ValidationMessge {

	public static final String DIRECTION_NOT_NULL = null;

	/**
	 * 用户手机号已经注册
	 */
	public static String USER_REGISTER_TELEPHONE_DUPLICATE = "用户手机号已经申请注册";

	/**
	 * 短信验证码发送失败
	 */
	public static String USER_MSG_CODE_SEND_ERROR = "user_msg_code_send_error";

	/**
	 * 用户名已经注册
	 */
	public static String USER_REGISTER_USERNAME_DUPLICATE = "user_register_username_duplicate";

	/**
	 * 用户注册校验码无效
	 */
	public static String USER_REGISTER_VERIFICATION_CODE_INVALID = "user_register_verification_code_invalid";

	/**
	 * 用户忘记密码校验码无效
	 */
	public static String USER_FORGETPWD_VERIFICATION_CODE_INVALID = "user_forgetpwd_verification_code_invalid";

	/**
	 * 用户机器申请认证Mac为空
	 */
	public static String USER_COMPUTER_APPLY_MAC_IS_NULL = "user_computer_apply_mac_is_null";

	/**
	 * 用户机器申请认证申请信息为空
	 */
	public static String USER_COMPUTER_APPLY_APPLICATIONINFO_IS_NULL = "user_computer_apply_applicationinfo_is_null";

	/**
	 * 用户机器未申请认证
	 */
	public static String USER_COMPUTER_DID_NOT_APPLY_FOR_CERTIFICATION = "user_computer_did_not_apply_for_certification";

	/**
	 * 用户机器重复申请认证
	 */
	public static String USER_COMPUTER_ALREADY_APPLY_FOR_CERTIFICATION = "user_computer_already_apply_for_certification";

	/**
	 * 用户机器未激活
	 */
	public static String USER_COMPUTER_IS_INACTIVATED = "user_computer_is_inactivated";

	/**
	 * 用户机器不在有效日期内
	 */
	public static String USER_COMPUTER_IS_NOT_IN_VALID_DATE = "user_computer_is_not_in_valid_date";

	/**
	 * 用户机器不在有效时间内
	 */
	public static String USER_COMPUTER_IS_NOT_IN_VALID_TIME = "user_computer_is_not_in_valid_time";

	/**
	 * 租户注册创库失败
	 */
	public static String USER_REGISTER_CREATE_USER_DB_ERROR = "user_register_create_user_db_error";

	/**
	 * 租户账号为空
	 */
	public static String USER_TENANT_ACCOUNT_IS_NULL = "user_tenant_account_is_null";

	/**
	 * 租户公司名为空
	 */
	public static String USER_TENANT_COMPANYNAME_IS_NULL = "user_tenant_companyname_is_null";

	/**
	 * 租户地址为空
	 */
	public static String USER_TENANT_ADDRESS_IS_NULL = "user_tenant_address_is_null";

	/**
	 * 租户邮箱为空
	 */
	public static String USER_TENANT_EMAIL_IS_NULL_OR_PATTERN_ERROR = "user_tenant_email_is_null_or_pattern_error";

	/**
	 * 租户地区编号为空
	 */
	public static String USER_TENANT_AREA_CODE_IS_NULL = "user_tenant_area_code_is_null";

	/**
	 * 租户地区名称为空
	 */
	public static String USER_TENANT_AREA_NAME_IS_NULL = "user_tenant_area_name_is_null";

	/**
	 * 租户行业ID为空
	 */
	public static String USER_TENANT_INDUSTRY_ID_IS_NULL = "user_tenant_industry_id_is_null";

	/**
	 * 租户行业名称为空
	 */
	public static String USER_TENANT_INDUSTRY_NAME_IS_NULL = "user_tenant_industry_name_is_null";

	/**
	 * 租户不存在
	 */
	public static String USER_TENANT_NOT_FOUND = "user_tenant_not_found";

	/**
	 * 租户旧密码错误
	 */
	public static String USER_TENANT_OLDPWD_ERROR = "user_tenant_oldpwd_error";

	/**
	 * 销售单据重复
	 */
	public static String SALE_SALENO_IS_DUPLICATE = "sale_saleno_is_duplicate";

	/**
	 * 库存盘点单没有完成 不能重新开启新的盘点单
	 */
	public static String INVENTORY_CHECK_BILL_NOT_DONE = "inventory_check_bill_not_done";

	/**
	 * 库存盘点单已审核
	 */
	public static String INVENTORY_CHECK_BILL_IS_CHECKED = "inventory_check_bill_is_checked";

	/**
	 * 商品编码为空
	 */
	public static String BASIC_GOODS_CODE_IS_NULL = "basic_goods_code_is_null";

	/**
	 * 商品名称为空
	 */
	public static String BASIC_GOODS_NAME_IS_NULL = "basic_goods_name_is_null";

	/**
	 * 商品编码重复
	 */
	public static String BASIC_GOODS_CODE_IS_DUPLICATE = "basic_goods_code_is_duplicate";

	/**
	 * 商品有关联数据
	 */
	public static String BASIC_GOODS_HAVE_RELATED_DATA = "basic_goods_have_related_data";

	/**
	 * 同步云商品库商品类型为空
	 */
	public static String BASIC_GOODS_SYNC_YUN_GOODS_TYPEID_IS_NULL = "basic_goods_sync_yun_goods_typeid_is_null";

	/**
	 * 同步云商品库品牌为空
	 */
	public static String BASIC_GOODS_SYNC_YUN_GOODS_BRAND_NAME_IS_NULL = "basic_goods_sync_yun_goods_brand_name_is_null";

	/**
	 * 同步云商品库商品列表为空
	 */
	public static String BASIC_GOODS_SYNC_YUN_GOODS_LIST_IS_NULL = "basic_goods_sync_yun_goods_list_is_null";

	/**
	 * 基本信息名称不能为空
	 */
	public static String BASIC_DICTIONARY_NAME_IS_NULL = "basic_dictionary_name_is_null";

	/**
	 * 基本信息名称重复
	 */
	public static String BASIC_DICTIONARY_NAME_IS_DUPLICATE = "basic_dictionary_name_is_duplicate";

	/**
	 * 商品规格错误
	 */
	public static String BASIC_GOODS_SPECIFICATION_IS_NULL_OR_ZERO = "basic_goods_specification_is_null_or_zero";

	/**
	 * 类型名称不能为空
	 */
	public static String BASIC_TYPE_NAME_IS_NULL = "basic_type_name_is_null";

	/**
	 * 类型编码重复
	 */
	public static String BASIC_TYPE_CODE_IS_DUPLICATE = "basic_type_code_is_duplicate";

	/**
	 * 类型不存在
	 */
	public static String BASIC_TYPE_IS_NOT_EXISTS = "basic_type_is_not_exists";

	/**
	 * 类型已有子类
	 */
	public static String BASIC_TYPE_HAVE_SUBCLASS = "basic_type_have_subclass";

	/**
	 * 类型已有数据
	 */
	public static String BASIC_TYPE_HAVE_DETAIL_DATA = "basic_type_have_detail_data";

	/**
	 * 联系人姓名不能为空
	 */
	public static String BASIC_CONTACTS_NAME_IS_NULL = "basic_contacts_name_is_null";

	/**
	 * 联系人编码重复
	 */
	public static String BASIC_CONTACTS_CODE_IS_DUPLICATE = "basic_contacts_code_is_duplicate";

	/**
	 * 往来记事联系人不能为空
	 */
	public static String BASIC_INTERCOURSE_CONTACT_IS_NULL = "basic_intercourse_contact_is_null";

	/**
	 * 供应商编码不能为空
	 */
	public static String BASIC_SUPPLIER_CODE_IS_NULL = "basic_supplier_code_is_null";

	/**
	 * 供应商名称不能为空
	 */
	public static String BASIC_SUPPLIER_NAME_IS_NULL = "basic_supplier_name_is_null";

	/**
	 * 供应商类型id不能为空
	 */
	public static String BASIC_SUPPLIER_TYPEID_IS_NULL = "basic_supplier_typeid_is_null";

	/**
	 * 供应商编码重复
	 */
	public static String BASIC_SUPPLIER_CODE_IS_DUPLICATE = "basic_supplier_code_is_duplicate";

	/**
	 * 供应商有关联数据
	 */
	public static String BASIC_SUPPLIER_HAVE_RELATED_DATA = "basic_supplier_have_related_data";

	/**
	 * 客户编码不能为空
	 */
	public static String BASIC_CUSTOMER_CODE_IS_NULL = "basic_customer_code_is_null";

	/**
	 * 客户名称不能为空
	 */
	public static String BASIC_CUSTOMER_NAME_IS_NULL = "basic_customer_name_is_null";

	/**
	 * 客户类型id不能为空
	 */
	public static String BASIC_CUSTOMER_TYPEID_IS_NULL = "basic_customer_typeId_is_null";

	/**
	 * 客户编码重复
	 */
	public static String BASIC_CUSTOMER_CODE_IS_DUPLICATE = "basic_customer_code_is_duplicate";

	/**
	 * 客户有关联数据
	 */
	public static String BASIC_CUSTOMER_HAVE_RELATED_DATA = "basic_customer_have_related_data";

	/**
	 * 仓库编码不能为空
	 */
	public static String BASIC_ORGANIZATION_CODE_IS_NULL = "basic_organization_code_is_null";

	/**
	 * 仓库所属机构id不能为空
	 */
	public static String BASIC_ORGANIZATION_ORGANIZATIONID_IS_NULL = "basic_organization_organizationId_is_null";

	/**
	 * 仓库编码重复
	 */
	public static String BASIC_ORGANIZATION_CODE_IS_DUPLICATE = "basic_organization_code_is_duplicate";

	/**
	 * 仓库有关联数据
	 */
	public static String BASIC_ORGANIZATION_HAVE_RELATED_DATA = "basic_organization_have_related_data";

	/**
	 * 仓库所属机构已停用
	 */
	public static String BASIC_ORGANIZATION_BELONGS_DEPORT_HAS_BEEN_DISABLED = "basic_organization_belongs_deport_has_been_disabled";

	/**
	 * 员工编码不能为空
	 */
	public static String BASIC_EMPLOYEE_CODE_IS_NULL = "basic_employee_code_is_null";

	/**
	 * 员工姓名不能为空
	 */
	public static String BASIC_EMPLOYEE_NAME_IS_NULL = "basic_employee_name_is_null";

	/**
	 * 员工工号不能为空
	 */
	public static String BASIC_EMPLOYEE_JOBNUMBER_IS_NULL = "basic_employee_jobnumber_is_null";

	/**
	 * 员工工号不能为空
	 */
	public static String BASIC_EMPLOYEE_MOBILEPHONE_IS_NULL = "basic_employee_mobilephone_is_null";

	/**
	 * 员工电话号码重复
	 */
	public static String BASIC_EMPLOYEE_PHONE_IS_DUPLICATE = "basic_employee_phone_is_duplicate";

	/**
	 * 验证码无效
	 */
	public static String BASIC_EMPLOYEE_VERIFICATION_CODE_INVALID = "basic_employee_verificationc_ode_invalid";

	/**
	 * 修改企业信息电话验证码无效
	 */
	public static String PERSONAL_TENANTINFO_VERIFICATION_CODE_INVALID = "personal_tenantinfo_verification_code_invalid";

	/**
	 * 非系统用户不可修改企业信息
	 */
	public static String PERSONAL_TENANTINFO_USER_IS_NOT_SYSTEM_USER = "personal_tenantinfo_user_is_not_system_user";

	/**
	 * 修改企业信息电话重复
	 */
	public static String PERSONAL_TENANTINFO_TELEPHONE_DUPLICATE = "personal_tenantinfo_telephone_duplicate";

	/**
	 * 员工类型id不能为空
	 */
	public static String BASIC_EMPLOYEE_TYPEID_IS_NULL = "basic_employee_typeId_is_null";

	/**
	 * 员工编码重复
	 */
	public static String BASIC_EMPLOYEE_CODE_IS_DUPLICATE = "basic_employee_code_is_duplicate";

	/**
	 * 员工工号重复
	 */
	public static String BASIC_EMPLOYEE_JOBNUMBER_IS_DUPLICATE = "basic_employee_jobnumber_is_duplicate";

	/**
	 * 员工有关联数据
	 */
	public static String BASIC_EMPLOYEE_HAVE_RELATED_DATA = "basic_employee_have_related_data";

	/**
	 * 机构编码不能为空
	 */
	public static String BASIC_AUTHORGANIZATION_CODE_IS_NULL = "basic_authorganization_code_is_null";

	/**
	 * 机构名称不能为空
	 */
	public static String BASIC_AUTHORGANIZATION_NAME_IS_NULL = "basic_authorganization_name_is_null";

	/**
	 * 机构类型id不能为空
	 */
	public static String BASIC_AUTHORGANIZATION_TYPEID_IS_NULL = "basic_authorganization_typeId_is_null";

	/**
	 * 机构编码重复
	 */
	public static String BASIC_AUTHORGANIZATION_CODE_IS_DUPLICATE = "basic_authorganization_code_is_duplicate";

	/**
	 * 机构下有子类
	 */
	public static String BASIC_AUTHORGANIZATION_HAVE_SUBCLASS = "basic_authorganization_hava_subclass";

	/**
	 * 机构下有仓库
	 */
	public static String BASIC_AUTHORGANIZATION_HAVE_STOREHOUSE = "basic_authorganization_have_storehouse";

	/**
	 * 机构下没有仓库
	 */
	public static String BASIC_AUTHORGANIZATION_NOT_HAVE_STOREHOUSE = "basic_authorganization_not_have_storehouse";

	/**
	 * 采购单据没有明细
	 */
	public static String PURCHASE_BILL_NOT_HAVE_DETAIL = "purchase_bill_not_have_detail";

	/**
	 * 采购单据没有仓库
	 */
	public static String PURCHASE_BILL_NOT_HAVE_STOREHOUSE_ID = "purchase_bill_not_have_storehouse_id";

	/**
	 * 采购单据明细件价为空
	 */
	public static String PURCHASE_BILL_DETAIL_ITEM_PRICE_IS_NULL = "purchase_bill_detail_item_price_is_null";

	/**
	 * 采购单据明细散价为空
	 */
	public static String PURCHASE_BILL_DETAIL_SCATTERED_PRICE_IS_NULL = "purchase_bill_detail_scattered_price_is_null";

	/**
	 * 采购单据明细件价不能为负数
	 */
	public static String PURCHASE_BILL_DETAIL_ITEM_PRICE_CAN_NOT_BE_MINUS = "purchase_bill_detail_item_price_can_not_be_minus";

	/**
	 * 采购单据明细散价不能为负数
	 */
	public static String PURCHASE_BILL_DETAIL_SCATTERED_PRICE_CAN_NOT_BE_MINUS = "purchase_bill_detail_scattered_price_can_not_be_minus";

	/**
	 * 采购单据明细数量不能为0
	 */
	public static String PURCHASE_BILL_DETAIL_QUANTITY_CAN_NOT_BE_ZERO = "purchase_bill_detail_quantity_can_not_be_zero";

	/**
	 * 采购单据没有供应商
	 */
	public static String PURCHASE_BILL_NOT_HAVE_SUPPLIER_ID = "purchase_bill_not_have_supplier_id";

	/**
	 * 采购单据没有机构
	 */
	public static String PURCHASE_BILL_NOT_HAVE_ORGANIZATION_ID = "purchase_bill_not_have_organization_id";

	/**
	 * 采购单据已经支付
	 */
	public static String PURCHASE_BILL_IS_PAYED = "purchase_bill_is_payed";

	/**
	 * * 采购单据已经审核
	 */
	public static String PURCHASE_BILL_IS_CHECKED = "purchase_bill_is_checked";

	/**
	 * 采购单据还未审核
	 */
	public static String PURCHASE_BILL_IS_NOT_CHECK = "purchase_bill_is_not_check";

	/**
	 * 采购策略，不允许同时输入件数和散数
	 */
	public static String PURCHASE_BILL_DOES_NOT_ALLOW_THE_ITEMQUANTITY_AND_SCATTEREDQUANTITY_AT_THE_SAME_TIME = "purchase_bill_does_not_allow_the_itemquantity_and_scatteredquantity_at_the_same_time";

	/**
	 * 单据不存在
	 */
	public static String BILL_IS_NOT_EXISTS = "bill_is_not_exists";

	/**
	 * 单据已结存
	 */
	public static String BILL_IS_PAYED = "bill_is_payed";

	/**
	 * 单据禁止修改日期
	 */
	public static String BILL_CAN_NOT_MODIFY_FILL_DATE = "bill_can_not_modify_fill_date";

	/**
	 * 已存在负库存
	 */
	public static String INVENTORY_QUANTITY_BELOW_ZERO_IS_EXISTS = "inventory_quantity_below_zero_is_exists";

	/**
	 * 销售单据已审核
	 */
	public static String SALE_SALE_BILL_IS_CHECKED = "sale_sale_bill_is_checked";

	/**
	 * 销售单据没有客户
	 */
	public static String SALE_SALE_BILL_NOT_HAVE_CUSTOMER_ID = "sale_sale_bill_not_have_customer_id";

	/**
	 * 销售单据没有机构
	 */
	public static String SALE_SALE_BILL_NOT_HAVE_ORGANIZATION_ID = "sale_sale_bill_not_have_organization_id";

	/**
	 * 销售单据沒有仓库
	 */
	public static String SALE_SALE_BILL_NOT_HAVE_STOREHOUSE_ID = "sale_sale_bill_not_have_storehouse_id";

	/**
	 * 销售单据明细件价为空
	 */
	public static String SALE_SALE_BILL_DETAIL_ITEM_PRICE_IS_NULL = "sale_sale_bill_detail_item_price_is_null";

	/**
	 * 销售单据明细散价为空
	 */
	public static String SALE_SALE_BILL_DETAIL_SCATTERED_PRICE_IS_NULL = "sale_sale_bill_detail_scattered_price_is_null";

	/**
	 * 销售单据明细件价不能为负数
	 */
	public static String SALE_SALE_BILL_DETAIL_ITEM_PRICE_CAN_NOT_BE_MINUS = "sale_sale_bill_detail_item_price_can_not_be_minus";

	/**
	 * 销售单据明细散价不能为负数
	 */
	public static String SALE_SALE_BILL_DETAIL_SCATTERED_PRICE_CAN_NOT_BE_MINUS = "sale_sale_bill_detail_scattered_price_can_not_be_minus";

	/**
	 * 销售单据明细数量不能为0
	 */
	public static String SALE_SALE_BILL_DETAIL_QUANTITY_CAN_NOT_BE_ZERO = "sale_sale_bill_detail_quantity_can_not_be_zero";

	/**
	 * 销售单据沒有明细
	 */
	public static String SALE_SALE_BILL_NOT_HAVE_DETAIL = "sale_sale_bill_not_have_detail";

	/**
	 * 销售单据已结款，不能反审
	 */
	public static String SALE_SALE_BILL_IS_PAYED = "sale_sale_bill_is_payed";

	/**
	 * 销售策略，不允许同时输入件数和散数
	 */
	public static String SALE_BILL_DOES_NOT_ALLOW_THE_ITEMQUANTITY_AND_SCATTEREDQUANTITY_AT_THE_SAME_TIME = "sale_bill_does_not_allow_the_itemquantity_and_scatteredquantity_at_the_same_time";

	/**
	 * 销售策略，库存不足
	 */
	public static String SALE_BILL_INVENTORY_IS_NOT_ENOUGH = "sale_bill_inventory_is_not_enough";

	/**
	 * 销售策略，售价低于售价1
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_SP1 = "sale_bill_sale_sp_below_goods_sp1";
	/**
	 * 销售策略，售价低于售价2
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_SP2 = "sale_bill_sale_sp_below_goods_sp2";

	/**
	 * 销售策略，售价低于售价3
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_SP3 = "sale_bill_sale_sp_below_goods_sp3";

	/**
	 * 销售策略，售价低于售价4
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_SP4 = "sale_bill_sale_sp_below_goods_sp4";

	/**
	 * 销售策略，售价低于售价5
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_SP5 = "sale_bill_sale_sp_below_goods_sp5";

	/**
	 * 销售策略，售价低于配送价
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_DISTRIBUTIONSP = "sale_bill_sale_sp_below_goods_distributionsp";

	/**
	 * 销售策略，售价低于零售价
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_RETAILPRICE = "sale_bill_sale_sp_below_goods_retailprice";

	/**
	 * 销售策略，售价低于会员价
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_VIPPRICE = "sale_bill_sale_sp_below_goods_vipprice ";

	/**
	 * 销售策略，售价低于最新进价
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_NEWESTSP = "sale_bill_sale_sp_below_goods_newestsp ";

	/**
	 * 销售策略，售价低于面价
	 */
	public static String SALE_BILL_SALE_SP_BELOW_GOODS_FACTORYSP = "sale_bill_sale_sp_below_goods_factorysp ";

	/**
	 * 开单时单据明细数量不能为0
	 */
	public static String SALE_SALE_BILL_DETAIL_QUANTITY_ZERO = "sale_sale_bill_detail_quantity_zero";

	/**
	 * 促销单据已审核
	 */
	public static String SALE_PROMOTION_BILL_IS_CHECKED = "sale_promotion_bill_is_checked";

	/**
	 * 促销单据不能反审
	 */
	public static String SALE_PROMOTION_BILL_CAN_NOT_BACKCHECK = "sale_promotion_bill_can_not_backCheck";

	/**
	 * 促销单没有选择客户
	 */
	public static String SALE_PROMOTION_BILL_NOT_HAVE_CUSTOMER = "sale_promotion_bill_not_have_customer";

	/**
	 * 促销开始日期不能为空
	 */
	public static String SALE_PROMOTION_BILL_BEGINDATE_IS_NULL = "sale_promotion_bill_begindate_is_null";

	/**
	 * 促销结束日期不能为空
	 */
	public static String SALE_PROMOTION_BILL_ENDDATE_IS_NULL = "sale_promotion_bill_enddate_is_null";

	/**
	 * 促销结束日期不能小于开始日期
	 */
	public static String SALE_PROMOTION_BILL_ENDDATE_CAN_NOT_LESS_THAN_BEGINDATE = "sale_promotion_bill_enddate_can_not_less_than_begindate";

	/**
	 * 促销结束日期不能小于当天
	 */
	public static String SALE_PROMOTION_BILL_ENDDATE_CAN_NOT_LESS_THAN_TODAY = "sale_promotion_bill_enddate_can_not_less_than_today";

	/**
	 * 促销单没有明细
	 */
	public static String SALE_PROMOTION_BILL_NOT_HAVE_DETAIL = "sale_promotion_bill_not_have_detail";

	/**
	 * 促销明细主表id不能为空
	 */
	public static String SALE_PROMOTION_DETAIL_MAINID_IS_NULL = "sale_promotion_detail_mainid_is_null";

	/**
	 * 促销单明细商品id不能为空
	 */
	public static String SALE_PROMOTION_DETAIL_GOODSID_IS_NULL = "sale_promotion_detail_goodsid_is_null";

	/**
	 * 促销单明细原散售价不能为空
	 */
	public static String SALE_PROMOTION_DETAIL_GETORIGINALSP_IS_NULL = "sale_promotion_detail_getoriginalsp_is_null";

	/**
	 * 促销单明细散促销价不能为空
	 */
	public static String SALE_PROMOTION_DETAIL_GETSP_IS_NULL = "sale_promotion_detail_getsp_is_null";

	/**
	 * 促销单明细散促销价不能为负数
	 */
	public static String SALE_PROMOTION_DETAIL_GETSP_CAN_NOT_BE_MINUS = "sale_promotion_detail_getsp_can_not_be_minus";

	/**
	 * 促销商品不存在
	 */
	public static String SALE_PROMOTION_GOODS_IS_NOT_EXISTS = "sale_promotion_goods_is_not_exists";

	/**
	 * 调价单已审核
	 */
	public static String SALE_ADJUSTPRICE_BILL_IS_CHECKED = "sale_adjustprice_bill_is_checked";

	/**
	 * 调价单没有明细
	 */
	public static String SALE_ADJUSTPRICE_BILL_NOT_HAVE_DETAIL = "sale_adjustprice_bill_not_have_detail";

	/**
	 * 调价明细主表id不能为空
	 */
	public static String SALE_ADJUSTPRICE_DETAIL_MAINID_IS_NULL = "sale_adjustprice_detail_mainid_is_null";

	/**
	 * 调价单明细商品id不能为空
	 */
	public static String SALE_ADJUSTPRICE_DETAIL_GOODSID_IS_NULL = "sale_adjustprice_detail_goodsid_is_null";

	/**
	 * 调价单批量更新明细列不存在
	 */
	public static String SALE_ADJUXTPRICE_BILL_UPDATE_DETAIL_FIELD_OUT_OF_BOUND = "sale_adjuxtprice_bill_update_detail_field_out_of_bound";

	/**
	 * 转仓单已审核
	 */
	public static String INVENTORY_TRANSFER_BILL_IS_CHECKED = "inventory_transfer_bill_is_checked";

	/**
	 * 转仓单不能反审
	 */
	public static String INVENTORY_TRANSFER_BILL_CAN_NOT_BACKCHECK = "inventory_transfer_bill_can_not_backCheck";

	/**
	 * 转仓单出库仓库不能为空
	 */
	public static String INVENTORY_TRANSFER_OUTSTOREHOUSE_IS_NULL = "inventory_transfer_outstorehouse_is_null";

	/**
	 * 转仓单入库仓库不能为空
	 */
	public static String INVENTORY_TRANSFER_INSTOREHOUSE_IS_NULL = "inventory_transfer_instorehouse_is_null";

	/**
	 * 转仓单出库仓库该商品库存不足
	 */
	public static String INVENTORY_TRANSFER_OUTSTOREHOUSE_NOT_HAVE_ENOUGH_GOODS = "inventory_transfer_outstorehouse_not_have_enough_goods";

	/**
	 * 转仓单没有明细
	 */
	public static String INVENTORY_TRANSFER_BILL_NOT_HAVE_DETAIL = "inventory_transfer_bill_not_have_detail";

	/**
	 * 转仓明细主表id不能为空
	 */
	public static String INVENTORY_TRANSFER_DETAIL_MAINID_IS_NULL = "inventory_transfer_detail_mainid_is_null";

	/**
	 * 转仓单据明细件价为空
	 */
	public static String INVENTORY_TRANSFER_DETAIL_ITEM_PRICE_IS_NULL = "inventory_transfer_detail_item_price_is_null";

	/**
	 * 转仓单据明细散价为空
	 */
	public static String INVENTORY_TRANSFER_DETAIL_SCATTERED_PRICE_IS_NULL = "inventory_transfer_detail_scattered_price_is_null";

	/**
	 * 转仓单据明细件价不能为负数
	 */
	public static String INVENTORY_TRANSFER_DETAIL_ITEM_PRICE_CAN_NOT_BE_MINUS = "inventory_transfer_detail_item_price_can_not_be_minus";

	/**
	 * 转仓单据明细散价不能为负数
	 */
	public static String INVENTORY_TRANSFER_DETAIL_SCATTERED_PRICE_CAN_NOT_BE_MINUS = "inventory_transfer_detail_scattered_price_can_not_be_minus";

	/**
	 * 转仓单明细商品不能为空
	 */
	public static String INVENTORY_TRANSFER_DETAIL_GOODSID_IS_NULL = "inventory_transfer_detail_goodsid_is_null";

	/**
	 * 转仓单数量不能为0
	 */
	public static String INVENTORY_TRANSFER_DETAIL_QUANTITY_CAN_NOT_BE_ZERO = "inventory_transfer_detail_quantity_can_not_be_zero";

	/**
	 * 转仓商品不存在
	 */
	public static String INVENTORY_TRANSFER_GOODS_IS_NOT_EXISTS = "inventory_transfer_goods_is_not_exists";

	/**
	 * 转仓明细数量不不能为0
	 */
	public static String INVENTORY_TRANSFER_DETAIL_NUM_IS_ZERO = "inventory_transfer_detail_num_is_zero";

	/**
	 * 加工单已审核
	 */
	public static String INVENTORY_MACHINING_BILL_IS_CHECKED = "inventory_machining_bill_is_checked";

	/**
	 * 加工单出库明细不能为空
	 */
	public static String INVENTORY_MACHINING_BILL_OUTSTOREHOUSE_NOT_HAVE_DETAIL = "inventory_machining_bill_outstorehouse_not_have_detail";

	/**
	 * 加工单入库明细不能为空
	 */
	public static String INVENTORY_MACHINING_BILL_INSTOREHOUSE_NOT_HAVE_DETAIL = "inventory_machining_bill_instorehouse_not_have_detail";

	/**
	 * 加工单不能反审
	 */
	public static String INVENTORY_MACHINING_BILL_CAN_NOT_BACKCHECK = "inventory_machining_bill_can_not_backCheck";

	/**
	 * 加工单出库仓库不能为空
	 */
	public static String INVENTORY_MACHINING_OUTSTOREHOUSE_IS_NULL = "inventory_machining_outstorehouse_is_null";

	/**
	 * 加工单入库仓库不能为空
	 */
	public static String INVENTORY_MACHINING_INSTOREHOUSE_IS_NULL = "inventory_machining_instorehouse_is_null";

	/**
	 * 加工单数量不不能为0
	 */
	public static String INVENTORY_MACHINING_DETAIL_NUM_IS_ZERO = "inventory_machining_detail_num_is_zero";

	/**
	 * 加工单成本价不能为空
	 */
	public static String INVENTORY_MACHINING_DETAIL_COST_IS_NULL = "inventory_machining_detail_cost_is_null";

	/**
	 * 加工单成本价不能为负数
	 */
	public static String INVENTORY_MACHINING_DETAIL_COST_CAN_NOT_BE_MINUS = "inventory_machining_detail_cost_can_not_be_minus";

	/**
	 * 加工单明细主表id不能为空
	 */
	public static String INVENTORY_MACHINING_DETAIL_MAINID_IS_NULL = "inventory_machining_detail_mainid_is_null";

	/**
	 * 加工单明细商品id不能为空
	 */
	public static String INVENTORY_MACHINING_DETAIL_GOODSID_IS_NULL = "inventory_machining_detail_goodsid_is_null";

	/**
	 * 加工单出库仓库中没有该商品
	 */
	public static String INVENTORY_MACHINING_OUTSTOREHOUSE_NOT_HAVE_THIS_GOODS = "inventory_machining_outstorehouse_not_have_this_goods";

	/**
	 * 加工单出库仓库中该商品库存不足
	 */
	public static String INVENTORY_MACHINING_OUTSTOREHOUSE_NOT_HAVE_ENOUGH_GOODS = "inventory_machining_outstorehouse_not_have_enough_goods";

	/**
	 * 加工商品不存在
	 */
	public static String INVENTORY_MACHINING_GOODS_IS_NOT_EXISTS = "inventory_machining_goods_is_not_exists";

	/**
	 * 成本调价单已审核
	 */
	public static String INVENTORY_COSTADJUST_BILL_IS_CHECKED = "inventory_costadjust_bill_is_checked";

	/**
	 * 成本调价单不能反审
	 */
	public static String INVENTORY_COSTADJUST_BILL_CAN_NOT_BACKCHECK = "inventory_costadjust_bill_can_not_backcheck";

	/**
	 * 成本调价单没有明细
	 */
	public static String INVENTORY_COSTADJUST_BILL_NOT_HAVE_DETAIL = "inventory_costadjust_bill_not_have_detail";

	/**
	 * 成本调价单主表和明细表机构不一致
	 */
	public static String INVENTORY_COSTADJUST_BILL_MAIN_AND_DETAIL_ORGANIZATIONID_NOT_SAME = "inventory_costadjust_bill_main_and_detail_organizationid_not_same";

	/**
	 * 成本调价单明细主表id不能为空
	 */
	public static String INVENTORY_COSTADJUST_DETAIL_MAINID_IS_NULL = "inventory_costadjust_detail_mainid_is_null";

	/**
	 * 成本调价单明细商品id不能为空
	 */
	public static String INVENTORY_COSTADJUST_DETAIL_GOODSID_IS_NULL = "inventory_costadjust_detail_goodsid_is_null";

	/**
	 * 成本调价单明细新成本价不能为空
	 */
	public static String INVENTORY_COSTADJUST_DETAIL_COST_IS_NULL = "inventory_costadjust_detail_cost_is_null";

	/**
	 * 成本调价单明细新成本价不能小于0
	 */
	public static String INVENTORY_COSTADJUST_DETAIL_COST_CAN_NOT_LESS_THEN_ZERO = "inventory_costadjust_detail_cost_can_not_less_then_zero";

	/**
	 * 成本调价单已有该明细
	 */
	public static String INVENTORY_COSTADJUST_BILL_HAVE_THIS_DETAIL = "inventory_costadjust_bill_have_this_detail";

	/**
	 * 仓库已经锁定
	 */
	public static String INVENTORY_STOREHOUSE_IS_LOCK = "inventory_storehouse_is_lock";

	/**
	 * 存在没有审核的单据 不能结存
	 */
	public static String INVENTORY_BILL_HAS_NOT_CHECK = "inventory_bill_has_not_check";

	/**
	 * 结存日期重叠
	 */
	public static String INVENTORY_BALANCE_DATE_IS_OVERLAP = "inventory_balance_date_is_overlap";

	/**
	 * 上期结存还未完成
	 */
	public static String INVENTORY_BALANCE_PREVIOUS_IS_NOT_DONE = "inventory_balance_previous_is_not_done";

	/**
	 * 下期结存已经完成 不能反结存
	 */
	public static String INVENTORY_BALANCE_NEXT_IS_DONE = "inventory_balance_next_is_done";

	/**
	 * 当期已经结存不能再次结存
	 */
	public static String INVENTORY_BALANCE_IS_DONE = "inventory_balance_is_done";

	/**
	 * 当期已经反结存不能再次反结存
	 */
	public static String INVENTORY_BACK_BALANCE_IS_DONE = "inventory_back_balance_is_done";

	/**
	 * 期初单已经完成不能再次进行期初录入
	 */
	public static String INVENTORY_BEGIN_BILL_IS_DONE = "inventory_begin_bill_is_done";

	/**
	 * 期初录入单明细重复
	 */
	public static String INVENTORY_BEGIN_BILL_DETAIL_DUPLICATE = "inventory_begin_bill_detail_duplicate";

	/**
	 * 期初录入单已经审核
	 */
	public static String INVENTORY_BEGIN_BILL_IS_CHECKED = "inventory_begin_bill_is_checked";

	/**
	 * 期初录入单已经反审
	 */
	public static String INVENTORY_BEGIN_BILL_IS_BACK_CHECKED = "inventory_begin_bill_is_back_checked";

	/**
	 * 导入期初单据数据无效
	 */
	public static String INVENTORY_BEGIN_BILL_IMPORT_IS_INVALID = "inventory_begin_bill_import_is_invalid";

	/**
	 * 期初录入单没有找到
	 */
	public static String INVENTORY_BEGIN_BILL_HAS_NOT_FOUND = "inventory_begin_bill_has_not_found";

	/**
	 * 期初录入单明细没有找到
	 */
	public static String INVENTORY_BEGIN_BILL_DETAIL_HAS_NOT_FOUND = "inventory_begin_bill_detail_has_not_found";

	/**
	 * 用户名不存在
	 */
	public static String USER_USERNAME_IS_NOT_EXISTS = "user_username_is_not_exists";

	/**
	 * 用户忘记密码验证码错误
	 */
	public static String USER_FORGETPASSWORD_VERIFICATIONCODE_IS_ERROR = "user_forgetpassword_verificationcode_is_error";

	/**
	 * 用户忘记密码租户ID错误
	 */
	public static String USER_FORGETPASSWORD_TENANTID_IS_ERROR = "user_forgetpassword_tenantid_is_error";

	/**
	 * 用户忘记密码用户名错误
	 */
	public static String USER_FORGETPASSWORD_USERNAME_IS_ERROR = "user_forgetpassword_username_is_error";

	/**
	 * 用户忘记密码电话错误
	 */
	public static String USER_FORGETPASSWORD_PHONENO_IS_ERROR = "user_forgetpassword_phoneno_is_error";

	/**
	 * 用户名已存在
	 */
	public static String USER_USERNAME_IS_EXISTS = "user_username_is_exists";

	/**
	 * 系统用户不可操作
	 */
	public static String USER_IS_SYSTEM_USER = "user_is_system_user";

	/**
	 * 用户没有电话号码
	 */
	public static String USER_USER_NOT_HAVE_PHONENO = "user_user_not_have_phoneno";

	/**
	 * 关联员工已分配
	 */
	public static String USER_RELATED_EMPLOYEE_IS_DISTRIBUTE = "user_related_employee_is_distribute";

	/**
	 * 用户原密码不正确
	 */
	public static String USER_USER_OLD_PASSWORD_IS_ERROR = "user_user_old_password_is_error";

	/**
	 * 角色名已存在
	 */
	public static String ROLE_NAME_IS_EXISTS = "role_name_is_exists";

	/**
	 * 角色已绑定用户
	 */
	public static String ROLE_IS_BIND_USER = "role_is_bind_user";

	/**
	 * 系统角色不可操作
	 */
	public static String ROLE_IS_SYSTEM_ROLE = "role_is_system_role";

	/**
	 * 其他系统对接接口购物车创建人为空
	 */
	public static String EXPOSE_SHOPPINGCART_CREATOR_IS_NULL = "expose_shoppingcart_creator_is_null";

	/**
	 * 其他系统对接接口购物车修改人为空
	 */
	public static String EXPOSE_SHOPPINGCART_MODIFIER_IS_NULL = "expose_shoppingcart_modifier_is_null";

	/**
	 * 其他系统对接接口购物车ID错误
	 */
	public static String EXPOSE_SHOPPINGCART_ID_IS_ERROR = "expose_shoppingcart_id_is_error";

	/**
	 * 其他系统对接接口购物车已存在商品
	 */
	public static String EXPOSE_SHOPPINGCART_ALREADY_HAVE_GOODS = "expose_shoppingcart_already_have_goods";

	/**
	 * 其他系统对接接口购物车商品ID为空
	 */
	public static String EXPOSE_SHOPPINGCART_GOODSID_IS_NULL = "expose_shoppingcart_goodsid_is_null";

	/**
	 * 其他系统对接接口购物车商品不存在
	 */
	public static String EXPOSE_SHOPPINGCART_GOODS_IS_NOT_EXISTS = "expose_shoppingcart_goods_is_not_exists";

	/**
	 * 其他系统对接接口购物车商品对应套餐不存在
	 */
	public static String EXPOSE_SHOPPINGCART_GOODS_PACKAGE_IS_NOT_EXISTS = "expose_shoppingcart_goods_package_is_not_exists";

	/**
	 * 其他系统对接接口购物车已购买过其他付费商品,免费试用商品添加失败
	 */
	public static String EXPOSE_SHOPPINGCART_ALREADY_BUY_ANOTHER_PAID_GOODS = "expose_shoppingcart_already_buy_another_paid_goods";

	/**
	 * 其他系统对接接口购物车免费试用商品只能添加一次
	 */
	public static String EXPOSE_SHOPPINGCART_FREE_GOODS_ONLY_SELECTED_ONCE = "expose_shoppingcart_free_goods_only_selected_once";

	/**
	 * 其他系统对接接口购物车租户ID为空
	 */
	public static String EXPOSE_SHOPPINGCART_TENANTID_IS_NULL = "expose_shoppingcart_tenantid_is_null";

	/**
	 * 其他系统对接接口购物车支付来源为空
	 */
	public static String EXPOSE_SHOPPINGCART_ORDER_SOURCE_IS_NULL = "expose_shoppingcart_order_source_is_null";

	// =====活动模块=====
	/**
	 * 保存成功
	 */
	public static String ACTIVITY_IS_NULL = "activity_is_null";

	// =====通用提示=====
	/**
	 * 根据id未查询到数据
	 */
	public static String QUERY_ERROR = "根据id未查询到数据";

	/**
	 * 保存错误
	 */
	public static String SAVE_ERROR = "保存错误";

	/**
	 * 保存成功
	 */
	public static String SAVE_SUCCEED = "保存成功";

	/**
	 * 删除成功
	 */
	public static String DEL_SUCCEED = "删除成功";

	/**
	 * 删除错误
	 */
	public static String DEL_ERROR = "删除错误";

	/**
	 * id参数错误
	 */
	public static String ID_RRROR = "id参数错误";

	/**
	 * 用户名不存在
	 */
	public static String USER_NO = "用户名不存在";
	
	/**
	 * 拒绝登录
	 */
	public static String NOT_LOGIN = "拒绝登录";
	/**
	 * 密码错误
	 */
	public static String PASSWORD_ERROR = "密码错误";

	/**
	 * 该社会信用代码已注册
	 */
	public static String CREDITCODE_IS_NULL = "社会信用代码为空";

	/**
	 * 该社会信用代码已注册
	 */
	public static String CREDITCODE_DUPLICATE = "该社会信用代码已注册";
}
