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
import org.springframework.beans.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.CommonUtil;
import com.smartdot.meeting.server.common.util.ImportExcelUtils;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.department.dao.IDepartmentDao;
import com.smartdot.meeting.server.modules.department.service.IDepartmentService;
import com.smartdot.meeting.server.modules.importtemplate.dao.IImportTemplateDao;
import com.smartdot.meeting.server.modules.importtemplate.service.IImportTemplateService;
import com.smartdot.meeting.server.modules.template.dao.ITemplateDao;
import com.smartdot.meeting.server.modules.template.model.TemplateForm;
import com.smartdot.meeting.server.modules.template.model.TemplatePageForm;
import com.smartdot.meeting.server.modules.template.model.TemplateVO;
import com.smartdot.meeting.server.modules.template.service.ITemplateService;
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
import com.smartdot.meeting.server.common.entity.Department;
import com.smartdot.meeting.server.common.entity.ImportTemplate;
import com.smartdot.meeting.server.common.entity.Template;


@Controller
@RequestMapping(value = "/depttemplate")
public class DeptTemplateController {

	private static final Logger _LOG = Logger.getLogger(DeptTemplateController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = ITemplateService.SERVICE_NAME)
	private ITemplateService templateService;
	
	@Resource(name = ITemplateDao.DAO_NAME)
	private ITemplateDao templateDao;
	
	@Resource(name = IDepartmentDao.DAO_NAME)
	private IDepartmentDao departmentDao;
	
	@Resource(name = IImportTemplateDao.DAO_NAME)
	private IImportTemplateDao importTemplateDao;
	
	@Resource(name = IDepartmentService.SERVICE_NAME)
	private IDepartmentService departmentService;
	
