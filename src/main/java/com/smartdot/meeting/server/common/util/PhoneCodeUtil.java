package com.smartdot.meeting.server.common.util;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/** 
 * @ClassName: PhoneCodeUtil 
 * @Description: 手机验证码工具类
 * @author: haomt
 * @date: 2017年2月14日 上午9:59:21  
 */
public class PhoneCodeUtil {
	
	private static Log logger = LogFactory.getLog(PhoneCodeUtil.class);
	
  	/**
     * 发送 手机验证码
     * @param code:验证内容、phones:手机号、template:短信模版、type:1-短信  2-语音
     * @return 返回结果
     */
	public final synchronized static boolean sendVerificationCode(String code,
			String phones, String template, String type) {
		boolean result = false;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = format.format(new Date());

			if ("1".equals(type)) { // 短信
				result = shortMessageCode(code, phones, template, date);
			}
			if ("2".equals(type)) { // 语音
				result = voiceCode(code, phones, date);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("错误信息:  " + e.getMessage());
		}

		return result;
	}
  	
  	/**
     * 短信手机验证码
     * @param code:验证内容、phones:手机号(多个手机号用','英文逗号隔开,不能超过100个)、date:当前时间
     * @return 返回结果
     */
  	public static boolean shortMessageCode(String code,String phones,String template,String date) {
  		boolean result = false; 
  	 	try {			
			String url = "";
  	 		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
  	 		String YTX_URL = bundle.getString("YTX_URL");
  	 		String YTX_GESHI = bundle.getString("YTX_SOMESS_GESHI");
  	 		String YTX_ACCOUNT_SID = bundle.getString("YTX_ACCOUNT_SID");
  	 		String YTX_AUTH_TOKEN = bundle.getString("YTX_AUTH_TOKEN");
  	 		String YTX_APPLICATION_ID = bundle.getString("YTX_APPLICATION_ID");
  	 		String YTX_CONTENT_LENGTH = bundle.getString("YTX_CONTENT_LENGTH");
  	 		String code_fatime = bundle.getString("code_fatime");
			String sig = YTX_ACCOUNT_SID + YTX_AUTH_TOKEN + date;
  	 		sig = EncodEncryUtil.string2MD5(sig).toUpperCase();
  	 		YTX_GESHI = YTX_GESHI.replace("{accountSid}",YTX_ACCOUNT_SID).replace("{SigParameter}",sig);
  	 		url += YTX_URL + YTX_GESHI;
			String Authorization = EncodEncryUtil.base64CodeData(YTX_ACCOUNT_SID+":"+date);
			URL wsUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/xml");
            conn.addRequestProperty("Content-Type", "application/xml;charset=utf-8");
            conn.addRequestProperty("Content-Length", YTX_CONTENT_LENGTH);
            conn.addRequestProperty("Authorization", Authorization);
            
            OutputStream os = conn.getOutputStream();
            String soap = "";
			if (StringUtils.isNotBlank(code)) {
				soap = "<?xml version='1.0' encoding='utf-8'?> "
						+ "<TemplateSMS> " + "<to>" + phones + "</to> "
						+ "<appId>" + YTX_APPLICATION_ID + "</appId> "
						+ "<templateId>" + template + "</templateId>"
						+ "<datas>" + "<data>" + code + "</data>" + "<data>"
						+ code_fatime + "</data>" + "</datas>"
						+ "</TemplateSMS>";
			} else {
				soap = "<?xml version='1.0' encoding='utf-8'?> "
						+ "<TemplateSMS> " + "<to>" + phones + "</to> "
						+ "<appId>" + YTX_APPLICATION_ID + "</appId> "
						+ "<templateId>" + template + "</templateId>"
						+ "</TemplateSMS>";
			}
            	
	 		 os.write(soap.getBytes("utf-8"));
	         InputStream is = conn.getInputStream();
	         SAXReader saxReader = new SAXReader();	         
	         Document document = saxReader.read(is);
             Element root = document.getRootElement();
             Element statusCode = root.element("statusCode"); //返回结果标识
             Element statusMsg = root.element("statusMsg");//错误信息
			if (null != statusCode) {
				if ("000000".equals(statusCode.getText())) {
					result = true;
				}
			}
			if (null != statusMsg) {
				logger.info("手机号为：(" + phones + ") 短信接口返回错误结果："
						+ statusMsg.getText());
			}
	         is.close();
	         os.close();
	         conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("短信util报错信息", e);
        	logger.error("错误信息:  "+e.getMessage());
		}
  	 	return result;
  	 }
  	
