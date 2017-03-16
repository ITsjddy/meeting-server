<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html style="overflow-x: hidden; overflow-y: auto;">

	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta name="author" content="ms" />
		<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />
		<script src="${ctx }/static/sysviews/systemSetting/js/add.js"></script>
		<script src="${ctx }/static/angular-1.2.32/angular-messages.js"></script>
		<title>操作</title>
	</head>

	<body ng-app="addSystemSetting" ng-controller="addSystemSettingCtlr" >
		<div id="wrapper">
			<div id="page-wrapper">
			<form class="form-horizontal" role="form" name="form" ng-submit="addSystemSetting()">
					<div class="panel panel-default">
							<c:choose>
								<c:when test="${empty id}">
									<ul class="breadcrumb">
										<li>添加</li>
									</ul>
								</c:when>
								<c:otherwise>
									<ul class="breadcrumb">
										<li>修改</li>
									</ul>
								</c:otherwise>
							</c:choose>
						<input type="hidden" value="${id}" id="spValue">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">唯一标识：</label>
							<div class="col-sm-4">
								<input class="form-control" id="uniqueId" required  autofocus oninput="setCustomValidity('');"  type="text" name="uniqueId" ng-model="systemSetting.uniqueId"  ng-tlogos="uniqueId"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">状态：</label>
							<div class="col-sm-4">
								<select class="form-control"  required  name="status" ng-model="systemSetting.status" >
										<option  value="" >请选择</option>
										<option ng-repeat="type in statusList" value="{{type.id}}" >{{type.text}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">内容：</label>
							<div class="col-sm-4">
								<textarea class="required" required autofocus ng-model="systemSetting.content" name="content" rows="4" cols="1000" 
											style="resize:none;width:400px;" ></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">说明：</label>
							<div class="col-sm-4">
								<textarea class="required"  ng-model="systemSetting.description" name="description" rows="4" cols="1000" 
											style="resize:none;width:400px;" ></textarea>
							</div>
						</div>
					</div>
					<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
							&nbsp;&nbsp; &nbsp;&nbsp;
							<input class="btn btn-primary" type="submit" value="保存" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>

</html>