	@Resource(name = IImportTemplateService.SERVICE_NAME)
	private IImportTemplateService importTemplateService;
	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "template/deptlist";
	}
	@RequestMapping(value = "/deptimportlist")
	public String deptimportlist() throws Exception {
		
		return "template/deptimportlist";
	}
	@RequestMapping(value = "/createindex")
	public String createindex() throws Exception {
		
		return "template/createlist";
	}
	@RequestMapping(value = "/createlist")
	public void createlist(HttpServletRequest request,HttpServletResponse response,String[] selectname) throws Exception {
		Class<?> entity=Department.class;
		//下载时保存最新模板
		DetachedCriteria detachedCriteriat = DetachedCriteria.forClass(Template.class);
		detachedCriteriat.add(Restrictions.eq("enable", true));
		detachedCriteriat.add(Restrictions.eq("tableName", CommonUtil.getTableName(entity)));
		List<Template> templatetemp = templateDao.findByDetachedCriteria(detachedCriteriat);
		String str = "";  
		if(null!=templatetemp&&templatetemp.size()>0){
			for (int i = 0; i < templatetemp.size(); i++) {
				Template memtem = templatetemp.get(i);
				str += memtem.getFieldName() + ",";
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
			impt.setExplain("团导入模板");
			impt.setUniqueIden("invitationCode");
			importTemplateService.save(impt);
		}
		String modelName = "团信息导入模版表";
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
		String sheetName = "团信息";
		
		
		WritableSheet sheet = book.createSheet(sheetName, 1);
		
		WritableFont wf = new WritableFont(WritableFont.ARIAL,11,WritableFont.BOLD,false);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setBackground(Colour.GRAY_25);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		//导入字段列表
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Template.class);
//		detachedCriteria.add(Restrictions.eq("enable", true));
//		detachedCriteria.add(Restrictions.eq("tableName", CommonUtil.getTableName(entity)));
//		List<Template> memtemall = templateDao.findByDetachedCriteria(detachedCriteria);
		if(null!=selectname&&selectname.length>0){
			for (int i = 0; i < selectname.length; i++) {
					sheet.setColumnView(i, 20);
					//设置表头
					Label label0 = new Label(i,0,selectname[i],wcf);
					sheet.addCell(label0);
			}
		}
		WritableCellFormat wc = new WritableCellFormat();
		wc.setAlignment(Alignment.CENTRE);

		//写入数据并关闭文件
		book.write();
		book.close();
		downLoad(result.getAbsolutePath(), response, false);
		return;
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
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "template/deptadd";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		model.addAttribute("id", id);
		return "template/deptadd";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "template/deptdetails";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<TemplateVO> queryAll() throws Exception {
		List<Template> templates = templateService.findAll();
		List<TemplateVO> vo = new ArrayList<TemplateVO>();
		TemplateVO templateVO = null;
		for (Template template : templates) {
			templateVO  = new TemplateVO();
			BeanUtils.copyProperties(template, templateVO);
			vo.add(templateVO);
		}
		return vo;
	}
	
	@RequestMapping(value = "/queryAllIds")
	public @ResponseBody Map<String, Object> queryAllIds() throws Exception {
		Map<String, Object> map = new HashMap<>();
		ArrayList<String> strArrayid = new ArrayList<String> ();
		ArrayList<String> strArrayname = new ArrayList<String> ();
		String tableName = CommonUtil.getTableName(Department.class);
		DetachedCriteria dc = DetachedCriteria.forClass(Template.class);
	    dc.add(Restrictions.eq("enable", true));
	    dc.add(Restrictions.eq("tableName", tableName));
	    dc.add(Restrictions.eq("isMust", "1"));
	    List<Template> lmemberTemplate = templateDao.findByDetachedCriteria(dc);
	    if(null!=lmemberTemplate&&lmemberTemplate.size()>0){
	    	for (Template memberTemplate : lmemberTemplate) {
	    		strArrayid.add(memberTemplate.getId());
	    		strArrayname.add(memberTemplate.getTemplateName());
	    	}
	    }
	    map.put("strArrayid", strArrayid);
	    map.put("strArrayname", strArrayname);
		return map;
	}
	
	@RequestMapping(value = "/queryfields")
	public @ResponseBody
	 List<String> queryfields() throws Exception {
		List<String>  xfields = new ArrayList<String>();
		Department info=new Department();
		Field[] fields =  info.getClass().getDeclaredFields();
		String tableName = CommonUtil.getTableName(Department.class);
		DetachedCriteria dc = DetachedCriteria.forClass(Template.class);
	    dc.add(Restrictions.eq("enable", true));
	    dc.add(Restrictions.isNotNull("fieldName"));
	    dc.add(Restrictions.ne("fieldName",""));
	    dc.add(Restrictions.isNotNull("tableName"));
	    dc.add(Restrictions.eq("tableName", tableName));
	    List<Template> lmemberTemplate = templateDao.findByDetachedCriteria(dc);
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
	@RequestMapping(value = "/importExcelDept")
	public @ResponseBody
	Map<String, Object> importExcelDept(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String messs = "";
		//获取上传文件
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("logo");
		//获取上传文件主目录
        ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String upFolder = bundle.getString("upload_folder");//
		if(StringUtils.isBlank(upFolder)){
			upFolder="upload/";
		}else{
			upFolder=upFolder+"/";
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
	public String importDeptMess(String uploadPath) throws IOException{

		String messstr = "";
		StringBuffer errsbu=new StringBuffer();
		Map<String,Object> tripmap= MemimtemMap(Department.class);//导入字段
		List<Map<String, Object>> dataList  = ImportExcelUtils.readExcel(new File(uploadPath), tripmap);
		int h=0;
		boolean isright = true;
		try {
			for (int i=0;i<dataList.size();i++) {
				Map<String,Object> mapItem = dataList.get(i);
				//判断数据是否都为空
				if(null!=mapItem&&!mapItem.isEmpty()){
					Department info = new Department();
					String departname = String.valueOf(mapItem.get("departname")).trim();//团名称
					String pName = String.valueOf(mapItem.get("pName")).trim();//上级团名称
					String classificationName = String.valueOf(mapItem.get("classificationName")).trim();//团分类名称(例如：内宾、外宾) 
					String typeName = String.valueOf(mapItem.get("typeName")).trim();//团类型名称 (例如：部长团、省市团) 
					//String fullpath= String.valueOf(mapItem.get("fullpath"));//说明信息
					
					if((StringUtils.isNotBlank(departname)&&!"null".equals(departname))){
						//校验是否修改;	
						DetachedCriteria dc= DetachedCriteria.forClass(Department.class);
						dc.add(Restrictions.eq("enable", true));
						dc.add(Restrictions.eq("departname", departname));
						List<Department> memberList = departmentDao.findByDetachedCriteria(dc);
						if(memberList!=null && memberList.size()>=1){
							info = memberList.get(0);
						}else{
							info.setDepartname(departname);
						}
						//上级团
						if((StringUtils.isNotBlank(pName)&&!"null".equals(pName))){
							DetachedCriteria dc2= DetachedCriteria.forClass(Department.class);
							dc2.add(Restrictions.eq("enable", true));
							dc2.add(Restrictions.eq("departname", pName));
							List<Department> memberList2 = departmentDao.findByDetachedCriteria(dc2);
							if(memberList2!=null && memberList2.size()>0){
								info.setParentid(memberList2.get(0).getId().toString());
							}else{
								errsbu.append("第"+(h+2)+"行 上级团名称没有找到!:"+pName+"<br>");
								isright=false;
							}			
						}else{
							info.setParentid("0");
						}
						//团分类
						if((StringUtils.isNotBlank(classificationName)&&!"null".equals(classificationName))){
//							DataDictionary dataDictionary = dataDictionary("tuanClassification",classificationName);
//							if(null != dataDictionary){
//								info.setClassification(dataDictionary.getUneIdent());
//							}else{
//								errsbu.append("第"+(h+2)+"行 团分类名称没有找到!:"+classificationName+"<br>");
//							}
						}else{
							info.setClassification(null);
						}
						//团类型
						if((StringUtils.isNotBlank(typeName)&&!"null".equals(typeName))){
//							DataDictionary dataDictionary = dataDictionary("tuanType",typeName);
//							if(null != dataDictionary){
//								info.setType(dataDictionary.getUneIdent());
//							}else{
//								errsbu.append("第"+(h+2)+"行 团类型名称没有找到!:"+typeName+"<br>");
//							}
						}else{
							info.setType(null);
						}
						if(isright==true){
							//departmentService.save(info);
							h++;
						}
					}else{
						errsbu.append("第"+(h+2)+"行导入失败：团名字为空;<br>");
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
	/**
	 * 获取模版导入字段
	 * @param MemimtemUniIden
	 * @throws IOException
	 */
	public Map<String,Object> MemimtemMap(Class<?> entity){
		ImportTemplate memtem=null;
		DetachedCriteria meimmodetach=DetachedCriteria.forClass(ImportTemplate.class);
		meimmodetach.add(Restrictions.eq("enable", true));
		meimmodetach.add(Restrictions.eq("tableName", CommonUtil.getTableName(entity)));
		meimmodetach.addOrder(Order.desc("id"));
		List<ImportTemplate> meimmoall = importTemplateDao.findByDetachedCriteria(meimmodetach);
		Map<String,Object> map=new HashMap<String, Object>();
		if(null!=meimmoall&&meimmoall.size()>0){
			memtem=meimmoall.get(0);
			String temids=memtem.getTemids();
			if(StringUtils.isNotBlank(temids)){
				String[] temidss=temids.split(",");
				DetachedCriteria memodetach=DetachedCriteria.forClass(Template.class);
				memodetach.add(Restrictions.eq("enable", true));
				memodetach.add(Restrictions.eq("tableName", CommonUtil.getTableName(entity)));
				memodetach.add(Restrictions.in("fieldName", temidss));
				List<Template> memoall = templateDao.findByDetachedCriteria(memodetach);
				if(null!=memoall&&memoall.size()>0){
					for (Template memo:memoall) {
						String fiename=memo.getFieldName();
						String temname=memo.getTemplateName();
						if(StringUtils.isNotBlank(fiename)&&StringUtils.isNotBlank(temname)){
							map.put(temname, fiename);
						}
					}
				}
			}
			return map;
		}
		return null;
	}
	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(TemplateForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		Template template= new Template();
		BeanUtils.copyProperties(form, template);
		template.setTableName(CommonUtil.getTableName(Department.class));
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = templateService.save(template);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DeptTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<TemplateForm> form) throws Exception {
	List<Template> templates=new ArrayList<Template>();
		ReturnValue returnValue =new ReturnValue();
		Template entityTemplate = new Template();
		for (TemplateForm template : form) {
			entityTemplate=new Template();
			BeanUtils.copyProperties(template, entityTemplate);
			templates.add(entityTemplate);
		}
		boolean result = templateService.saveAll(templates);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DeptTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = templateService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DeptTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = templateService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DeptTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(TemplateForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		Template template = new Template();
		BeanUtils.copyProperties(form, template);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = templateService.updateTemplate(template);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(DeptTemplateController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/queryById")
	public @ResponseBody
	TemplateVO queryById(String id) throws Exception {
		TemplateVO vo = new TemplateVO();
		Template template = templateService.getTemplateById(id);
		if (template != null) {
			BeanUtils.copyProperties(template, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, TemplatePageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Template.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		dc.add(Restrictions.eq("tableName", CommonUtil.getTableName(Department.class)));
		return templateService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
