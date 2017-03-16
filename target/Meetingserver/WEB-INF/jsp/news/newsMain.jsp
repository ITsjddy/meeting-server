<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>新闻主页面</title>
</head>
<body style="width:100%;height:100%;">
	<div id="wrapper" style="width:80%;height:100%;float:left;padding-left:10px">
		<iframe id="mainFrame" name="mainFrame" src="${ctx }dispatcher/news/toList" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="560"></iframe>
    </div>
 </div>
</body>
</html>