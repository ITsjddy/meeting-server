package com.smartdot.meeting.server.modules.jpushentiy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSONObject;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.DateUtils;
import com.smartdot.meeting.server.modules.datadictionary.service.IDataDictionaryService;
import com.smartdot.meeting.server.modules.jpushentiy.model.JpushByHand;
import com.smartdot.meeting.server.modules.jpushentiy.model.JpushEntiyForm;
import com.smartdot.meeting.server.modules.jpushentiy.model.JpushEntiyVO;
import com.smartdot.meeting.server.modules.jpushentiy.model.TitleAndTextForJpush;
import com.smartdot.meeting.server.modules.jpushentiy.service.IJpushEntiyService;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.modules.servicePersonnel.service.IServicePersonnelService;
import com.smartdot.meeting.server.common.entity.DataDictionary;
import com.smartdot.meeting.server.common.entity.JpushEntiy;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;



/** 
 * @ClassName: JpushEntiyController 
 * @Description: 极光推送
 * @author: haomt
 * @date: 2016年1月17日 下午1:43:19  
 */
@Controller
@RequestMapping(value = "/jpushEntiy")
public class JpushEntiyController {

	private static final Logger _LOG = Logger.getLogger(JpushEntiyController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IJpushEntiyService.SERVICE_NAME)
	private IJpushEntiyService jpushEntiyService;
	
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService iMemberService;
	
	@Resource(name = IServicePersonnelService.SERVICE_NAME)
	private IServicePersonnelService servicePersonnelService;
	
	@Resource(name = IDataDictionaryService.SERVICE_NAME)
	private IDataDictionaryService dataDictionaryService;
	
	@Resource(name = "taskExecutor")
	private TaskExecutor executor;
	
