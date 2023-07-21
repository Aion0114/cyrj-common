package com.cyrj.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyrj.common.config.RedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 企业微信开发接口公共类
 *
 * @author cw
 * @date 2022/11/15 8:50
 * @return
 */
public class WeChatWorkUtil {
    private static Log logger = LogFactory.getLog(StringUtil.class);

    //获取Token地址
    private static final String GET_TOKEN_PATH = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    //获取审批模板详情地址
    private static final String GET_TEMPLATE_PATH = "https://qyapi.weixin.qq.com/cgi-bin/oa/gettemplatedetail";

    //手机号获取userid地址
    private static final String GET_USER_ID = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserid";

    //提交审批申请地址
    private static final String POST_APPLY_EVENT_PATH = "https://qyapi.weixin.qq.com/cgi-bin/oa/applyevent";

    //获取审批模板详情地址
    private static final String GET_APPROVAL_DETAIL_PATH = "https://qyapi.weixin.qq.com/cgi-bin/oa/getapprovaldetail";

    /**
     * 获取企业微信开发accessToken
     * <p>
     * *获取access_token是调用企业微信API接口的第一步，相当于创建了一个登录凭证，其它的业务API接口，都需要依赖于access_token来鉴权调用者身份。
     * *因此开发者，在使用业务接口前，要明确access_token的颁发来源，使用正确的access_token。
     * <p>
     *
     * @param corpid     企业ID
     * @param corpsecret 应用的凭证密钥
     * @return java.util.Map
     * @author cw
     * @date 2022/11/15 9:38
     */
    public static Map getAccessToken(String corpid, String corpsecret) {
        Map resultMap = new HashMap();
        JSONObject js = RedisUtil.getCorpid(corpid);
        String access_token = "";
        if (js == null) {
            try {
                String result = HttpUtil.sendGet(GET_TOKEN_PATH + "?corpid=" + corpid + "&corpsecret=" + corpsecret);
                logger.error("获取企业微信access_token结果 >>>> " + result);
                JSONObject token = JSON.parseObject(result);
                if (!token.containsKey("access_token")) {
                    resultMap.put("result", false);
                    resultMap.put("error", JSON.toJSONString(token));
                    return resultMap;
                }
                access_token = token.getString("access_token");

                RedisUtil.createCorpid(corpid, access_token);
            } catch (Exception e) {
                resultMap.put("result", false);
                resultMap.put("error", e.getMessage());
                return resultMap;
            }
        } else {
            access_token = js.getString("access_token");
        }
        resultMap.put("result", true);
        resultMap.put("corpid", corpid);
        resultMap.put("corpsecret", corpsecret);
        resultMap.put("access_token", access_token);
        return resultMap;
    }

