<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>tableTest</title>
	<script src="${ctx }/static/sysviews/test/js/edit.js"></script>
</head>
<body ng-app="testApp" ng-controller="testController">
  <div id="wrapper">
        <div id="page-wrapper">
			<form class="form-horizontal" role="form">
			<input type="hidden" value="${id }" id="testid">
				<fieldset>
					<legend>详情/修改页面</legend>
					<fieldset>
					<legend>添加页面</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">姓名</label>
						<div class="col-sm-4">
							<input class="form-control" id="ds_host" type="text" name="name" value="" ng-model="testDemo.name"
								placeholder="" />
						</div>
					</div>
					<div class="form-group">
						<label for="sexRadios" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-2">
							<div class="radio">
								<label> 
									<input type="radio" name="sex" ng-model="testDemo.sex"id="optionsRadios1" value="0" checked="{{testDemo.sex=='0'}}" >男
								</label>
							</div>
							<div class="radio">
								<label> 
									<input type="radio" name="sex"  ng-model="testDemo.sex"id="optionsRadios1" value="1" checked="{{testDemo.sex=='0'}}">女
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="testEditSave()" >保存</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
    </div>
</body>
</html>