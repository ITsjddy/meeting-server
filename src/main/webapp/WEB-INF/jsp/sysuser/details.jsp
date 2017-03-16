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
<script src="${ctx }/static/sysviews/sysuser/js/edit.js"></script>
	
</head>
<body ng-app="sysUserApp" ng-controller="sysUserController" style="width:100%;height:100%;margin:10px">
	<ul class="breadcrumb">
		<li>系统用户信息详情</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<input id="id" name="id" type="hidden" value="${id}" />
				
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
					<div class="col-sm-3"> 
						<input type="text" class="form-control" ng-model="sysUser.userName" readonly/>
					</div> 
	  			</div> 
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">姓名：</label> 
					<div class="col-sm-3"> 
						<input type="text" class="form-control" ng-model="sysUser.name" readonly/>
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">联系电话：</label> 
					<div class="col-sm-3"> 
						<input type="text" class="form-control" ng-model="sysUser.mobile" readonly/>
					</div> 
	  			</div>
	  			<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label">权限：</label>
					<div class="col-sm-3"> 
						<select class="form-control" ng-model="sysUser.roleId" readonly>
							<option ng-repeat="role in lSysRole" value="{{role.id}}" >{{role.name}}</option>
						</select>
					</div>
				</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">是否启用：</label> 
					<div class="col-sm-3"> 
						<input type="radio" value="1" ng-model="sysUser.state" ng-checked="isSelected(sysUser.state=='1')" disabled/>&nbsp;是&nbsp;&nbsp;
						<input type="radio" value="0" ng-model="sysUser.state" ng-checked="isSelected(sysUser.state=='0')" disabled/>&nbsp;否
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">备注：</label> 
					<div class="col-sm-3"> 
						<textarea class="form-control" ng-model="sysUser.remark" readonly></textarea>
					</div> 
	  			</div>
			</form>
		</div>
		
		<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10"> 
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
	</div>
</body>
</html>