    /**
     * 根据手机号码获取企业微信用户ID
     *
     * @param accessToken 调用接口凭证
     * @param phone       用户在企业微信通讯录中的手机号码
     * @return java.util.Map
     * @author cw
     * @date 2022/11/15 14:29
     */
    public static String getUserId(String accessToken, String phone) {
        Map<String, String> params = new HashMap<>();
        params.put("mobile", phone);

        String result = null;
        try {
            result = HttpUtil.sendPost(GET_USER_ID + "?access_token=" + accessToken, JSON.toJSONString(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        JSONObject json = JSON.parseObject(result);
        if (!json.getString("errcode").equals("0")) {
            return "";
        }

        return json.getString("userid");
    }

    /**
     * 获取审批模板详情
     *
     * @param accessToken 调用接口凭证
     * @param templateId  模板的唯一标识id
     * @return java.util.Map
     * @author cw
     * @date 2022/11/15 11:33
     */
    public static Map getTemplateDetail(String accessToken, String templateId) {
        Map resultMap = new HashMap();

        //模板格式数据列表，格式根据提交审批申请接口返回参数apply_data中contents进行组装
        List<Map<String, Object>> contentList = new ArrayList<>();

        Map<String, String> params = new HashMap<>();
        params.put("template_id", templateId);
        try {
            String result = HttpUtil.sendPost(GET_TEMPLATE_PATH + "?access_token=" + accessToken, JSON.toJSONString(params));
            JSONObject json = JSON.parseObject(result);
            if (!json.getString("errcode").equals("0")) {
                resultMap.put("result", false);
                resultMap.put("errcode", json.getString("errcode"));
                resultMap.put("error", json.getString("errmsg"));
                return resultMap;
            }

            //数据组装
            JSONObject templateContentObj = json.getJSONObject("template_content");
            JSONArray contentArray = templateContentObj.getJSONArray("controls");
            for (int i = 0; i < contentArray.size(); i++) {
                Map<String, Object> data = new HashMap<>();
                JSONObject control = contentArray.getJSONObject(i);
                JSONObject property = control.getJSONObject("property");//模板控件属性
                JSONObject config = control.getJSONObject("config");//模板控件配置
                String c = property.getString("control");//控件类型
                data.put("control", c);
                data.put("id", property.get("id"));//控件id
                data.put("require", property.get("require"));//是否必填：1-必填；0-非必填
                JSONArray titleArray = property.getJSONArray("title");
                data.put("title", titleArray.getJSONObject(0).getString("text"));

                //现仅支持（时间、单选/多选）模板控件配置；如有需要可扩展后续配置
                switch (c) {
                    case "Date":
                        data.put("config", config.get("date"));
                        break;
                    case "Selector":
                        data.put("config", config.get("selector"));
                        break;
                    default:
                        data.put("config", null);
                }
                contentList.add(data);
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            resultMap.put("result", false);
            resultMap.put("error", e.getMessage());
            return resultMap;
        }

        resultMap.put("result", true);
        resultMap.put("contentList", contentList);
        return resultMap;
    }

    /**
     * 提交审批申请
     *
     * @param accessToken 调用接口凭证
     * @param userId      申请人userid
     * @param templateId  模板id
     * @param contentList 审批申请详情列表
     * @param useTemplate 审批人模式：0-通过接口指定审批人、抄送人（此时approver、notifyer等参数可用）; 1-使用此模板在管理后台设置的审批流程(需要保证审批流程中没有“申请人自选”节点)，支持条件审批。默认为0
     * @return java.util.Map
     * @author cw
     * @date 2022/11/16 10:15
     */
    public static Map<String, Object> applyEvent(String accessToken, String userId, String templateId, List<Map<String, Object>> contentList,
                                                 Integer useTemplate, Map<String, Object> textInfo) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> bodyParam = new HashMap<>();
        bodyParam.put("creator_userid", userId);
        bodyParam.put("template_id", templateId);
        bodyParam.put("use_template_approver", useTemplate);

        Map<String, Object> applyDataContents = new HashMap<>();
        applyDataContents.put("contents", contentList);
        bodyParam.put("apply_data", applyDataContents);

        if (useTemplate == 0) {
            //暂不使用
            List<Map<String, Object>> approverList = new ArrayList<>();
            Map<String, Object> approverContent = new HashMap<>();
            approverContent.put("attr", 1);
            approverContent.put("userid", Collections.singletonList(""));
            approverList.add(approverContent);
            bodyParam.put("approver", approverList);
        }

        List<Map<String, Object>> summaryList = new ArrayList<>();
        Map<String, Object> summaryInfo = new HashMap<>();
        //************* 摘要信息，用于显示在审批通知卡片、审批列表的摘要信息 ******************
        List<Map<String, Object>> summaryInfoList = new ArrayList<>();
        Map<String, Object> infoText = new HashMap<>();
        if (textInfo != null && textInfo.get("goodsName") != null) {
            infoText.put("text", "商品【" + textInfo.get("goodsName") + "】低于成本价销售");
        } else {
            infoText.put("text", "");
        }
        infoText.put("lang", "zh_CN");
        summaryInfoList.add(infoText);
        summaryInfo.put("summary_info", summaryInfoList);
        summaryList.add(summaryInfo);
        bodyParam.put("summary_list", summaryList);
        //***********************************************************************************

        System.out.println(JSON.toJSONString(bodyParam));

        try {
            String result = HttpUtil.sendPost(POST_APPLY_EVENT_PATH + "?access_token=" + accessToken, JSON.toJSONString(bodyParam));
            JSONObject json = JSON.parseObject(result);
            System.out.println("提交审批申请返回数据 ------> json:" + json.toJSONString());
            if (!json.getString("errcode").equals("0")) {
                resultMap.put("result", false);
                resultMap.put("error", json.getString("errmsg"));
                return resultMap;
            }

            resultMap.put("result", true);
            resultMap.put("sp_no", json.getString("sp_no"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            resultMap.put("result", false);
            resultMap.put("error", e.getMessage());
            return resultMap;
        }

        return resultMap;
    }

    /**
     * 获取审批申请状态
     *
     * @param accessToken 调用接口凭证。必须使用审批应用或企业内自建应用的secret获取
     * @param spNo        审批单编号
     * @return java.util.Map
     * @author cw
     * @date 2022/11/15 14:29
     */
    public static Integer getApprovalDetail(String accessToken, String spNo) {
        Integer spStatus = 0;
        Map<String, String> params = new HashMap<>();
        params.put("sp_no", spNo);

        String result = null;
        try {
            result = HttpUtil.sendPost(GET_APPROVAL_DETAIL_PATH + "?access_token=" + accessToken, JSON.toJSONString(params));
            System.out.println("获取审批申请状态 查询结果：" + result);
            JSONObject json = JSON.parseObject(result);
            if (!json.getString("errcode").equals("0")) {
                return null;
            }
            JSONObject infoJson = json.getJSONObject("info");
            if (infoJson != null) {
                spStatus = infoJson.getInteger("sp_status");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return spStatus;
    }

}
