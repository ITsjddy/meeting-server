<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>App版本修改</title>
<script src="${ctx }/static/sysviews/appVersion/js/details.js"></script>
<script src="${ctx }/static/angular-1.2.32/angular-messages.js"></script>
</head>
<body ng-app="appVersionApp" ng-controller="appVersionController" style="width: 100%; height: 100%; margin: 10px">
	<ul class="breadcrumb">
		<li>详情</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" name="form" enctype="multipart/form-data" ng-submit="appVersionEditSave()">
				<input type="hidden" value="${id }" id="id">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机类型:</label>
						<div class="col-sm-3">
							<select name="type" disabled class="form-control" ng-model="appVersion.type" ng-change="changeAppVersionType()">
								<option value="">请选择</option>
								<option value="1">Android</option>
								<option value="2">IOS</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">版本名称:</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="versionName" ng-model="appVersion.versionName"  disabled/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">版本号:</label>
						<div class="col-sm-3">
							<input readonly="readonly" class="form-control" type="text" name="versionNum" 
							 ng-model="appVersion.versionNum" placeholder=""  disabled/>
						</div>
					</div>
					<div id="apkDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">上传安装包:</label>
						<div class="col-sm-3">
							<input type="file" name="path" disabled class="form-control" accept=".APK,.apk" file-model="appVersion.path" />
						</div>
					</div>
					<div id="ipaDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">上传安装包:</label>
						<div class="col-sm-3">
							<input type="file" name="path" disabled class="form-control" accept=".IPA,.ipa" file-model="appVersion.path" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">下载地址二维码:</label>
						<div class="col-sm-3">
							<input type="file" class="form-control" disabled name="qrCode" file-model="appVersion.qrCode" accept=".png,.jpg,.jpeg,.PNG,.JPG,.JPEG"/>
							<img ng-src="${ctx}{{appVersion.qr_code}}" style="width:100px; height:100px;"/>
						</div>
					</div>
					<div id="plistDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载plsit文件:</label>
						<div class="col-sm-3">
							<input type="file" class="form-control" disabled name="plist" file-model="appVersion.plist" accept=".list,.LIST"/>
						</div>
					</div>
					<div id="picOneDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载显示图片（大）:</label>
						<div class="col-sm-3">
							<input type="file"class="form-control"  disabled name="picOne" file-model="appVersion.picOne" accept=".png,.jpg,.jpeg,.PNG,.JPG,.JPEG"/>
							<img ng-src="${ctx}{{appVersion.picOne}}" style="width:100px; height:100px;"/>
						</div>
					</div>
					<div id="picTwoDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载显示图片（小）:</label>
						<div class="col-sm-3">
							<input type="file" class="form-control" disabled name="picTwo" file-model="appVersion.picTwo" accept=".png,.jpg,.jpeg,.PNG,.JPG,.JPEG"/>
							<img src="${ctx}{{appVersion.picTwo}}" style="width:100px; height:100px;"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">更新描述:</label>
						<div class="col-sm-3">
							<textarea id="remark" class="form-control" disabled ng-model="appVersion.remark" placeholder=""
								style="margin:0px;height:88px;" maxlength="1500"></textarea>
						</div>
					</div>
					<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>