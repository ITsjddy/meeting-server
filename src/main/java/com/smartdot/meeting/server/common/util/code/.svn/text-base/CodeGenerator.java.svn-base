package com.smartdot.meeting.server.common.util.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

//import com.iskyshop.core.annotation.Title;

public class CodeGenerator {

	protected String templateDir = "\\templates\\";
	protected String resultDir = "\\result\\";
	private final String beanName;
	private final String packageName;
	private Map<String, String> javafiles;

	public CodeGenerator(String[] args) {
		String bean = args[0];
		String packageName = args[1];
		this.beanName = bean;
		this.packageName = packageName;
	}

	public void init() {
		String shorBeanName = beanName.substring(beanName.lastIndexOf(".") + 1);
		addJavaFiles(shorBeanName);
	}

	private void addJavaFiles(String shortBeanName) {
		String moduleName = shortBeanName.toLowerCase();
		Map<String, String> javaMapping = new HashMap<String, String>();
		//dao接口
		javaMapping.put("dao/iDaoTemplate.java", "java/modules/" + moduleName + "/dao/I"+ shortBeanName +"Dao.java");
		//dao实现;
		javaMapping.put("dao/impl/daoImplTemplate.java", "java/modules/" + moduleName + "/dao/impl/" + shortBeanName + "DaoImpl.java");
		//service接口;
		javaMapping.put("service/IServiceTemplate.java", "java/modules/" + moduleName + "/service/I" + shortBeanName + "Service.java");
		//service实现;
		javaMapping.put("service/impl/ServiceImplTemplate.java", "java/modules/" + moduleName + "/service/impl/" + shortBeanName + "ServiceImpl.java");
		//service测试;
		javaMapping.put("test/ServiceTesterTemplate.java", "test/modules/" + moduleName + "/service/" + shortBeanName + "ServiceTester.java");
		//Controller
		javaMapping.put("controller/ControllerTemplate.java", "java/modules/" + moduleName + "/controller/" + shortBeanName + "Controller.java");
		//Controller测试
		javaMapping.put("test/ControllerTesterTemplate.java", "test/modules/" + moduleName + "/controller/" + shortBeanName + "ControllerTester.java");
		//普通的请求form
		javaMapping.put("model/FormTemplate.java", "java/modules/" + moduleName + "/model/" + shortBeanName + "Form.java");
		//分页的请求form;
		javaMapping.put("model/PageFormTemplate.java", "java/modules/" + moduleName + "/model/" + shortBeanName + "PageForm.java");
		// VO响应;
		javaMapping.put("model/VOTemplate.java", "java/modules/" + moduleName + "/model/" + shortBeanName + "VO.java");
		this.javafiles = javaMapping;
	}

	private void mergeJava(String sourcePath, String targetPath) throws Exception {
		for (String templateFile : this.javafiles.keySet()) {
			String targetFile = this.javafiles.get(templateFile);
			Properties pro = new Properties();
			pro.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
			pro.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
			pro.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, sourcePath);
			VelocityEngine ve = new VelocityEngine(pro);
			org.apache.velocity.Template t = ve.getTemplate(templateFile, "UTF-8");
			VelocityContext context = new VelocityContext();

			Class<?> clz = Class.forName(beanName);
			Field[] fields = clz.getDeclaredFields();
			List<UserField> ufs = new ArrayList<UserField>();
			for (Field field : fields) {
				if (field.getType().isPrimitive() || field.getType().toString().indexOf("java.lang.") >= 0
						|| field.getType().toString().indexOf("java.util.") >= 0 || field.getType().toString().indexOf("java.sql.") >= 0) {
					if ("serialVersionUID".equals(field.getName())) {
						continue;
					}
					UserField uf = new UserField();
					uf.setName(field.getName());
					if(field.getType().getName().split("\\.").length<=1){
						uf.setType(field.getType().getName());
					}else{
						uf.setType(field.getType().getName().split("\\.")[2]);
					}
					
					ufs.add(uf);
				}
			}
			context.put("fields", ufs);

			context.put("domainName", this.beanName.substring(beanName.lastIndexOf(".") + 1));
			context.put("domain", (this.beanName.substring(beanName.lastIndexOf(".") + 1).toLowerCase()));
			context.put("packageName", this.packageName);
			context.put("floder", this.beanName.substring(beanName.lastIndexOf(".") + 1).toLowerCase());
			File file = new File(targetPath, targetFile);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			if (!file.exists())
				file.createNewFile();

			FileOutputStream outStream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(outStream, "UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			t.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
			System.out.println("成功生成Java文件:" + (targetPath + targetFile).replaceAll("/", "\\\\"));
		}
	}

	public void generate() {
		this.init();
		try {
			String sourcePath = System.getProperty("user.dir") + this.templateDir;
			String java_targetPath = System.getProperty("user.dir") + this.resultDir + "\\" + this.packageName.replace(".", "\\");
			System.out.println("开始生成java文件......");
			// this.mergeHTML(sourcePath, html_targetPath);
			this.mergeJava(sourcePath, java_targetPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("生成HTML页面模板失败，失败原因如下:");
			e.printStackTrace();
		}
	}
}
