package com.cyrj.common.util;

public class TextMessage {
	
	  private String FromUserName;
	  private String ToUserName;
	  private String MsgType;
	  private Long CreateTime;
	  private String Content;
	  
	  
		public String getFromUserName() {
			return FromUserName;
		}
		public void setFromUserName(String fromUserName) {
			FromUserName = fromUserName;
		}
		public String getToUserName() {
			return ToUserName;
		}
		public void setToUserName(String toUserName) {
			ToUserName = toUserName;
		}
		public String getMsgType() {
			return MsgType;
		}
		public void setMsgType(String msgType) {
			MsgType = msgType;
		}
		public Long getCreateTime() {
			return CreateTime;
		}
		public void setCreateTime(Long createTime) {
			CreateTime = createTime;
		}
		public String getContent() {
			return Content;
		}
		public void setContent(String content) {
			Content = content;
		}
  
}
