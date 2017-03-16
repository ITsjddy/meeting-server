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
<script src="${ctx }/static/sysviews/template/js/deptadd.js"></script>	
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
				<label for="firstname" class="col-sm-2 control-label">实体字段名称：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<select class="form-control" ng-change="changeConHallId()" name="conHallId" id="conHallId" ng-model="schedule.conHallId">
						<option value="">--请选择--</option>
						<option ng-repeat="val in guest" value="{{val}}" ng-model="conHall.conHallId">{{val}}</option>
					</select>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">自定义名称：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="自定义名称" class="form-control" autofocus id="endepartname" name="endepartname" ng-init="template.fieldName=''" ng-model="template.fieldName" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">模板字段名称：<font color="red">*</font></label> 
				<div class="col-sm-8"> 
					<input type="text" placeholder="模板字段名称" class="form-control" autofocus id="mbname" name="mbname" ng-model="template.templateName" maxlength="220" />
				</div>
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">说明：</label> 
				<div class="col-sm-8"> 
					<textarea class="form-control" placeholder="说明" rows="3" autofocus id="shuoming" name="shuoming" ng-model="template.explain"></textarea>
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