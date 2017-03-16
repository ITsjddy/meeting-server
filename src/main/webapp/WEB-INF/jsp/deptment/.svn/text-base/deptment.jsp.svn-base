<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>团管理</title>
	<!-- ztree用的css和js -->
	<link rel="stylesheet" href="${ctx }/css/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx }/css/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.excheck.js"></script>
	<script src="${ctx }/static/sysviews/department/js/departmenttree.js"></script>
</head>
<body style="width:100%;height:100%;">
	<input type="hidden" id="groupid" value="${groupid}"/>
	<ul class="breadcrumb"><li>团管理</li></ul>
	<div style="width:100%;height:100%;display:inline-block;">
		<div class="content_wrap" style="width:20%;height:100%;background-color: #FAFAFA; float: left;overflow: auto;">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div id="wrapper" style="width:80%;height:100%;float:left;padding-left:10px">
		<iframe id="mainFrame" name="mainFrame" src="${ctx }dispatcher/department/indexdetial" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="560"></iframe>
    </div>
 </div>
</body>
</html>