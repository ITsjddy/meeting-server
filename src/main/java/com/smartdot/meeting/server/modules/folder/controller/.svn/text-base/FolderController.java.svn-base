package com.smartdot.meeting.server.modules.folder.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.entity.Folder;
import com.smartdot.meeting.server.common.model.Page;
import com.smartdot.meeting.server.common.model.ReturnUploadImage;
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.AjaxResult;
import com.smartdot.meeting.server.common.util.CommonUtil;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.common.util.uploadUtil.Upload;
import com.smartdot.meeting.server.modules.folder.model.ReturnUploadFolder;
import com.smartdot.meeting.server.modules.folder.service.IFolderService;




@Controller
@RequestMapping(value = "/folder")
public class FolderController {

	private static final Logger _LOG = Logger.getLogger(FolderController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IFolderService.SERVICE_NAME)
	private IFolderService folderService;
	
	
	/**
	 *  列表页面
	 * */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/folder/folderList";
	}
	
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public Map<String, Object> folderList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		Map<String,Object> folderMap = new HashMap<String,Object>();
		String fileName = request.getParameter("fileName");
		if(StringUtils.isNotBlank(fileName)){
			folderMap.put("fileName",fileName);
		}
		String fileEnName = request.getParameter("fileEnName");
		if(StringUtils.isNotBlank(fileEnName)){
			fileEnName = new String(fileEnName.getBytes("iso-8859-1"),"UTF-8");
			folderMap.put("fileEnName", fileEnName);
		}
		String fileType = request.getParameter("fileType");
		if(StringUtils.isNotBlank(fileType)){
			fileType = new String(fileType.getBytes("iso-8859-1"),"UTF-8");
			folderMap.put("fileType", fileType);
		}
		String explains = request.getParameter("explains");
		if(StringUtils.isNotBlank(explains)){
			explains = new String(explains.getBytes("iso-8859-1"),"UTF-8");
			folderMap.put("explains", explains);
		}
		String createTimes = request.getParameter("createTimes");
		if(StringUtils.isNotBlank(createTimes)){
			folderMap.put("createTimes", createTimes);
		}
 		folderMap.put("enable", true);
		return folderService.pageList(PageUtilExtent.getPageInfo(request),folderMap);
	}
	/*
	 *  文件资料详情
	 * */
	@RequestMapping(value="/returnDetail")
	public String returnDetail(HttpServletRequest request, HttpServletResponse response,Model model){
		String id = request.getParameter("id");
		
		model.addAttribute("id",id);
		return "folder/folderDetail";
	}
	
	/**
	 * 通过ID查询资料信息
	 * */
	@RequestMapping(value = "/folderDetailById")
	public Map<String, Object> folderDetailById(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		ModelAndView mav = new ModelAndView("folder/folderDetail");
		String id = request.getParameter("id");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(id)){
			Folder folder = folderService.getFolderById(id);
			map.put("fileName", folder.getFileName());
			map.put("fileType",folder.getFileType());
			map.put("filePath", folder.getFilePath());
			map.put("explains", folder.getExplains());
			map.put("createTime",folder.getCreateTimes());
			map.put("editTime", folder.getEditTimes());
			map.put("id", folder.getId());
//			mav.addObject(folder);
		}
		return map;
	}
	/**
	 *  修改 
	 * **/
	@RequestMapping(value = "/folderEdit")
	public String edit(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
		String id = request.getParameter("id");
		model.addAttribute("id",id);
		return "folder/folderEdit";
	}
	
	@RequestMapping(value = "/editSave")
	public String folderEditSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String fileName = request.getParameter("fileName");
//		if(StringUtils.isNotBlank(fileName)){
//			fileName = new String(fileName.getBytes("iso-8859-1"),"UTF-8");
//		}
		String fileEnName = request.getParameter("fileEnName");
//		if(StringUtils.isNotBlank(fileEnName)){
//			fileEnName = new String(fileEnName.getBytes("iso-8859-1"),"UTF-8");
//		}
		String fileType = request.getParameter("fileType");
		String explains = request.getParameter("explains");
