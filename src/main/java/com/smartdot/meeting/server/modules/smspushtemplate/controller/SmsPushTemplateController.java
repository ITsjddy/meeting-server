package com.smartdot.meeting.server.modules.smspushtemplate.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.EncodEncryUtil;
import com.smartdot.meeting.server.common.util.PhoneCodeUtil;
import com.smartdot.meeting.server.modules.member.service.IMemberService;
import com.smartdot.meeting.server.modules.servicePersonnel.service.IServicePersonnelService;
import com.smartdot.meeting.server.modules.smspushs.model.SmsPushsForm;
import com.smartdot.meeting.server.modules.smspushs.service.ISmsPushsService;
import com.smartdot.meeting.server.modules.smspushtemplate.model.SmsPushTemplateForm;
import com.smartdot.meeting.server.modules.smspushtemplate.model.SmsPushTemplateVO;
import com.smartdot.meeting.server.modules.smspushtemplate.service.ISmsPushTemplateService;
import com.smartdot.meeting.server.common.entity.Member;
import com.smartdot.meeting.server.common.entity.ServicePersonnel;
import com.smartdot.meeting.server.common.entity.SmsPushTemplate;
import com.smartdot.meeting.server.common.entity.SmsPushs;



@Controller
@RequestMapping(value = "/smsPushTemplate")
public class SmsPushTemplateController {

