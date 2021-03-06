<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>服务人员tree列表</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	
	<link rel="stylesheet" href="${ctx }/css/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx }/css/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.excheck.js"></script>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/guest/js/indexServicePersonnelTree.js"></script>
</head>
<body ng-app="guestApp" ng-controller="guestController">
	<div id="wrapper">
        <div class="zTreeDemoBackground">
        	<input type="hidden" id="i" name="i" value="${i}">
        	<p>
				<input type=text ng-model="searchValue" placeholder="查询tree名称" style="width:75%;" ng-keyup="keyupSearch(searchValue)">
				<button ng-click="search()">查询</button>
			</p>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
    </div>
</body>
</html>