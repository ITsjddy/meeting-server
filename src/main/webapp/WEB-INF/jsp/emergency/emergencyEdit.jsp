<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>应急管理修改</title>
<script src="${ctx }/static/sysviews/emergency/js/edit.js"></script>
<!-- 引入上传的css -->
<link rel="stylesheet" href="${ctx }/css/upload/upload.css" type="text/css">
<!-- 引入上传的js -->
<script type="text/javascript" src="${ctx }/static/upload/js/swfobject.js"></script>
<script type="text/javascript" src="${ctx }/static/upload/js/jquery.uploadify.v2.1.0.min.js"></script>
</head>
<body ng-app="emergencyApp" ng-controller="emergencyController" style="width: 100%; height: 100%; margin: 10px">
	<ul class="breadcrumb">
		<li>修改页面</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" 
				name="form" ng-submit="emergencyEditSave()">
				<input type="hidden" value="${id }" id="id">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">应急电话</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" id="tel" name="tel" required  autofocus oninput="setCustomValidity('');"
								ng-model="emergency.emergencyTel" ng-tlogos="tel"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">应急类型</label>
						<div class="col-sm-2">
						  <select class="form-control" ng-model="emergency.emergencyType" required>
						        <option value="">请选择</option>
						        <option ng-repeat="type in emergencyTypesData" value="{{type.id}}" selected="{{emergency.emergencyType==type.id}}">{{type.content}}</option>     
						    </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">应急标识</label>
						<div class="col-sm-2">
							<table style="width: 90%;">
								<tr>
									<td style="width: 100px;">
										<input type="file" id="uploadify" />
									</td>
									<td align="left">
										<a href="javascript:$('#uploadify').uploadifyUpload()">上传</a> &nbsp;&nbsp; <a href id='Xdel' onClick='delimg();'>清空</a>
									</td>
								</tr>
							</table>
								<div id="fileQueue" style="width:300px; padding-right: 3px;">
									<input class="form-control" ng-model="emergency.emergencyPic" id="imaggo" type="hidden" />
									<span id="imagesTO">
										<c:if test="${not empty id }">
											<img alt="" src="${strpath }{{emergency.emergencyPic}}" id='imgvo' width='70px' height='70px'>
										</c:if>
									</span>
								</div>
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