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
		<script src="${ctx }/static/sysviews/savorPoint/js/details.js"></script>
		<script src="${ctx }/static/angular-1.2.32/angular-messages.js"></script>
		<title>修改</title>
	</head>

	<body ng-app="addSavorPoint" ng-controller="spAddCtlr">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form"  enctype="multipart/form-data" name="form" ng-submit="addSavorPoint()">
					<div class="panel panel-default">
						<ul class="breadcrumb">
							<li>详情</li>
						</ul>
						<hr/>
						<input type="hidden" value="${spUniteId}" id="spValue">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">唯一标识：</label>
							<div class="col-sm-4">
								<input class="form-control" id="spOnly" disabled oninput="setCustomValidity('');"  type="text" name="spOnly" ng-model="savorPoint.spOnly" ng-tlogos="spOnly"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">类型：</label>
							<div class="col-sm-4">
								<select ng-model="savorPoint.spType" disabled class="form-control">
									<option ng-repeat="spType in spTypes" value="{{spType.dDLogo}}">{{spType.dDName}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">经度：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" disabled name="spLongitude" ng-model="savorPoint.spLongitude" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">维度：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" disabled name="spDimensions" ng-model="savorPoint.spDimensions" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">Logo:</label>
							<div class="col-sm-4">
								<input type="file" name="small_icon" disabled class="form-control" file-model="savorPoint.spLogo" accept=".png,.jpg,.jpeg,.PNG,.JPG,.JPEG"/>
								<img ng-src="${ctx}{{savorPoint.spLogo}}" style="width:100px; height:100px;"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文名称：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" disabled name="spName" ng-model="savorPoint.spName" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文地址：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" disabled name="spAddress" ng-model="savorPoint.spAddress" />
							</div>
						</div>
						<hr/>
						<div class="form-group">
							<label class="col-md-2 control-label" for="ds_host">其他语言：</label>
							<div class="col-md-10">
								<div ng-repeat="topic in spAddressNames">
									<div class="form-group">
										<div ng-bind="topicMark=$index" style="display:none"></div>
										<div class="col-sm-3">
											<select class="form-control" disabled id="topicType{{$index + 1}}" ng-model="topic.topicType" ng-change="fchat.topicOnChange({{$index+1}},topic.topicType)">
													<option value="">请选择</option>
													<option ng-repeat="type in topicTypesData" value="{{type.dDLogo}}" ng-selected="{{type.dDLogo==topic.topicType}}">{{type.dDName}}</option>
											</select>
										</div>
									</div>
									<div id="optionsDiv{{topicMark+1}}" style="display: block;" ng-hide="topic.topicType==''">
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">名称：</label>
											<div class="col-sm-4">
												<input type="text" ng-if="topic.topicType.length > 0" disabled  class="form-control" name="topic{{topicMark+1}}Option{{$index + 1}}" ng-model="topic.spName" id="topic{{topicMark+1}}Option{{$index + 1}}" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">地址：</label>
											<div class="col-sm-4">
												<input type="text"  ng-if="topic.topicType.length > 0" disabled  class="form-control" ng-model="topic.spAddress" id="topic{{topicMark+11}}Option{{$index + 1}}" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<hr/>
					</div>
					<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>

</html>