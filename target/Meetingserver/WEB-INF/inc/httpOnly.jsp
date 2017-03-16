<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("P3P" , "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"" );
String sessionid = request.getSession().getId();
response.addHeader("Set-Cookie", "JSESSIONID=" + sessionid + ";  HttpOnly");
%>