package com.smartdot.meeting.server.modules.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.alibaba.fastjson.JSON;
import com.smartdot.meeting.server.common.model.ReturnUploadImage;

@Controller
@RequestMapping(value = "/uploadIMG")
public class Upload {
	
	@RequestMapping(value = "upload")
	public String uploadImage(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 转型为MultipartHttpRequest：
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("uploadify"); // UEditor传到后台的是这个upfile，其他的文件上传，应该也差不多是这个，还没去研究，断点一下就知道了
		if (file == null || file.getSize() == 0) {
			return null;
		}
		//获取上传文件主目录
		ResourceBundle bundle = PropertyResourceBundle.getBundle("config");
		String upFolder = bundle.getString("upload_folder");//
		if(StringUtils.isBlank(upFolder)){
			return null;
		}
		String type = StringUtils.isNotBlank(request.getParameter("folderName"))?request.getParameter("folderName"):"folder";
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
		
		String filePath = request.getSession().getServletContext().getRealPath("/");
		String[] str = filePath.split("meeting-server");
		String result = "";
		str[0] = str[0] + File.separator + upFolder + File.separator;
		result = "/" + upFolderResult + "/";
		String fileName = UUID.randomUUID() + RandomStringUtils.randomNumeric(3) + dec;
		File dir = new File(str[0]);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		str[0] = str[0] + fileName;
		result = result + fileName;
		InputStream fis = null;
		FileOutputStream fos = null;
		try {
			// 考虑大附件上传， 一点点存储
			fis = file.getInputStream();
			fos = new FileOutputStream(str[0]);
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
		ReturnUploadImage resultForUe = new ReturnUploadImage();// 这个是UEditor需要的返回值内容，UEditor要的返回值需要封装成Json格式
		resultForUe.setTitle(file.getOriginalFilename());// 这里需要设置文件名称如：xxx.jpg
		resultForUe.setOriginal(file.getOriginalFilename());// 这里需要设置文件名称如：xxx.jpg
		resultForUe.setState("SUCCESS");
		resultForUe.setUrl(basePath+result);
		
		String resultJson = JSON.toJSONString(resultForUe);// 这边就是为了返回给UEditor做的格式转换
		response.getWriter().write(upFolder+"\\"+fileName);
		return null;
		
	}
	
	
	@RequestMapping(value = "delImg")
	public void delImg(HttpServletRequest request, HttpServletResponse response, String imgURL)
			throws IllegalStateException, IOException {
			File f = new File(imgURL);
			f.delete();
	}

}
