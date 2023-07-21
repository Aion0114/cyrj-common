package com.cyrj.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

public class MessageUtil {
	
	//可接收消息类型
    public static final String MESSAGE_TEXT = "text";//文本消息
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_CUSTOMER="transfer_customer_service";//客服消息
    public static final String EVENT_SUB = "subscribe";//关注事件
    public static final String EVENT_SCAN = "SCAN";//扫描事件
    public static final String EVENT_UNSUB = "unsubscribe";//取消关注事件
    public static final String EVENT_CLICK = "CLICK";
    public static final String EVENT_VIEW = "VIEW";
    public static final String EVENT_User_Get_Card = "user_get_card";//用户领取卡券事件
    public static final String EVENT_CUSTOMER= "CUSTOMERMSG";//联系客服

    /**
     * xml转为map
     * @param request
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request )
    {
    	Map<String,String> map = new HashMap<String, String>();
        try {

            SAXReader reader = new SAXReader();

            InputStream ins = request.getInputStream();
            Document doc = reader.read(ins);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getText());
            }
            ins.close();
           
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        return map;
    }

    public static String textMessageToXml(TextMessage textMessage){
        XStream xstream = new XStream();
        xstream.alias("xml", textMessage.getClass());
        return xstream.toXML(textMessage);

    }
    public static String initText(String toUserName, String fromUserName, String content){
        TextMessage text = new TextMessage();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setMsgType(MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }
    //微信客服系统接入
    public static String sendCustomerMessage(String toUserName, String fromUserName, String content){
        TextMessage text = new TextMessage();
        text.setToUserName(fromUserName);
        text.setFromUserName(toUserName);
        text.setMsgType(MESSAGE_CUSTOMER);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }
    
}
