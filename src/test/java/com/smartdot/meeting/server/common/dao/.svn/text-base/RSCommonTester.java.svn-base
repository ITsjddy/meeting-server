package com.smartdot.meeting.server.common.dao;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SessionManager;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

public class RSCommonTester {
	
	private final static String CONTEXT_PATH = "/Meetingserver";
	private final static String RS_PATH =  "/rs";
	protected final static String FACADE_ADDRESS = "http://localhost:8089" + CONTEXT_PATH;
	private final static String WADL_ADDRESS = FACADE_ADDRESS + "?_wadl";
	
	private static final Logger _LOG = Logger.getLogger(RSCommonTester.class);
	
	protected static WebClient client;
	private static Server server;
	
	@BeforeClass
	public static void setup() {
		setupServer();
		setupHTTPConduit();
	}
	
	
	private static void setupServer() {
		server = new Server(8089);
		server.setAttribute("org.eclipse.jetty.util.URI.charset", "UTF-8");
		
		SessionManager sessionManager = new HashSessionManager();
		SessionHandler sessionHandler = new SessionHandler(sessionManager);
		//sessionManager.setSessionIdManager(new HashSessionIdManager());
		//sessionManager.setSessionHandler(sessionHandler);
		//sessionHandler.setSessionManager(sessionManager);
		//server.addManaged(sessionManager);
		//server.setHandler(sessionHandler);
		server.setSessionIdManager(new HashSessionIdManager());
		

		// 注册servlet信息
		final ServletHolder servletHolder = new ServletHolder(new CXFServlet());
		final ServletContextHandler context = new ServletContextHandler();
		context.setContextPath(CONTEXT_PATH);
		
		// 添加Spring监听器
		context.addEventListener(new ContextLoaderListener());
		
		// 添加SpringSecurityFilter
		context.addFilter(new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain")), "/rs/*", EnumSet.allOf(DispatcherType.class));
		context.addFilter(new FilterHolder(new OpenSessionInViewFilter()), "/rs/*", EnumSet.allOf(DispatcherType.class));

		
		context.setInitParameter("contextConfigLocation", "classpath*:conf/applicationContext*.xml");

		context.addServlet(servletHolder, "/rs/*");
		context.setSessionHandler(sessionHandler);
		
		server.setHandler(context);
		try {
			server.start();
			//server.join();
		} catch (Exception e) {
			_LOG.error("Server start error.");
			try {
				server.stop();
			} catch (Exception e1) {
				_LOG.error("Server stop error.");
			}
		}
	}
	
	private static void setupHTTPConduit(){
		
		/*
		   JSONProvider<?> jsonProvider = new JSONProvider<Object>();
		   jsonProvider.setDropRootElement(true); 
		   jsonProvider.setSupportUnwrapped(true); 
		   jsonProvider.setSerializeAsArray(true); 
		 */
		JacksonJaxbJsonProvider jsonProvider = new JacksonJaxbJsonProvider();
		List<JacksonJaxbJsonProvider> providers = new ArrayList<JacksonJaxbJsonProvider>();
		providers.add(jsonProvider);
		
		client = WebClient.create(FACADE_ADDRESS, providers);
		client.header("Authorization", "Basic dG9tOjEyMzQ1Njc4");
		client.encoding("UTF-8");
		HTTPConduit conduit = (HTTPConduit)WebClient.getConfig(client).getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(60*10*1000);
		httpClientPolicy.setReceiveTimeout(1000000000000L);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setAcceptEncoding("UTF-8");
		conduit.setClient(httpClientPolicy);
		
	}
	
	@AfterClass
	public static void destroy() throws Exception {
		server.stop();
		server.destroy();
	}
	
	@SuppressWarnings({ "static-access", "unused" })
	@Deprecated
	private void waitForWADL() throws Exception {
		//WebClient client = WebClient.create(WADL_ADDRESS); // wait for 20 secs
															// or so
		/*for (int i = 0; i < 20; i++) {
			Thread.currentThread().sleep(1000);
			Response response = client.get();
			if (response.getStatus() == 200) {
				break;
			}
		}*/ // no WADL is available yet - throw an exception or give tests a chance to run anyway
	}
	
	protected String path(String path) {
		return RS_PATH + "/" + path;
	}
}