	private static final Logger _LOG = Logger.getLogger(SmsPushTemplateController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";

	@Resource(name = ISmsPushTemplateService.SERVICE_NAME)
	private ISmsPushTemplateService smsPushTemplateService;

	@Resource(name = ISmsPushsService.SERVICE_NAME)
	private ISmsPushsService isps;
	
	@Resource(name = IMemberService.SERVICE_NAME)
	private IMemberService iMemberService;
	
	@Resource(name = IServicePersonnelService.SERVICE_NAME)
	private IServicePersonnelService servicePersonnelService;
	
	@Resource(name = "taskExecutor")
	private TaskExecutor executor;
	

	@RequestMapping(value = "/openPage")
	public String openPage() throws Exception {
		return ISmsPushTemplateService.PAGE_MAIN_PATH;
	}

	@RequestMapping(value = "/addEdit")
	public String addAndEditPage(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return ISmsPushTemplateService.PAGE_ADDEDIT_PATH;
	}

	@RequestMapping(value = "/pushSms")
	public String pushSmsPage(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return ISmsPushTemplateService.PAGE_PUSHSMS_PATH;
	}
	
	
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("enable", true);
		String sptNo = request.getParameter("sptNo");
		if (StringUtils.isNotBlank(sptNo)) {
			searchMap.put("sptNo", sptNo);
		}
		String sptTetle = request.getParameter("sptTetle");
		if (StringUtils.isNotBlank(sptTetle)) {
			searchMap.put("sptTetle", sptTetle);
		}
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		Page<SmsPushTemplate> page = new Page<SmsPushTemplate>();
		page.setCurrentPage(Integer.parseInt(StringUtils.isNotBlank(pageNow) ? pageNow
				: "1"));
		page.setPageSize(Integer.parseInt(StringUtils.isNotBlank(pageSize) ? pageSize
				: "15"));
		return smsPushTemplateService
				.findSmsPushTemplateByPage(page, searchMap);
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public ReturnValue save(SmsPushTemplateForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		SmsPushTemplate smsPushTemplate = new SmsPushTemplate();
		BeanUtils.copyProperties(form, smsPushTemplate);
		boolean result;
		if (StringUtils.isEmpty(form.getId())) {
			result = smsPushTemplateService.save(smsPushTemplate);
		} else {
			result = smsPushTemplateService
					.updateSmsPushTemplate(smsPushTemplate);
		}
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}


	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = smsPushTemplateService.deleteById(id);
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteList")
	@ResponseBody
	public ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = smsPushTemplateService.deleteAll(Arrays.asList(ids));
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public ReturnValue update(SmsPushTemplateForm form) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		SmsPushTemplate smsPushTemplate = new SmsPushTemplate();
		BeanUtils.copyProperties(form, smsPushTemplate);
		boolean result = smsPushTemplateService
				.updateSmsPushTemplate(smsPushTemplate);
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	@ResponseBody
	public SmsPushTemplateVO queryById(String id) throws Exception {
		SmsPushTemplateVO vo = new SmsPushTemplateVO();
		SmsPushTemplate smsPushTemplate = smsPushTemplateService
				.getSmsPushTemplateById(id);
		if (smsPushTemplate != null) {
			BeanUtils.copyProperties(smsPushTemplate, vo);
		}
		return vo;
	}
	
	@RequestMapping(value = "/smsPush")
	@ResponseBody
	public ReturnValue smsPush(SmsPushsForm spf) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = false;
		spf.setSmspBf(true);
		SmsPushs smsPushs = new SmsPushs();
		BeanUtils.copyProperties(spf, smsPushs);
		/**
		 * 手机号码方式直接推
		 */
		if (StringUtils.isNotBlank(spf.getSmsphoneNums())) {
			result = sendPhones(smsPushs);
		}
		/**
		 * 条件查询List<?> ---- 拼装手机号 ---- 推送
		 * 
		 */
		if (StringUtils.isNotBlank(spf.getSmspGuests())) {
			sendForAppUserAndService("Service", smsPushs, Arrays.asList(spf.getSmspGuests().split(",")));
		}
		if (StringUtils.isNotBlank(spf.getSmspStbers())) {
			DetachedCriteria detachedCriteria = null;
			String[] ss = null;
			detachedCriteria = DetachedCriteria
					.forClass(ServicePersonnel.class);
			ss = spf.getSmspStbers().split(",");
			detachedCriteria.add(Restrictions.in("id", Arrays.asList(ss)));
			detachedCriteria.add(Restrictions.eq("enable", true));
			List<ServicePersonnel> list = servicePersonnelService
					.findByDetachedCriteria(detachedCriteria);
			List<String> strings = new ArrayList<String>();
			if (list.size() > 0) {
				for (ServicePersonnel servicePersonnel : list) {
					strings.add(servicePersonnel.getMember().getMemberId());
				}
			}
			sendForAppUserAndService("AppUser", smsPushs, strings);
		}
		result = isps.save(smsPushs);
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(SmsPushTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	/** 
	 * @Title: sendForAppUserAndService 
	 * @param type 人员类型
	 * @param smsPushs 实体
	 * @param strings  id集合
	 * @return: void
	 */
	private void sendForAppUserAndService(String type, SmsPushs smsPushs,List<String> strings){
		/*ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String deskey = bundle.getString("des_key");*/
		List<Member> members = new ArrayList<Member>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Member.class);
		detachedCriteria.add(Restrictions.in("memberId", strings));
		detachedCriteria.add(Restrictions.eq("enable", true));
		detachedCriteria.add(Restrictions.isNotNull("mobile"));
		members = iMemberService.findByDetachedCriteria(detachedCriteria);
		if (members.size() < 101) {
			String mobiles = "";
			for (Member member : members) {
				String mobile = member.getMobile();/*EncodEncryUtil.desDecode(deskey, member.getMobile()); 暂时不解密*/
				if (mobile.length() == 11) {
					mobiles = mobiles + "," + mobile;
				}
			}
			if (StringUtils.isNotBlank(mobiles)) {
				boolean tsj = PhoneCodeUtil.sendVerificationCode(
						smsPushs.getSmspText(), mobiles,
						smsPushs.getSmstId(), "1");
				if (!tsj) {
					_LOG.error("--嘉宾手机号- (" + mobiles + ") -发送失败--");
				}
			}
		} else {
			for (Member member : members) {
				String mobile = member.getMobile();
				if (StringUtils.isNotBlank(mobile) && mobile.length() == 11) {
					executor.execute(new Runnable() {
						@Override
						public void run() {
							runThread(mobile, smsPushs.getSmspText(),
									smsPushs.getSmstId());
						}
					});
				}
			}
		}
	}
	
	private void runThread(String mobile, String smspText, String smstId) {
		HttpURLConnection conn = null;
		OutputStream os = null;
		InputStream is = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String date = format.format(new Date()); // 当前时间
			String url = "";
			ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
			String YTX_URL = bundle.getString("YTX_URL");//
			String YTX_GESHI = bundle.getString("YTX_SOMESS_GESHI");//
			String YTX_ACCOUNT_SID = bundle.getString("YTX_ACCOUNT_SID");//
			String YTX_AUTH_TOKEN = bundle.getString("YTX_AUTH_TOKEN");//
			String YTX_APPLICATION_ID = bundle.getString("YTX_APPLICATION_ID");//
			String YTX_CONTENT_LENGTH = bundle.getString("YTX_CONTENT_LENGTH");//
			String code_fatime = bundle.getString("code_fatime");//
			String sig = YTX_ACCOUNT_SID + YTX_AUTH_TOKEN + date;
			sig = EncodEncryUtil.string2MD5(sig).toUpperCase();
			YTX_GESHI = YTX_GESHI.replace("{accountSid}", YTX_ACCOUNT_SID)
					.replace("{SigParameter}", sig);
			url += YTX_URL + YTX_GESHI;
			String Authorization = EncodEncryUtil
					.base64CodeData(YTX_ACCOUNT_SID + ":" + date);
			URL wsUrl = new URL(url);
			conn = (HttpURLConnection) wsUrl.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/xml");
			conn.addRequestProperty("Content-Type",
					"application/xml;charset=utf-8");
			conn.addRequestProperty("Content-Length", YTX_CONTENT_LENGTH);
			conn.addRequestProperty("Authorization", Authorization);
			os = conn.getOutputStream();
			String soap = "";
			if (StringUtils.isNotBlank(smspText)) {
				soap = "<?xml version='1.0' encoding='utf-8'?> "
						+ "<TemplateSMS> " + "<to>" + mobile + "</to> "
						+ "<appId>" + YTX_APPLICATION_ID + "</appId> "
						+ "<templateId>" + smstId + "</templateId>" + "<datas>"
						+ "<data>" + smspText + "</data>" + "<data>"
						+ code_fatime + "</data>" + "</datas>"
						+ "</TemplateSMS>";
			} else {
				soap = "<?xml version='1.0' encoding='utf-8'?> "
						+ "<TemplateSMS> " + "<to>" + mobile + "</to> "
						+ "<appId>" + YTX_APPLICATION_ID + "</appId> "
						+ "<templateId>" + smstId + "</templateId>"
						+ "</TemplateSMS>";
			}
			os.write(soap.getBytes("utf-8"));
			is = conn.getInputStream();
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(is);
			Element root = document.getRootElement();
			Element statusMsg = root.element("statusMsg");
			if (null != statusMsg) {
				_LOG.info("----嘉宾手机号为：(" + mobile + ") -发送失败--");
			}
		} catch (Exception e) {
			_LOG.info("----嘉宾手机号为：(" + mobile + ") 短信接口返回错误结果："
					+ e.getMessage());
		} finally {
			try {
				is.close();
				os.close();
				conn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean sendPhones(SmsPushs smsPushs) {
		String[] phoness = smsPushs.getSmsphoneNums().split(",");
		if (phoness.length < 101) {
			return  PhoneCodeUtil.sendVerificationCode(smsPushs.getSmspText(),
						smsPushs.getSmsphoneNums(), smsPushs.getSmstId(), "1");
		} else {
			for (String string : phoness) {
				executor.execute(new Runnable() {
					@Override
					public void run() {
						runThread(string, smsPushs.getSmspText(), smsPushs.getSmstId());
					}
				});
			}
			return true;
		}
	}
	
}
