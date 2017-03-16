<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>修改公共任务</title>
<script src="${ctx }/static/sysviews/commonTask/js/edit.js"></script>
</head>
<body ng-app="commonTaskApp" ng-controller="commonTaskController" style="width: 100%; height: 100%; margin: 10px">
	<ul class="breadcrumb">
		<li>修改公共任务</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" novalidate="novalidate"
				name="form" ng-submit="commonTaskEditSave()">
				<input type="hidden" value="${id }" id="id">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">名称:<font color="red">*</font></label>
						<div class="col-sm-4">
							<input class="form-control" type="text" name="name" required
								ng-model="commonTask.name" placeholder="" maxlength="500"/>
							<span style="color: red" class="error" ng-show="form.$dirty && form.name.$error.required">名称不能为空</span>
						</div>
					</div>
					<div class="form-group" has-feedback" ng-class="{true:'has-success',false:'has-error'}[form.uniqueCode.$valid]">  
						<label class="col-sm-2 control-label">唯一标识:<font color="red">*</font></label>
						<div class="col-sm-4">
							<input class="form-control" type="text" name="uniqueCode" required
								ng-model="commonTask.uniqueCode" placeholder="" maxlength="500" vaild-unique-code/>
							<span style="color: red" class="error" ng-show="form.$dirty && form.uniqueCode.$error.required">唯一标识不能为空</span>
							<div id="uniqueCodeError"></div>
						</div>
						<div class="col-sm-4">
							<div style="color: red;">*{唯一标识：只能是公共任务名称的汉语拼音全拼}</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">排序:<font color="red">*</font></label>
						<div class="col-sm-4">
							<input class="form-control" type="number" name="sort" required
								ng-model="commonTask.sort" placeholder="" maxlength="100"/>
							<span style="color: red" class="error" ng-show="form.$dirty && form.sort.$error.required">排序不能为空</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">描述信息</label>
						<div class="col-sm-4">
							<textarea id="remark" class="form-control" ng-model="commonTask.remark" placeholder=""
								style="margin:0px;height:88px;" maxlength="1500"></textarea>
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