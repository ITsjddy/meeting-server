package com.smartdot.meeting.server.modules.template.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
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

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.smartdot.meeting.server.common.entity.ImportTemplate;
import com.smartdot.meeting.server.common.entity.Stroke;
import com.smartdot.meeting.server.common.entity.Template;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.CommonUtil;
import com.smartdot.meeting.server.common.util.ImportExcelUtils;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.cloudgroup.controller.CloudGroupController;
import com.smartdot.meeting.server.modules.importtemplate.dao.IImportTemplateDao;
import com.smartdot.meeting.server.modules.importtemplate.service.IImportTemplateService;
import com.smartdot.meeting.server.modules.stroke.dao.IStrokeDao;
import com.smartdot.meeting.server.modules.stroke.service.IStrokeService;
import com.smartdot.meeting.server.modules.template.model.TemplateForm;
import com.smartdot.meeting.server.modules.template.model.TemplatePageForm;
import com.smartdot.meeting.server.modules.template.model.TemplateVO;
import com.smartdot.meeting.server.modules.template.service.ITemplateService;


/** 
 * @ClassName: TravelInforTemplateController 
 * @Description: 行程信息导入
 * @author: 郝满堂
 * @date: 2017年3月15日 上午9:58:14  
 */
@Controller
@RequestMapping(value = "/travelInforTemplate")
public class TravelInforTemplateController {
	
	@SuppressWarnings("unused")
	private static final Logger _LOG = Logger.getLogger(TravelInforTemplateController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ITemplateService.SERVICE_NAME)
	private ITemplateService templateService;
	
	@Resource(name = IStrokeService.SERVICE_NAME)
	private IStrokeService strokeService;
	
	@Resource(name = IStrokeDao.DAO_NAME)
	private IStrokeDao strokeDao;
	
	@Resource(name = IImportTemplateDao.DAO_NAME)
	private IImportTemplateDao importTemplateDao;
	
