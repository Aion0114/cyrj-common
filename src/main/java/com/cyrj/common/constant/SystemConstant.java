package com.cyrj.common.constant;

/**
 * Created by ChuWang on 2018/5/10.
 * 系统常量
 */
public interface SystemConstant {

    /**
     * 套餐模块
     */
    interface Package{
        /**
         * 租户套餐支付状态
         */
        interface RentOrderStatus{
            int 待支付 = 0;
            int 已支付 = 1;
            int 已取消 = 2;
            int 已失败 = 3;
            int 已退款 = 4;
        }

        /**
         * 租户套餐开通类型
         */
        interface TenantPackageType{
            int 套餐 = 1;
            int 菜单 = 2;
        }

        /**
         * 租户套餐开通状态
         */
        interface TenantPackageStatus{
            int 待开通 = 1;
            int 已开通 = 2;
        }

        /**
         * 租户套餐主表开通状态
         */
        interface TenantPackageMainStatus{
            int 开通 = 0;
            int 续期 = 1;
            int 取消 = 2;
        }

        /**
         * 租户套餐记录状态
         */
        interface TenantPackageDelflag{
            int 删除 = 0;
            int 正常 = 1;
        }

        /**
         * 系统套餐状态
         */
        interface PackageStatus{
            int 停用 = 0;
            int 可用 = 1;
        }

        /**
         * 系统套餐周期
         */
        interface PackagePeriod{
            int 年 = 1;
            int 月 = 2;
            int 周 = 3;
            int 日 = 4;
        }

        /**
         * 系统套餐类型
         */
        interface PackageType{
            int 不试用 = 0;
            int 试用 = 1;
        }

        /**
         * 系统套餐试用天数
         */
        interface TryoutDays{
            int 试用天数 = 60;
        }

        /**
         * 系统套餐类型
         */
        interface PackageVersion{
            int 免费版 = 0;
            int 标准版 = 1;
        }

    }

    /**
     * 支付模块
     */
    interface Pay{

        /**
         * 发起支付来源
         */
        interface PaySource{
            String 未知 = "0";
            String 平台 = "1";
            String 官网 = "2";
        }

        /**
         * 购物车支付状态
         */
        interface ShoppingCartStatus{
            int 待支付 = 0;
            int 已支付 = 1;
        }

        /**
         * 请求目的
         */
        interface RequestAim{
            int 创建订单 = 0; //创建订单并返回二维码
            int 刷新订单 = 1; //只刷新订单的二维码
        }

    }

    /**
     * 数据删除状态
     */
    interface DataDelflag{
        int 删除 = 0;
        int 正常 = 1;
    }

    /**
     * 数据停用状态
     */
    interface DataStatus{
        int 停用 = 0;
        int 正常 = 1;
    }

    /**
     * 活动
     */
    interface Activity{

        /**
         * 活动类型
         */
        interface ActivityType{
            String 赠送使用天数 = "AT001";
            String 返现 = "AT002";
        }

        /**
         * 是否随机礼物
         */
        interface RandomGift{
            String 随机礼物 = "1";
            String 非随机礼物 = "0";
        }

        /**
         * 活动领取状态
         */
        interface ActivityReceiveStatus{
            String 未领取 = "0";
            String 已领取 = "1";
        }

        /**
         * 是否可重复领取
         */
        interface RepeatedlyGift{
            String 不可重复领取 = "0";
            String 可以重复领取 = "1";
        }

        interface ActivityDetailType{
            String 首次登陆 = "ADT0001";
            String 支付 = "ADT0002";
            String 关注公众号 = "ADT0003";
            String 绑定微信手机报表 = "ADT0004";
            String 反馈问题 = "ADT0005";
            String 完善资料 = "ADT0006";
            String 每日开单并打印 = "ADT0007";
            String 邀请注册 = "ADT0008";
            String 接受邀请注册 = "ADT0009";
        }


    }

    /**
     * 租户模块
     */
    interface TenantInfo{

        /**
         * 租户状态
         */
        interface TenantStatus{
            int 停用 = 0;
            int 正常 = 1;
        }

        /**
         * 租户是否被赠送过菜单或天数
         */
        interface isGiven{
            String 未赠送过 = "0";
            String 已赠送过 = "1";
        }
    }

    /**
     * 财务模块
     */
    interface Financial{
        interface AccountingVoucherStatus{
            int 待审核 = 1;
            int 审核通过 = 2;
        }
    }

}