	@RequestMapping(value = "/openPage")
	public String openPage() throws Exception {
		return IJpushEntiyService.PAGE_PATH_NAME;
	}
	
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public List<JpushEntiyVO> queryAll() throws Exception {
		List<JpushEntiy> jpushEntiys = jpushEntiyService.findAll();
		List<JpushEntiyVO> vo = new ArrayList<JpushEntiyVO>();
		JpushEntiyVO jpushEntiyVO = null;
		for (JpushEntiy jpushEntiy : jpushEntiys) {
			jpushEntiyVO  = new JpushEntiyVO();
			BeanUtils.copyProperties(jpushEntiy, jpushEntiyVO);
			vo.add(jpushEntiyVO);
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public ReturnValue save(JpushEntiyForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		JpushEntiy jpushEntiy= new JpushEntiy();
		BeanUtils.copyProperties(form, jpushEntiy);
		boolean result;
		if (StringUtils.isEmpty(form.getId())) {
			result = jpushEntiyService.save(jpushEntiy);
		} else {
			result = jpushEntiyService.updateJpushEntiy(jpushEntiy);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(JpushEntiyController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(JpushByHand jbh) throws Exception {
		jbh.setJpUniteId(UUID.randomUUID().toString().replace("-", ""));
		ReturnValue returnValue =new ReturnValue();
		List<JpushEntiy> jesEntiys = new ArrayList<JpushEntiy>();
		String[] languages = jbh.getLanguages();
		List<String> jpTitleTexts = Arrays.asList(languages);
		JpushEntiy jpushEntiy= createJpushEntiy(jbh, null);
		jpushEntiy.setJpText(jbh.getJpText());
		jpushEntiy.setJpTitle(jbh.getJpTitle());
		jpushEntiy.setLanguageId(jbh.getLanguageId());
		jesEntiys.add(jpushEntiy);
		if (!jpTitleTexts.get(0).equals("{\"languageId\":\"\"}")&&!jpTitleTexts.get(0).equals("{}")) {
			if (jpTitleTexts.get(0).indexOf("jpTitle") == -1
					|| jpTitleTexts.get(0).indexOf("jpText") == -1
					|| jpTitleTexts.get(0).indexOf("languageId") == -1) {
				String jtt = jpTitleTexts.get(0) + "," + jpTitleTexts.get(1) + "," + jpTitleTexts.get(2);
				TitleAndTextForJpush tatj =  JSONObject.parseObject(jtt, TitleAndTextForJpush.class);
				if (tatj.getLanguageId() != "") {
					jesEntiys.add(createJpushEntiy(jbh, tatj));
				}
			} else {
				for (String string : jpTitleTexts) {
					TitleAndTextForJpush tatj =  JSONObject.parseObject(string, TitleAndTextForJpush.class);
					if (tatj.getLanguageId() != "") {
						jesEntiys.add(createJpushEntiy(jbh, tatj));
					}
				}
			}
		}
		boolean result = jpushForHand(jbh, jesEntiys);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(JpushEntiyController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	/** 
	 * @Title: createJpushEntiy 
	 * @Description: 单条推送实体
	 * @param jbh
	 * @param tjp
	 * @return
	 * @return: JpushEntiy
	 */
	private JpushEntiy createJpushEntiy(JpushByHand jbh,TitleAndTextForJpush tjp){
		JpushEntiy jpushEntiy= new JpushEntiy();
		jpushEntiy.setBroadCast(jbh.getBroadCast());
		jpushEntiy.setInsiderIds(jbh.getInsiderIds());
		jpushEntiy.setOutsiderIds(jbh.getOutsiderIds());
		jpushEntiy.setJpUniteId(jbh.getJpUniteId());
		if (tjp != null ) {
			jpushEntiy.setJpText(tjp.getJpText());
			jpushEntiy.setJpTitle(tjp.getJpTitle());
			jpushEntiy.setLanguageId(tjp.getLanguageId());
		}
		return jpushEntiy;
	}
	
	
	/** 
	 * @Title: jpushForHand 
	 * @Description: 消息推送
	 * @param jbh
	 * @param jesEntiys
	 * @return
	 * @throws Exception
	 * @return: boolean
	 */
	private boolean jpushForHand(JpushByHand jbh,List<JpushEntiy> jesEntiys) throws Exception{
		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String appKeyHy = bundle.getString("appKey_hy");
		String masterSecretHy = bundle.getString("masterSecret_hy");
		
		/**
		 *  广播通知推送
		 */
		if (StringUtils.isNotBlank(jbh.getBroadCast())&&jbh.getBroadCast().equals("true")) {
			Builder newBuilder = PushPayload.newBuilder();
			JPushClient jpushClient = new JPushClient(masterSecretHy, appKeyHy);
			Map<String,String> map = new HashMap<String,String>();
			/**
			 * 消息拼装
			 */
			for (JpushEntiy jpushEntiy : jesEntiys) {
				map.put(jpushEntiy.getLanguageId()+"Title", String.valueOf(jpushEntiy.getJpTitle()));
				map.put(jpushEntiy.getLanguageId()+"Text",String.valueOf(jpushEntiy.getJpText()));
				map.put("date",DateUtils.getTimeStr(new Date()));
				jpushEntiyService.save(jpushEntiy);
			}
			/**
			 * 推送
			 */
			IosNotification iosNotification = IosNotification.newBuilder().setBadge(1)
					.setSound("default").addExtras(map).build();
			AndroidNotification androidNotification = AndroidNotification.newBuilder().addExtras(map).build();
			Notification notificationBuild = Notification.newBuilder()
					.setAlert(String.valueOf("广播通知 Broadcast notification"))
					.addPlatformNotification(androidNotification)
					.addPlatformNotification(iosNotification).build();
			newBuilder.setPlatform(Platform.android_ios()).setAudience(Audience.all());
			Options options = Options.newBuilder().setApnsProduction(true).build();
			newBuilder.setOptions(options);
			newBuilder.setNotification(notificationBuild);
			PushPayload pushPayload = newBuilder.build();
			PushResult result = jpushClient.sendPush(pushPayload);
			if(result.isResultOK()){
				_LOG.info( "推送消息发送成功");
			} else {
				_LOG.error("推送消息发送失败 ");
			}
		}
		
		/**
		 * 消息推送 
		 */
		if (StringUtils.isNotBlank(jbh.getInsiderIds()) || StringUtils.isNotBlank(jbh.getOutsiderIds())) {
			List<Member> members = new ArrayList<Member>();
			DetachedCriteria detachedCriteria = null;
			String[] ss = null;
			if (StringUtils.isNotBlank(jbh.getInsiderIds()) ) {
				 detachedCriteria = DetachedCriteria.forClass(ServicePersonnel.class);
				 ss = jbh.getInsiderIds().split(",");
				 detachedCriteria.add(Restrictions.in("id", Arrays.asList(ss)));
				 detachedCriteria.add(Restrictions.eq("enable", true));
				 List<ServicePersonnel> list = servicePersonnelService.findByDetachedCriteria(detachedCriteria);
				 List<String> strings = new ArrayList<String>();
				 if (list.size() > 0) {
					for (ServicePersonnel servicePersonnel : list) {
						strings.add(servicePersonnel.getMember().getMemberId());
					}
				}
				 detachedCriteria = DetachedCriteria.forClass(Member.class);
				 detachedCriteria.add(Restrictions.in("memberId",strings));
			} else {
				detachedCriteria = DetachedCriteria.forClass(Member.class);
				ss = jbh.getOutsiderIds().split(",");
				detachedCriteria.add(Restrictions.in("memberId", Arrays.asList(ss)));
			}
			detachedCriteria.add(Restrictions.eq("enable", true));
			detachedCriteria.add(Restrictions.isNotNull("invitationCode"));
			members = iMemberService.findByDetachedCriteria(detachedCriteria);
			if (members.size() > 0) {
				for (Member member : members) {
					executor.execute(new Runnable(){
						@Override
						public void run() {
							String invitationCode = member.getInvitationCode();
							Map<String,String> map = new HashMap<String,String>();
							String jpTitle = null;
							String jpText = null;
							for (JpushEntiy jpushEntiy : jesEntiys) {
								map.put(jpushEntiy.getLanguageId()+"Title", String.valueOf(jpushEntiy.getJpTitle()));
								map.put(jpushEntiy.getLanguageId()+"Text",String.valueOf(jpushEntiy.getJpText()));
								map.put("date",DateUtils.getTimeStr(new Date()));
								if (StringUtils.isNotBlank(jbh.getOutsiderIds())) {
									if (StringUtils.isNotBlank(member.getLanguage()) && StringUtils.isNotBlank(jpushEntiy.getLanguageId())) {
										if (member.getLanguage().equals(jpushEntiy.getLanguageId())) {
											jpTitle = jpushEntiy.getJpTitle();
											jpText = jpushEntiy.getJpText();
											jpushEntiy.setJpUserId(member.getMemberId());
											jpushEntiyService.save(jpushEntiy);
										}
									}
								}
								if (StringUtils.isNotBlank(jbh.getInsiderIds())) {
									if (jpushEntiy.getLanguageId().equals("中文表示")) {
										jpushEntiy.setJpUserId(member.getMemberId());
										jpushEntiyService.save(jpushEntiy);
									}
								}
							}
							Builder newBuilder = PushPayload.newBuilder();
							JPushClient jpushClient = new JPushClient(masterSecretHy, appKeyHy);
							IosNotification iosNotification = IosNotification.newBuilder().setBadge(1).
									setSound("default").setAlert(jpTitle != null ? jpTitle : jbh.getJpTitle()).addExtras(map).build();
							AndroidNotification androidNotification = AndroidNotification.newBuilder()
									.setTitle(jpTitle != null ? jpTitle : jbh.getJpTitle()).addExtras(map).build();
							Notification notificationBuild = Notification.newBuilder().setAlert(jpText != null ? jpText : jbh.getJpText())
									.addPlatformNotification(androidNotification).addPlatformNotification(iosNotification).build();
							newBuilder.setPlatform(Platform.android_ios()).setAudience(Audience.alias(invitationCode));
							Options options = Options.newBuilder().setApnsProduction(true).build();//设置为生产模式   默认为开发模式
							newBuilder.setOptions(options);
							newBuilder.setNotification(notificationBuild);
							PushPayload pushPayload = newBuilder.build();
							PushResult result;
							try {
								result = jpushClient.sendPush(pushPayload);
								if(result.isResultOK()){
									_LOG.info(invitationCode + "推送消息发送成功");
								} else {
									_LOG.error(invitationCode + "推送消息发送失败 ");
								}
							} catch (APIConnectionException e1) {
								e1.printStackTrace();
								_LOG.error(invitationCode + " ：消息推送错误信息：  " + e1.getMessage());
							} catch (APIRequestException e1) {
								e1.printStackTrace();
								_LOG.error(invitationCode + " ：消息推送错误信息：  " + e1.getMessage());
							}
						}
						
					});
				}
			}
			
		}
		return true;
	}
	
	
	
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = jpushEntiyService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(JpushEntiyController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	@ResponseBody
	public ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = jpushEntiyService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(JpushEntiyController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public ReturnValue update(JpushEntiyForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		JpushEntiy jpushEntiy = new JpushEntiy();
		BeanUtils.copyProperties(form, jpushEntiy);
		boolean result = jpushEntiyService.updateJpushEntiy(jpushEntiy);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(JpushEntiyController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	@ResponseBody
	public JpushEntiyVO queryById(String id) throws Exception {
		JpushEntiyVO vo = new JpushEntiyVO();
		JpushEntiy jpushEntiy = jpushEntiyService.getJpushEntiyById(id);
		if (jpushEntiy != null) {
			BeanUtils.copyProperties(jpushEntiy, vo);
		}
		return vo;
	}
	@RequestMapping(value = "/languageType")
	@ResponseBody
	public List<DataDictionary>  languageTypes(String languageType) throws Exception {
		List<DataDictionary> dataDictionaries = new ArrayList<DataDictionary>();
		dataDictionaries = dataDictionaryService.findAllByTypeLogo(languageType, null);
		return dataDictionaries;
	}

}
