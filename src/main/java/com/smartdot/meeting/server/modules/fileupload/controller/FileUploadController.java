package com.smartdot.meeting.server.modules.fileupload.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.smartdot.meeting.server.common.model.ReturnValue;
import com.smartdot.meeting.server.common.util.PageUtilExtent;
import com.smartdot.meeting.server.modules.fileupload.model.FileUploadForm;
import com.smartdot.meeting.server.modules.fileupload.model.FileUploadPageForm;
import com.smartdot.meeting.server.modules.fileupload.model.FileUploadVO;
import com.smartdot.meeting.server.modules.fileupload.service.IFileUploadService;
import com.smartdot.meeting.server.common.entity.FileUpload;


@Controller
@RequestMapping(value = "/fileUpload")
public class FileUploadController {

	private static final Logger _LOG = Logger.getLogger(FileUploadController.class);
	
	public static final String SUCCESS_MESSAGE = "操作成功";
	
	@Resource(name = IFileUploadService.SERVICE_NAME)
	private IFileUploadService fileUploadService;

	
	@RequestMapping(value = "/index")
	public String index() throws Exception {
		
		return "fileUpload/list";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model) throws Exception {
		
		return "fileUpload/edit";
	}
	
	@RequestMapping(value = "/edit")
	public String edit(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "fileUpload/edit";
	}

	@RequestMapping(value = "/details")
	public String details(String id, Model model) throws Exception {
		
		model.addAttribute("id", id);
		return "fileUpload/details";
	}
	
	@RequestMapping(value = "/queryAll")
	public @ResponseBody
	List<FileUploadVO> queryAll() throws Exception {
		List<FileUpload> fileUploads = fileUploadService.findAll();
		List<FileUploadVO> vo = new ArrayList<FileUploadVO>();
		FileUploadVO fileUploadVO = null;
		for (FileUpload fileUpload : fileUploads) {
			fileUploadVO  = new FileUploadVO();
			BeanUtils.copyProperties(fileUpload, fileUploadVO);
			vo.add(fileUploadVO);
		}
		return vo;
	}

	@RequestMapping(value = "/save")
	public @ResponseBody
	ReturnValue save(FileUploadForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		FileUpload fileUpload= new FileUpload();
		BeanUtils.copyProperties(form, fileUpload);
		boolean result = false;
		if (StringUtils.isEmpty(form.getId())) {
			result = fileUploadService.save(fileUpload);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(FileUploadController.SUCCESS_MESSAGE);
		}
		return returnValue;

	}

	@RequestMapping(value = "/saveList")
	public @ResponseBody
	ReturnValue saveList(List<FileUploadForm> form) throws Exception {
	List<FileUpload> fileUploads=new ArrayList<FileUpload>();
		ReturnValue returnValue =new ReturnValue();
		FileUpload entityFileUpload = new FileUpload();
		for (FileUploadForm fileUpload : form) {
			entityFileUpload=new FileUpload();
			BeanUtils.copyProperties(fileUpload, entityFileUpload);
			fileUploads.add(entityFileUpload);
		}
		boolean result = fileUploadService.saveAll(fileUploads);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(FileUploadController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}
	
	@RequestMapping(value = "/deleteById")
	public @ResponseBody
	ReturnValue deleteById(String id) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = fileUploadService.deleteById(id);
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(FileUploadController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/deleteList")
	public @ResponseBody
	ReturnValue deleteList(String[] ids) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = true;
		if(null != ids && ids.length > 0){
			result = fileUploadService.deleteAll(ids);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(FileUploadController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/update")
	public @ResponseBody
	ReturnValue update(FileUploadForm form) throws Exception {
		ReturnValue returnValue =new ReturnValue();
		boolean result = false;
		FileUpload fileUpload = new FileUpload();
		BeanUtils.copyProperties(form, fileUpload);
		if (StringUtils.isNotEmpty(form.getId())) {
			result = fileUploadService.updateFileUpload(fileUpload);
		}
		if(result){
			returnValue.setSuccess(true);
			returnValue.setMessage(FileUploadController.SUCCESS_MESSAGE);
		}
		return returnValue;
	}

	@RequestMapping(value = "/queryById")
	public @ResponseBody
	FileUploadVO queryById(String id) throws Exception {
		FileUploadVO vo = new FileUploadVO();
		FileUpload fileUpload = fileUploadService.getFileUploadById(id);
		if (fileUpload != null) {
			BeanUtils.copyProperties(fileUpload, vo);
		}
		return vo;
	}

	@RequestMapping(value = "/pageQuery")
	public @ResponseBody
	Map<String, Object> pageQuery(HttpServletRequest request, FileUploadPageForm req) throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(FileUpload.class);
		dc.add(Restrictions.eq("enable", true));
		dc.addOrder(Order.desc("updateTimes"));
		
		return fileUploadService.pagedQuery(dc, PageUtilExtent.getPageInfo(request));
	}
}
