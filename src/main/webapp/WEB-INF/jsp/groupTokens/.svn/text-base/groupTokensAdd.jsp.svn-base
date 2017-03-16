<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>论坛添加页面</title>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/groupTokens/js/add.js"></script>
</head>
<body  ng-app="groupTokensApp" ng-controller="groupTokensController">
 <div id="wrapper">
        <div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<fieldset>
					<legend>添加页面</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">姓名</label>
						<div class="col-sm-4">
							<input class="form-control" id="ds_host" type="text" name="name" ng-model="testDemo.name"
								placeholder="" />
						</div>
					</div>
					<div class="form-group">
						<label for="sexRadios" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-2">
							<div class="radio">
								<label> <input type="radio" name="sex" ng-model="testDemo.sex"
									id="optionsRadios1" value="0" checked="">男
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex" ng-model="testDemo.sex"
									id="optionsRadios1" value="1" checked="">女
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-4">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="testAddSave()" >保存</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
    </div>
</body>
</html>