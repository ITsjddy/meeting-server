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
<script src="${ctx }/static/angular-1.2.32/angular-messages.js"></script>
<!-- 引入上传的css -->
<link rel="stylesheet" href="${ctx }/css/upload/upload.css" type="text/css">
<!-- 引入上传的js -->
<script type="text/javascript" src="${ctx }/static/upload/js/swfobject.js"></script>
<script type="text/javascript" src="${ctx }/static/upload/js/jquery.uploadify.v2.1.0.min.js"></script>
</head>
<body ng-app="appVersionApp" ng-controller="appVersionController" >
	<ul class="breadcrumb">
		<li>添加页面</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form"  enctype="multipart/form-data" 
				name="form" ng-submit="appVersionAddSave()"  >
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">手机类型:</label>
						<div class="col-sm-3">
							<select name="type" id = "fileNeed" required  autofocus class="form-control" ng-model="appVersion.type" ng-change="changeAppVersionType()">
								<option value="">请选择</option>
								<option value="1">Android</option>
								<option value="2">IOS</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">版本名称:</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="versionName" ng-model="appVersion.versionName"
							 ng-maxlength=12 required  autofocus />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">版本号:</label>
						<div class="col-sm-3">
							<input readonly="readonly" class="form-control" type="text" name="versionNum" ng-model="appVersion.versionNum" placeholder=""  required  autofocus/>
						</div>
					</div>
					<div id="apkDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">上传安装包:</label>
						<div class="col-sm-3">
							<table style="width: 90%;">
								<tr>
									<td style="width: 100px;">
										<input type="file" id="APK_path" />
									</td>
									<td align="left">
										<a href="javascript:$('#APK_path').uploadifyUpload()">上传</a>
									</td>
								</tr>
							</table>
							<div id="ApkFileQueue" style="width:300px; padding-right: 3px;">
								<input class="form-control" ng-model="appVersion.path" id="pathURL" type="hidden" />
								<span id="imageApk"></span>
							</div>
						</div>
					</div>
					<div id="ipaDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">上传安装包:</label>
						<div class="col-sm-3">
							<table style="width: 90%;">
								<tr>
									<td style="width: 100px;">
										<input type="file" id="IPA_path" />
									</td>
									<td align="left">
										<a href="javascript:$('#IPA_path').uploadifyUpload()">上传</a>
									</td>
								</tr>
							</table>
							<div id="IpaFileQueue" style="width:300px; padding-right: 3px;">
								<input class="form-control" ng-model="appVersion.path" id="pathURL" type="hidden" />
								<span id="imageIpa"></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">下载地址二维码:</label>
						<div class="col-sm-3">
							<table style="width: 90%;">
								<tr>
									<td style="width: 100px;">
										<input type="file" id="qrCode" />
									</td>
									<td align="left">
										<a href="javascript:$('#qrCode').uploadifyUpload()">上传</a>
									</td>
								</tr>
							</table>
							<div id="qrCodeFileQueue" style="width:300px; padding-right: 3px;">
								<input class="form-control" ng-model="appVersion.qrCode" id="qrCodeURL" type="hidden" />
								<span id="imageQrCode"></span>
							</div>
						</div>
					</div>
					<div id="plistDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载plsit文件:</label>
						<div class="col-sm-3">
							<table style="width: 90%;">
								<tr>
									<td style="width: 100px;">
										<input type="file" id="plist" />
									</td>
									<td align="left">
										<a href="javascript:$('#plist').uploadifyUpload()">上传</a>
									</td>
								</tr>
							</table>
							<div id="plistFileQueue" style="width:300px; padding-right: 3px;">
								<input class="form-control" ng-model="appVersion.plist" id="plistURL" type="hidden" />
								<span id="imagePlist"></span>
							</div>
						</div>
					</div>
					<div id="picOneDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载显示图片（大）:</label>
						<div class="col-sm-3">
							<table style="width: 90%;">
								<tr>
									<td style="width: 100px;">
										<input type="file" id="picOne" />
									</td>
									<td align="left">
										<a href="javascript:$('#picOne').uploadifyUpload()">上传</a>
									</td>
								</tr>
							</table>
							<div id="picOneFileQueue" style="width:300px; padding-right: 3px;">
								<input class="form-control" ng-model="appVersion.picOne" id="picOneURL" type="hidden" />
								<span id="imagePicOne"></span>
							</div>
						</div>
					</div>
					<div id="picTwoDiv" style="display: none;" class="form-group">
						<label class="col-sm-2 control-label">ios下载显示图片（小）:</label>
						<div class="col-sm-3">
							<table style="width: 90%;">
								<tr>
									<td style="width: 100px;">
										<input type="file" id="picTwo" />
									</td>
									<td align="left">
										<a href="javascript:$('#picTwo').uploadifyUpload()">上传</a>
									</td>
								</tr>
							</table>
							<div id="picTwoFileQueue" style="width:300px; padding-right: 3px;">
								<input class="form-control" ng-model="appVersion.picTwo" id="picTwoURL" type="hidden" />
								<span id="imagePicTwo"></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">更新描述:</label>
						<div class="col-sm-3">
							<textarea id="remark" class="form-control" ng-model="appVersion.remark" placeholder=""
								style="margin:0px;height:88px;" maxlength="1500"></textarea>
						</div>
					</div>
					<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
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