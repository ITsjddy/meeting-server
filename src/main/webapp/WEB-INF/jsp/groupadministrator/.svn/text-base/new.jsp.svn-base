<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
<meta charset="utf-8"> 
<title>新建管理员</title>
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
<script src="${ctx }static/sysviews/groupAdministrator/js/new.js"></script>	
</head>
<body ng-app="guestApp" ng-controller="guestController">
<ul class="breadcrumb">
	<li>新建管理员</li>
</ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form">
			<input id="id" name="id" type="hidden" value="${id}" />
			<input id="groupId" name="groupId" type="hidden" value="${id}" />
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">邀请码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="邀请码" class="form-control" required autofocus id="invitationCode" name="invitationCode" ng-model="guest.invitationCode" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="手机号" class="form-control" autofocus id="mobile" name="mobile" ng-model="guest.mobile" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">email：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="email" class="form-control" autofocus id="email" name="email" ng-model="guest.email" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="用户名" class="form-control" autofocus id="userName" name="userName" ng-model="guest.userName" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
  			<label for="firstname" class="col-sm-2 control-label">密码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="密码" class="form-control" required autofocus id="password" name="password" ng-model="guest.password" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">姓名：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="姓名" class="form-control" required autofocus id="name" name="name" ng-model="guest.name" maxlength="220" />
				</div>
  			</div>
		</form>
	</div>
	<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
			<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="guestSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>
</body>
</html>