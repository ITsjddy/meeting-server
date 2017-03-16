package com.smartdot.meeting.server.common.util.code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class CodeGenerateFactory {
	/**
	 * @since1.0
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		generSimple();
	}

	private static void generSimple() throws IOException, FileNotFoundException {
		String path = "package.properties";
		Properties properties = new Properties();
		properties.load(CodeGenerateFactory.class.getResourceAsStream(path));
		List<String[]> argsList = new ArrayList<String[]>();
		for (Iterator<Object> it = properties.keySet().iterator(); it.hasNext();) {
			String domainName = (String) it.next();
			String packageName = properties.getProperty(domainName);
			String[] pair = new String[] { domainName, packageName };
			argsList.add(pair);
		}
		for (String[] arg : argsList) {
			// 根据类路径和生成的包前缀合成模板
			CodeGenerator gen=new CodeGenerator(arg);
			gen.generate();
		}
	}
}