	@Resource(name = IImportTemplateService.SERVICE_NAME)
	private IImportTemplateService importTemplateService;
	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		return "template/travelInforList";
	}
	
	
	@RequestMapping(value = "/operate")
	public String operate(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "template/travelInforOperate";
	}
	
	@RequestMapping(value = "/createindex")
	public String createindex() throws Exception {
		return "template/travelInforCreateList";
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/pageQuery")
	@ResponseBody
	public Map<String, Object> pageQuery(HttpServletRequest request, TemplatePageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Template.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		dc.add(Restrictions.ne("fieldName", "importLogo"));
		if (req != null) {
			if (StringUtils.isNotBlank(req.getTemplateName())) {
				dc.add(Restrictions.eq("templateName", req.getTemplateName()));
			}
			if (StringUtils.isNotBlank(req.getFieldName())) {
				dc.add(Restrictions.eq("fieldName", req.getFieldName()));
			}
			if (StringUtils.isNotBlank(req.getExplain())) {
				dc.add(Restrictions.in("explain", req.getExplain()));
			}
		}
		dc.add(Restrictions.eq("tableName", CommonUtil.getTableName(Stroke.class)));
		return templateService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
	
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue = new ReturnValue();
		boolean result = templateService.deleteById(id);
		if (result) {
			returnValue.setSuccess(true);
			returnValue.setMessage(DeptTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/queryById")
	@ResponseBody
	public TemplateVO queryById(String id) throws Exception {
		TemplateVO vo = new TemplateVO();
		Template template = templateService.getTemplateById(id);
		if (template != null) {
			BeanUtils.copyProperties(template, vo);
		}
		return vo;
	}
	
	@RequestMapping(value = "/queryList")
	@ResponseBody
	public List<Template> queryList() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Template.class);
	    dc.add(Restrictions.eq("enable", true));
	    dc.add(Restrictions.isNotNull("fieldName"));
	    dc.add(Restrictions.ne("fieldName", "importLogo"));
	    dc.add(Restrictions.ne("fieldName",""));
	    dc.add(Restrictions.isNotNull("tableName"));
	    dc.add(Restrictions.eq("tableName", CommonUtil.getTableName(Stroke.class)));
	    List<Template> lmemberTemplate = templateService.findByDetachedCriteria(dc);
		return lmemberTemplate;
	}
	
	@RequestMapping(value = "/saveEdit")
	@ResponseBody
	public ReturnValue saveEdit(TemplateForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		Template template = new Template();
		BeanUtils.copyProperties(form, template);
		template.setTableName(CommonUtil.getTableName(Stroke.class));
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = templateService.save(template);
		} else{
			result = templateService.updateTemplate(template);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DeptTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}
	
	
	@RequestMapping(value = "/queryfields")
	@ResponseBody
	public  List<String> queryfields() throws Exception {
		List<String> xfields = new ArrayList<String>();
		Stroke info = new Stroke();
		Field[] fields = info.getClass().getDeclaredFields();
		String tableName = CommonUtil.getTableName(Stroke.class);
		DetachedCriteria dc = DetachedCriteria.forClass(Template.class);
	    dc.add(Restrictions.eq("enable", true));
	    dc.add(Restrictions.isNotNull("fieldName"));
	    dc.add(Restrictions.ne("fieldName",""));
	    dc.add(Restrictions.isNotNull("tableName"));
	    dc.add(Restrictions.eq("tableName", tableName));
	    List<Template> lmemberTemplate = templateService.findByDetachedCriteria(dc);
		List<String> fieldNames=null;
	    if(null!=lmemberTemplate&&lmemberTemplate.size()>0){
	    	fieldNames=new ArrayList<String>();
	    	for (Template memberTemplate : lmemberTemplate) {
	    		fieldNames.add(memberTemplate.getFieldName());
			}
	    }
	    if(null!=fieldNames&&fieldNames.size()>0){
	    	for (Field field : fields) {
	    		if (!fieldNames.contains(field.getName())) {
	    			xfields.add(field.getName());
				}
			}
	    }else{
	    	for (Field field : fields) {
	    		xfields.add(field.getName());
			}
	    }
		return xfields;
	}
	
	
	private void updateImportTable(String[] templateNameList){
		Class<?> entity = Stroke.class;
		//下载时保存最新模板
		DetachedCriteria detachedCriteriat = DetachedCriteria.forClass(Template.class);
		detachedCriteriat.add(Restrictions.eq("enable", true));
		detachedCriteriat.add(Restrictions.or(Restrictions.eq("tableName", CommonUtil.getTableName(entity))));
		List<Template> templatetemp = templateService.findByDetachedCriteria(detachedCriteriat);
		String str = "";  
		if(null!=templateNameList&&templateNameList.length>0&&null!=templatetemp&&templatetemp.size()>0){
			for (int j = 0; j < templateNameList.length; j++) {
				for (int i = 0; i < templatetemp.size(); i++) {
					Template memtem = templatetemp.get(i);
					if (templateNameList[j].equals(memtem.getTemplateName())) {
						str += memtem.getFieldName() + ",";
						break;
					}
				}
				
			}
			 str = str.substring(0, str.length() - 1); 
		}
		DetachedCriteria detachedCriteriate = DetachedCriteria.forClass(ImportTemplate.class);
		detachedCriteriate.add(Restrictions.eq("enable", true));
		detachedCriteriate.add(Restrictions.eq("tableName", CommonUtil.getTableName(entity)));
		List<ImportTemplate> templatetempt = importTemplateDao.findByDetachedCriteria(detachedCriteriate);
		if(null!=templatetempt&&templatetempt.size()>0){
			ImportTemplate impt = templatetempt.get(0);
			impt.setTemids(str);
			importTemplateService.save(impt);
		}else{
			ImportTemplate impt = new ImportTemplate();
			impt.setTableName(CommonUtil.getTableName(entity));
			impt.setTemids(str);
			impt.setExplain("行程信息导入模版表");
			impt.setUniqueIden("invitationCode");
			importTemplateService.save(impt);
		}

	}
	@RequestMapping(value = "/createlist")
	@ResponseBody
	public ReturnValue createlist(HttpServletRequest request,HttpServletResponse response,String[] templateNameList) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String modelName = "行程信息导入模版表";
		updateImportTable(templateNameList);
		//生成临时文件
		//获取上传文件主目录
		String upFolder="upload/member/";
        String filePathtr = request.getSession().getServletContext().getRealPath("/") + upFolder;
		File fdir = new File(filePathtr);
		if (!fdir.exists())
			fdir.mkdir();
		File result = null;
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyyMMddHHmmss");
		result = new File(fdir.getAbsoluteFile() + "/" + modelName + sdf0.format(new Date()) + ".xls");
		if (!result.exists()) {
			result.createNewFile();
		}
		WritableWorkbook book = Workbook.createWorkbook(result);
		String sheetName = "行程信息导入明细";
		WritableSheet sheet = book.createSheet(sheetName, 1);
		
		WritableFont wf = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD,false);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setBackground(Colour.GRAY_25);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		//导入字段列表
		List<String> templateNames = Arrays.asList(templateNameList);
		if (null != templateNames && templateNames.size() > 0) {
			for (int i = 0; i < templateNames.size(); i++) {
				String memtem = templateNames.get(i);
				if(null!=memtem&&StringUtils.isNotBlank(memtem)){
					//设置列
					sheet.setColumnView(i, 20);
					//设置表头
					Label label0 = new Label(i,0,memtem,wcf);
					sheet.addCell(label0);
				}
			}
		}
		WritableCellFormat wc = new WritableCellFormat();
		wc.setAlignment(Alignment.CENTRE);

		//写入数据并关闭文件
		book.write();
		book.close();
		downLoad(result.getAbsolutePath(), response, false);
		returnValue.setSuccess(true);
		returnValue.setMessage(CloudGroupController.SUCCESS_MESSAGE);
		return returnValue;
	}
	
	public void downLoad(String filePath, HttpServletResponse response, boolean isOnLine){
		BufferedInputStream br = null;
		OutputStream out = null;
        try {
			File file = new File(filePath);
			if (!file.exists()) {
				response.sendError(404, "文件没找到!");
				return;
			}
			br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024];
			int len = 0;
			response.reset(); // 非常重要
			String fileName = new String(file.getName().getBytes("GBK"),"ISO8859_1");
			if (isOnLine) { // 在线打开方式
				URL u = new URL("file:///" + filePath);
				response.setContentType(u.openConnection().getContentType());
				response.setHeader("Content-Disposition", "inline; filename="+fileName);
				// 文件名应该编码成UTF-8
			} else { // 纯下载方式
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition","attachment; filename=" + fileName);
			}
			out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
	
	@RequestMapping(value = "/importExcelDept")
	@ResponseBody
	public Map<String, Object> importExcelDept(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String messs = "";
		//获取上传文件
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("logo");
		//获取上传文件主目录
        ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String upFolder = bundle.getString("upload_folder");//
		if (StringUtils.isBlank(upFolder)) {
			upFolder = "upload/";
		} else {
			upFolder = upFolder + "/";
		}
        String filePathtr = request.getSession().getServletContext().getRealPath("/") + upFolder;
        String fileUrl="";
		String FileName=file.getOriginalFilename();
		if(StringUtils.isNotBlank(FileName)){
			try {
				 String filePath = request.getSession().getServletContext().getRealPath("/") + upFolder+ file.getOriginalFilename();  
	             //转存文件  
	             file.transferTo(new File(filePath));  
			} catch (Exception e) {
			}
			File uploadDest = new File(filePathtr);  
	        String[] fileNames = uploadDest.list();  
	        for (int i = 0; i < fileNames.length; i++) {  
	            //打印出文件名  
	           if(fileNames[i].equals(FileName)){
	        	   fileUrl=filePathtr+fileNames[i];
	           };  
	        }
	        try {
	        	messs  = importDeptMess(fileUrl);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		resultMap.put("info", messs);
		return resultMap;
	}
	
	
	/**
	 * 获取模版导入字段
	 * @param MemimtemUniIden
	 * @throws IOException
	 */
	public Map<String,Object> MemimtemMap(Class<?> entity){
		ImportTemplate memtem = null;
		DetachedCriteria meimmodetach = DetachedCriteria.forClass(ImportTemplate.class);
		meimmodetach.add(Restrictions.eq("enable", true));
		meimmodetach.add(Restrictions.eq("tableName", CommonUtil.getTableName(entity)));
		meimmodetach.addOrder(Order.desc("id"));
		List<ImportTemplate> meimmoall = importTemplateDao
				.findByDetachedCriteria(meimmodetach);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != meimmoall && meimmoall.size() > 0) {
			memtem = meimmoall.get(0);
			String temids = memtem.getTemids();
			if (StringUtils.isNotBlank(temids)) {
				String[] temidss = temids.split(",");
				DetachedCriteria memodetach=DetachedCriteria.forClass(Template.class);
				memodetach.add(Restrictions.eq("enable", true));
				memodetach.add(Restrictions.eq("tableName", CommonUtil.getTableName(entity)));
				memodetach.add(Restrictions.in("fieldName", Arrays.asList(temidss)));
				List<Template> memoall = templateService.findByDetachedCriteria(memodetach);
				if (null != memoall && memoall.size() > 0) {
					for (Template memo : memoall) {
						String fiename = memo.getFieldName();
						String temname = memo.getTemplateName();
						if (StringUtils.isNotBlank(fiename)
								&& StringUtils.isNotBlank(temname)) {
							map.put(temname, fiename);
						}
					}
				}
			}
			return map;
		}
		return null;
	}
	
	public String importDeptMess(String uploadPath) throws IOException{
		String messstr = "";
		StringBuffer errsbu = new StringBuffer();
		Map<String,Object> tripmap = MemimtemMap(Stroke.class);//导入字段
		List<Map<String, Object>> dataList  = ImportExcelUtils.readExcel(new File(uploadPath), tripmap);
		int h = 0;
		boolean isright = true;
		try {
			for (int i = 0; i < dataList.size(); i++) {
				Map<String,Object> mapItem = dataList.get(i);
				//判断数据是否都为空
				if (null != mapItem && !mapItem.isEmpty()) {
					Stroke stroke = new Stroke();
					String memberId = String.valueOf(mapItem.get("memberId")).trim();//memberId
					String importLogo = String.valueOf(mapItem.get("importLogo")).trim();//memberId
					String startStrokedate = String.valueOf(mapItem.get("startStrokedate")).trim();//行程开始日期
					String startStroketime = String.valueOf(mapItem.get("startStroketime")).trim();//行程时间
					String stopStrokedate = String.valueOf(mapItem.get("stopStrokedate")).trim();//行程结束日期
					String stopStroketime = String.valueOf(mapItem.get("stopStroketime"));//行程时间
					String title = String.valueOf(mapItem.get("title"));//标题
					String location = String.valueOf(mapItem.get("location"));//地点
					String remarks = String.valueOf(mapItem.get("remarks"));//备注
					String language = String.valueOf(mapItem.get("language"));//语言标识 (数据字典)
					String uuid = String.valueOf(mapItem.get("uuid"));//语言唯一标识 (数据字典)
					if((StringUtils.isNotBlank(importLogo)&&!"null".equals(importLogo))){
						//memberId
						if((StringUtils.isNotBlank(importLogo)&&!"null".equals(importLogo))){
							stroke.setImportLogo(importLogo);
						}
						//memberId
						if((StringUtils.isNotBlank(memberId)&&!"null".equals(memberId))){
							stroke.setMemberId(memberId);
						}
						//行程开始日期
						if((StringUtils.isNotBlank(startStrokedate)&&!"null".equals(startStrokedate))){
							stroke.setStartStrokedate(startStrokedate);
						}
						//行程时间
						if((StringUtils.isNotBlank(startStroketime)&&!"null".equals(startStroketime))){
							stroke.setStartStroketime(startStroketime);
						}
						//行程结束日期
						if((StringUtils.isNotBlank(stopStrokedate)&&!"null".equals(stopStrokedate))){
							stroke.setStopStrokedate(stopStrokedate);
						}
						//行程时间
						if((StringUtils.isNotBlank(stopStroketime)&&!"null".equals(stopStroketime))){
							stroke.setStopStroketime(stopStroketime);
						}
						//标题
						if((StringUtils.isNotBlank(title)&&!"null".equals(title))){
							stroke.setTitle(title);
						}
						//地点
						if((StringUtils.isNotBlank(location)&&!"null".equals(location))){
							stroke.setLocation(location);
						}
						//备注
						if((StringUtils.isNotBlank(remarks)&&!"null".equals(remarks))){
							stroke.setRemarks(remarks);
						}
						//语言标识 (数据字典)
						if((StringUtils.isNotBlank(language)&&!"null".equals(language))){
							stroke.setLanguage(language);
						}
						//语言唯一标识 (数据字典)
						if((StringUtils.isNotBlank(uuid)&&!"null".equals(uuid))){
							stroke.setUuid(uuid);
						}
						if(isright == true){
							DetachedCriteria dc = DetachedCriteria.forClass(Stroke.class);
							dc.add(Restrictions.eq("enable", true));
							dc.add(Restrictions.eq("importLogo", importLogo));
							List<Stroke> strokes = strokeDao.findByDetachedCriteria(dc);
							if (stroke !=null &&strokes.size() == 1) {
								for (Stroke olStroke : strokes) {
									strokeDao.delete(olStroke);
									strokeService.save(stroke);
								}
							} else {
								strokeService.save(stroke);
							}
							h++;
						}
					}else{
						errsbu.append("第"+(h+2)+"行导入失败：导入数据唯一标识为空;<br>");
					}
				}else{
					errsbu.append("第"+(h+2)+"行导入失败，原因Excel格式或数据有问题！<br>");
				}
			}
		} catch (Exception e) {
			errsbu.append("停止的行数： 第"+(h+2)+"行，原因Excel格式或数据有问题！");
			errsbu.append("异常信息："+e.getMessage()+"<br>");
		}
		if(dataList != null){
			messstr = "excel总数量："+dataList.size()+"，导入成功数量："+h+"<br>"+errsbu.toString();
		}else{
			messstr = errsbu.toString();
		}
		return messstr;
	
	}

}