  	/**
     * 语音手机验证码
     * @param code:验证内容、phones:手机号(只能是一个手机号)、date:当前时间
     * @return 返回结果 boolean
     */
  	public static boolean voiceCode(String code,String phones,String date) {
  		boolean result=false; 
  		InputStream is = null;
  		OutputStream os = null;
  		HttpURLConnection conn = null;
  	 	try {
			String url = "";
			ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
			String YTX_URL = bundle.getString("YTX_URL");
			String YTX_GESHI = bundle.getString("YTX_VOICE_GESHI");
			String YTX_ACCOUNT_SID = bundle.getString("YTX_ACCOUNT_SID");
			String YTX_AUTH_TOKEN = bundle.getString("YTX_AUTH_TOKEN");
			String YTX_APPLICATION_ID = bundle.getString("YTX_APPLICATION_ID");
			String YTX_CONTENT_LENGTH = bundle.getString("YTX_CONTENT_LENGTH");
			String YTX_VOICE_TYPE = bundle.getString("YTX_VOICE_TYPE");

			String YTX_VOICE_PLAYTIMES = bundle.getString("YTX_VOICE_PLAYTIMES");
			String YTX_VOICE_MAXCALLTIMES = bundle.getString("YTX_VOICE_MAXCALLTIMES");

			String sig = YTX_ACCOUNT_SID + YTX_AUTH_TOKEN + date;
			sig = EncodEncryUtil.string2MD5(sig).toUpperCase();
			YTX_GESHI = YTX_GESHI.replace("{accountSid}", YTX_ACCOUNT_SID)
					.replace("{SigParameter}", sig);
			url += YTX_URL + YTX_GESHI;
			String Authorization = EncodEncryUtil.base64CodeData(YTX_ACCOUNT_SID + ":" + date);
			URL wsUrl = new URL(url);
			conn = (HttpURLConnection) wsUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/xml");
			conn.addRequestProperty("Content-Type", "application/xml;charset=utf-8");
			conn.addRequestProperty("Content-Length", YTX_CONTENT_LENGTH);
			conn.addRequestProperty("Authorization", Authorization);
			os = conn.getOutputStream();
			String soap = "";
			if ("2".equals(YTX_VOICE_TYPE)) {
				// 自定义语音
				soap = "<?xml version='1.0' encoding='utf-8'?> "
						+ "<VoiceVerify> "
							+ "<to>" + phones + "</to> " 
							+ "<appId>" + YTX_APPLICATION_ID + "</appId> "
							+ "<verifyCode>" + code + "</verifyCode>" // 验证码内容，为数字和英文字母，不区分大小写，长度4-8位
							+ "<playTimes>" + YTX_VOICE_PLAYTIMES + "</playTimes>" 
							+ "<maxCallTime>" + YTX_VOICE_MAXCALLTIMES + "</maxCallTime>" // 该通话最大通话时长，到时间自动挂机
							+ "<playVerifyCode>yanzhengma.wav;1.wav;3.wav;5.wav;9.wav</playVerifyCode>"
						+ "</VoiceVerify>";
			} else {
				// 标准语音
				soap = "<?xml version='1.0' encoding='utf-8'?> "
						+ "<VoiceVerify> "
							+ "<to>" + phones + "</to> "
							+ "<appId>" + YTX_APPLICATION_ID + "</appId> "
							+ "<verifyCode>" + code + "</verifyCode>" // 验证码内容，为数字和英文字母，不区分大小写，长度4-8位
							+ "<playTimes>" + YTX_VOICE_PLAYTIMES + "</playTimes>" 
							+ "<maxCallTime>" + YTX_VOICE_MAXCALLTIMES + "</maxCallTime>" 
						+ "</VoiceVerify>";
			}

			os.write(soap.getBytes("utf-8"));
			is = conn.getInputStream();
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(is);
			Element root = document.getRootElement();
			Element statusCode = root.element("statusCode"); // 返回结果标识
			Element statusMsg = root.element("statusMsg");// 错误信息
			if (null != statusCode) {
				if ("000000".equals(statusCode.getText())) {
					result = true;
				}
			}
			if (null != statusMsg) {
				logger.info("手机号为：(" + phones + ") 短信接口返回错误结果："
						+ statusMsg.getText());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("错误信息:  " + e.getMessage());
		} finally {
			try {
				is.close();
				os.close();
				conn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
  	
}
