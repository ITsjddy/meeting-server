<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>App版本添加</title>
<!-- 模块js -->
<script src="${ctx }/static/sysviews/appVersion/js/add.js"></script>
</head>
<body ng-app="appVersionApp" ng-controller="appVersionController" style="width: 100%; height: 100%; margin: 10px">
	<ul class="breadcrumb">
		<li>添加页面</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" novalidate="novalidate" enctype="multipart/form-data" 
				name="form" ng-submit="appVersionAddSave()">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机类型:</label>
						<div class="col-sm-2">
							<select name="type" ng-model="appVersion.type" ng-change="changeAppVersionType()">
								<option value="">请选择</option>
								<option value="1">Android</option>
								<option value="2">IOS</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">版本名称:</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="versionName" ng-model="appVersion.versionName" placeholder=""  required/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">版本号:</label>
						<div class="col-sm-2">
							<input readonly="readonly" class="form-control" type="text" name="versionNum" ng-model="appVersion.versionNum" placeholder=""  required/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">上传安装包:</label>
						<div class="col-sm-2">
							<input type="file" name="path" file-model="appVersion.path" ng-pattern="/.apk|.ipa/i/">
							<span style="color: red" class="error" ng-show="form.$dirty && form.path.$invalid">上传文件必须匹配: .apk 或 .ipa !</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">下载地址二维码:</label>
						<div class="col-sm-2">
							<input type="file" name="qrCode" file-model="appVersion.qrCode" ng-pattern="/.png/i">
							<span style="color: red" class="error" ng-show="form.$dirty && form.qrCode.$invalid">上传文件必须为png图片格式</span>
						</div>
					</div>
					<div id="plistDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载plsit文件:</label>
						<div class="col-sm-2">
							<input type="file" name="plist" file-model="appVersion.plist" ng-pattern="/.plist/i">
							<span style="color: red" class="error" ng-show="form.$dirty && form.plist.$invalid">上传文件必须为plist格式</span>
						</div>
					</div>
					<div id="picOneDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载显示图片（大）:</label>
						<div class="col-sm-2">
							<input type="file" name="picOne" file-model="appVersion.picOne" ng-pattern="/.png/i">
							<span style="color: red" class="error" ng-show="form.$dirty && form.picOne.$invalid">上传文件必须为png图片格式</span>
						</div>
					</div>
					<div id="picTwoDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载显示图片（小）:</label>
						<div class="col-sm-2">
							<input type="file" name="picTwo" file-model="appVersion.picTwo" ng-pattern="/.png/i">
							<span style="color: red" class="error" ng-show="form.$dirty && form.picTwo.$invalid">上传文件必须为png图片格式</span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">更新描述:</label>
						<div class="col-sm-2">
							<textarea id="remark" class="form-control" ng-model="appVersion.remark" placeholder=""
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