//		if(StringUtils.isNotBlank(explains)){
//			explains = new String(explains.getBytes("iso-8859-1"),"UTF-8");
//		}
		Folder folder = folderService.getFolderById(id);
		folder.setFileName(fileName);
		folder.setFileType(fileType);
		folder.setExplains(explains);
		folder.setEditTimes(new Timestamp(System.currentTimeMillis()));
		folderService.save(folder);
		return "redirect:/dispatcher/folder/index";
	}
	
	/**
	 *  删除
	 * */
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = folderService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(FolderController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		String[] idArray = ids.split(",");
		List<String> idList = Arrays.asList(idArray);
		boolean result = folderService.deleteAll(idList);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(FolderController.SUCCESS_MESSAGE);
		}
		return returnValue;	
	}
	/**
	 * 文件上传保存
	 * */
	@RequestMapping(value = "/returnSave")
	public String returnSave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "folder/addFolder";
	}
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> addSave(HttpServletRequest request, HttpServletResponse response,String folder) throws Exception {
		try {
			boolean flag = false;
			flag = folderService.saveAddFolder(folder);
			return AjaxResult.successResult();
		} catch (Exception e) {
			return AjaxResult.errorResult("内部服务器错误");
		}
	}
//	@RequestMapping(value = "/save")
//	public ModelAndView saveFileInput(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		ModelAndView mav = new ModelAndView("redirect:index");
//		String method = request.getMethod();
//		String fileName = request.getParameter("fileName");
//		if(StringUtils.isNotBlank(fileName)){
//			fileName = new String(fileName.getBytes("iso-8859-1"), "utf-8");
//	    }
//		String fileEnName = request.getParameter("fileEnName");
//		if(StringUtils.isNotBlank(fileEnName)){
//			fileEnName = new String(fileEnName.getBytes("iso-8859-1"), "utf-8");
//	    }
//		String fileType = request.getParameter("fileType");
//		if(StringUtils.isNotBlank(fileType)){
//			fileType = new String(fileType.getBytes("iso-8859-1"), "utf-8");
//	    }
//		String createTime = request.getParameter("createTime");
//		String explains = request.getParameter("explains");
//		String editTime = request.getParameter("editTime");
//		
//		String ids = request.getParameter("id");
//		try{
//			Folder folder = getFolder(request);
//			if(null!=folder){
//				folderService.save(folder);
//			}
//		}catch (Exception e) {
//			e.printStackTrace();
//			System.out.print(e);
//		}
//		mav.addObject("fileName", fileName);
//	    mav.addObject("fileEnName", fileEnName);
//	    mav.addObject("fileType", fileType);
//	    mav.addObject("createTime", createTime);
//	    mav.addObject("explains",explains);
//	    mav.addObject("editTime",editTime);
//		return mav;
//	}
//	
//	private Folder getFolder(HttpServletRequest request,HttpServletResponse response) {
//		Folder folder = new Folder();
//		try{
//			String ids = request.getParameter("id");
//			String fileName = request.getParameter("fileName");
//			String fileEnName = request.getParameter("fileEnName");
//			String fileType = request.getParameter("fileType");
//			String explains = request.getParameter("explains");
////			String filePath  = upload(request, "filePath", request.getRealPath("/"), fileName,"upload");
//			String filePath  = 
//			if(StringUtils.isNotBlank(ids)){  
//				folder = folderService.getFolderById(ids);
//				folder.setEditTimes(new Timestamp(System.currentTimeMillis()));
//			}
//			folder.setFileName(fileName);  
//			folder.setFileEnName(fileEnName);
//			folder.setFileType(fileType);
//			folder.setExplains(explains);
//			if(StringUtils.isNotBlank(filePath)){
//				folder.setFilePath(filePath);
//			}
//		}catch (Exception e) {
//			e.getMessage();
//			System.out.print(e);
//		}
//		return folder;
//	}
//	
//	public static String upload(HttpServletRequest request,String elementName,String root,
//			String folderName,String fiName) {
//		String result = "";
//		try {
////			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
////			MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
////			MultipartHttpServletRequest req = resolver.resolveMultipart(request);
//			MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
//			MultipartFile file = req.getFile(elementName);
//			if (file == null || file.getSize() == 0) {
//				return null;
//			}
//	        ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
//			String upFolder = bundle.getString("upload_folder");//
////			if(StringUtils.isBlank(upFolder)){ 
////				return null;
////			}
//			String upFolder2=upFolder;
//			if(StringUtils.isNotBlank(folderName)){
//				upFolder=upFolder + File.separator + folderName;
//				upFolder2=upFolder2 + "/" + folderName;
//			}
//			
//			String name = file.getOriginalFilename();
//			int i = name.lastIndexOf(".");
//			String dec = name.substring(i);
//			dec = dec.toLowerCase();
//
//			String filePath = root;
//			
//			filePath = filePath + File.separator + upFolder + File.separator;
//			result = "/" + upFolder2 + "/";
//
//			String fileName = fiName + dec;
//			File dir = new File(filePath);
//			if (!dir.exists()) {
//				dir.mkdirs();
//			}else{
//				File dirs = new File(filePath + fileName);
//				if(dirs.exists()){
//					String backupName = format(new Date(), "yyyyMMddHHmmssSS") + RandomStringUtils.randomNumeric(3) + "_hy" + dec;
//					dirs.renameTo(new File(filePath + backupName));
//				}
//			}
//			filePath = filePath + fileName;
//			result = result + fileName;
//			uploadFile(file,filePath);
//			String sharedType = bundle.getString("shared_type");
//			if(StringUtils.isNotBlank(sharedType)){
//				String[] types =sharedType.split(",");
//				for(int j = 0;j <types.length;j++){
//					if("1".equals(types[j])){
//						CommonUtil.shareUpload(filePath,folderName,"shared_info");
//					}
//					if("2".equals(types[j])){
//						CommonUtil.shareUpload(filePath,folderName,"shared_info2");
//					}
//					if("3".equals(types[j])){
//						CommonUtil.shareUpload(filePath,folderName,"shared_info3");
//					}
//				}
//			}
//        } catch (Exception ex) {
//        	ex.getMessage();
//        	System.out.print(ex);
//        }
//		
//		return result;
//	}
	
	@RequestMapping(value = "upload")
	public String uploadFolder(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("upfile"); // UEditor传到后台的是这个upfile，其他的文件上传，应该也差不多是这个，还没去研究，断点一下就知道了
		if (file == null || file.getSize() == 0) {
			return null;
		}
		//获取上传文件主目录
        ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String upFolder = bundle.getString("upload_folder");//
		if(StringUtils.isBlank(upFolder)){
			return null;
		}
		String type = "ueditor";
		String upFolderResult=upFolder;
		if(StringUtils.isNotBlank(type)){
			upFolder=upFolder + File.separator + type;
			upFolderResult=upFolderResult + "/" + type;
		}
		
		String name = file.getOriginalFilename();
		// 后缀小数点前面的字符数
		int i = name.lastIndexOf(".");
		// 得到后缀
		String dec = name.substring(i);
		dec = dec.toLowerCase();

		String filePath = request.getRealPath("/");
		String result = "";
		filePath = filePath + File.separator + upFolder + File.separator;
		result = "/" + upFolderResult + "/";
		String fileName = UUID.randomUUID() + RandomStringUtils.randomNumeric(3) + dec;
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		filePath = filePath + fileName;
		result = result + fileName;
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 考虑大附件上传， 一点点存储
			fis = file.getInputStream();
			fos = new FileOutputStream(filePath);
			file.getInputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.write(file.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result=null;
		} catch (IOException e) {
			e.printStackTrace();
			result=null;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				result=null;
			}
		}
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		ReturnUploadFolder resultForUe = new ReturnUploadFolder();// 这个是UEditor需要的返回值内容，UEditor要的返回值需要封装成Json格式
		resultForUe.setTitle(file.getOriginalFilename());// 这里需要设置文件名称如：xxx.jpg
		resultForUe.setOriginal(file.getOriginalFilename());// 这里需要设置文件名称如：xxx.jpg
		resultForUe.setState("SUCCESS");
		resultForUe.setUrl(basePath+result);
		
		String resultJson = JSON.toJSONString(resultForUe);// 这边就是为了返回给UEditor做的格式转换
		response.getWriter().write(resultJson);
		return null;
	
	}
	
	public static void uploadFile(MultipartFile file,String storePath){
		InputStream is =null;
		OutputStream os =null;
		try {
			is = file.getInputStream();
			os = new FileOutputStream(storePath);
			int bytes = 0;
			byte[] buffer = new byte[8192];
			while ((bytes = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytes);
			}
			os.close();
			is.close();
		} catch (Exception e) {
			e.getMessage();
			System.out.print(e);
		} finally{
			try{
				if(os!=null){
					os.close();
				}
				if(is!=null){
					is.close();
				}
			}catch(Exception e){
			e.getMessage();
			System.out.print(e);
			}			
		}
	}

	
	/**
	 * 时间转换
	 * */
	 private static  String format(Date date, String pattern) {
			return date == null ? "" : new SimpleDateFormat(pattern).format(date);
		}
	/**
	 *  分页？
	 * */
	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Page<Folder> pageQuery(Page<Folder> pageQuery, Folder req) throws Exception {
		
		Page<Folder> vo = new Page<Folder>();
		Page<Folder> page = new Page<Folder>();
		Folder folder = new Folder();
		BeanUtils.copyProperties(req, folder);
		page =folderService.findFolderByPage(
				folder, pageQuery);
		BeanUtils.copyProperties(page, vo);
		List<Folder> result = new ArrayList<Folder>();

		for (Folder source : page.getResult()) {
			Folder target = new Folder();
			BeanUtils.copyProperties(source, target);
			result.add(target);
		}
		vo.setResult(result);
		return vo;

	}
	
}
