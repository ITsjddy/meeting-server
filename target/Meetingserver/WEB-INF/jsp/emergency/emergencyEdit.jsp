<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>应急管理修改</title>
<script src="${ctx }/static/sysviews/emergency/js/edit.js"></script>
</head>
<body ng-app="emergencyApp" ng-controller="emergencyController" style="width: 100%; height: 100%; margin: 10px">
	<ul class="breadcrumb">
		<li>修改页面</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" novalidate="novalidate"
				name="form" ng-submit="emergencyEditSave()">
				<input type="hidden" value="${id }" id="id">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">应急电话</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="tel" ng-model="emergency.emergencyTel" placeholder=""  required
								ng-pattern="/^(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/" />
							<span style="color: red" class="error" ng-show="form.$dirty && form.tel.$invalid">填写不正确</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">应急类型</label>
						<div class="col-sm-2">
						    <select ng-model="emergency.emergencyType">
						        <option value="">请选择</option>
						        <option ng-repeat="type in emergencyTypesData" value="{{type.id}}" selected="{{emergency.emergencyType==type.id}}">{{type.content}}</option>     
						    </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">应急标识</label>
						<div class="col-sm-4">
							<img ng-src="${ctx }{{emergency.emergencyPic}}"><br>
							<input type="file" name="emergencyPic" file-model="emergency.emergencyPic">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
							<input class="btn btn-primary" type="submit" value="保存" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>