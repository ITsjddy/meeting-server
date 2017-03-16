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
<script src="${ctx }/static/sysviews/department/js/edit.js"></script>	
</head>
<body ng-app="guestApp" ng-controller="guestController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li>团信息修改</li>
</ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form">
			<input id="id" name="id" type="hidden" value="${id}" />
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">团名称：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="团名称" class="form-control" required autofocus id="departname" name="departname" ng-model="guest.departname" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">团名称英文：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="团名称英文" class="form-control" autofocus id="endepartname" name="endepartname" ng-model="guest.endepartname" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">团分类：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" name="classification" ng-model="guest.classification">
						<option value="">--请选择--</option>
						<option value="tneib" ng-model="guest.classification" ng-selected="isSelected(guest.classification)">内宾团</option>
						<option value="twaib" ng-model="guest.classification" ng-selected="isSelected(guest.classification)">外宾团</option>
					</select>
				</div>  
				<label for="firstname" class="col-sm-2 control-label">团类型：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" name="type" ng-model="guest.type">
						<option value="">--请选择--</option>
						<option value="tguojzzt" ng-model="guest.type" ng-selected="isSelected(guest.type)">国际组织团</option>
						<option value="tzhengyt" ng-model="guest.type" ng-selected="isSelected(guest.type)">政要团</option>
						<option value="tshengst" ng-model="guest.type" ng-selected="isSelected(guest.type)">省市团</option>
						<option value="qita" ng-model="guest.type" ng-selected="isSelected(guest.type)">其他</option>
					</select>
				</div>   
  			</div>
		</form>
	</div>
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
		<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="guestSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>
</body>
</html>