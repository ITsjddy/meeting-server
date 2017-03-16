<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="author" content="ms"/>
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />

<title>info</title>
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
<script src="${ctx }/static/sysviews/sysrole/js/edit.js"></script>
</head>
<body ng-app="sysRoleApp" ng-controller="sysRoleController" style="width:100%;height:100%;margin:10px">
	<ul class="breadcrumb">
		<li>角色详情 </li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<input id="id" name="id" type="hidden" value="${id}" />
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">名称：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" class="form-control" ng-model="sysRole.name" readonly/>
					</div> 
	  			</div> 
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">备注：</label> 
					<div class="col-sm-3"> 
						<textarea class="form-control" rows="3" cols="3" ng-model="sysRole.remark" readonly></textarea>
					</div> 
	  			</div>
			</form>
		</div>
		
		<div class="form-group breadcrumb" style="width:100%;float:left;">
			<div class="col-sm-offset-2 col-sm-10"> 
			<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
	</div>
	
</body>
</html>