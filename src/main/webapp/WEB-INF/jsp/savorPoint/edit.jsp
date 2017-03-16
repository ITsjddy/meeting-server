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
		<script src="${ctx }/static/sysviews/savorPoint/js/edit.js"></script>
		<script src="${ctx }/static/angular-1.2.32/angular-messages.js"></script>
		<!-- 引入上传的css -->
		<link rel="stylesheet" href="${ctx }/css/upload/upload.css" type="text/css">
		<!-- 引入上传的js -->
		<script type="text/javascript" src="${ctx }/static/upload/js/swfobject.js"></script>
		<script type="text/javascript" src="${ctx }/static/upload/js/jquery.uploadify.v2.1.0.min.js"></script>
		<title>修改</title>
	</head>

	<body ng-app="addSavorPoint" ng-controller="spAddCtlr">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form"  enctype="multipart/form-data" name="form" ng-submit="addSavorPoint()">
					<div class="panel panel-default">
						<ul class="breadcrumb">
							<li>修改兴趣点</li>
						</ul>
						<hr/>
						<input type="hidden" value="${spUniteId}" id="spValue">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">唯一标识：</label>
							<div class="col-sm-4">
								<input class="form-control" id="spOnly" required oninput="setCustomValidity('');"  type="text" name="spOnly" ng-model="savorPoint.spOnly" ng-tlogos="spOnly"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">类型：</label>
							<div class="col-sm-4">
								<select ng-model="savorPoint.spType" required class="form-control">
									<option ng-repeat="spType in spTypes" value="{{spType.dDLogo}}">{{spType.dDName}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">经度：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="spLongitude" ng-model="savorPoint.spLongitude" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">维度：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" name="spDimensions" ng-model="savorPoint.spDimensions" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">Logo:</label>
							<div class="col-sm-4">
								<input type="file" id="uploadify" />
								<div id="fileQueue" style="width:300px; padding-right: 3px;">
									<input class="form-control" ng-model="savorPoint.spLogo" id="imaggo" type="hidden" />
									<span id="imagesTO">
										<img alt="" src="${strpath }{{savorPoint.spLogo}}" id="imgvo" width="70px" height="70px">
									</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文名称：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" required name="spName" ng-model="savorPoint.spName" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文地址：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" required name="spAddress" ng-model="savorPoint.spAddress" />
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
											<select class="form-control" id="topicType{{$index + 1}}" ng-model="topic.topicType" ng-change="fchat.topicOnChange({{$index+1}},topic.topicType)">
													<option value="">请选择</option>
													<option ng-repeat="type in topicTypesData" value="{{type.dDLogo}}" ng-selected="{{type.dDLogo==topic.topicType}}">{{type.dDName}}</option>
											</select>
										</div>
										<div class="col-md-2 img-link">
											<a href="" ng-click="fchat.incrTopic($index)" ng-show="fchat.canIncrTopic">
												<img src="${ctx }/images/plus.png" alt="plus" width="30px" height="30px" />
											</a>
											<img src="${ctx }/images/dis_plus.png" alt="minus" width="30px" height="30px" ng-show="!fchat.canIncrTopic" />
											<a href="" ng-click="fchat.decrTopic($index)" ng-show="fchat.canDescTopic">
												<img src="${ctx }/images/minus.png" alt="minus" width="30px" height="30px" />
											</a>
											<img src="${ctx }/images/dis_minus.png" alt="minus" width="30px" height="30px" ng-show="!fchat.canDescTopic" />
										</div>
									</div>
									<div id="optionsDiv{{topicMark+1}}" style="display: block;" ng-hide="topic.topicType==''">
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">名称：</label>
											<div class="col-sm-4">
												<input type="text" ng-if="topic.topicType.length > 0" required  class="form-control" name="topic{{topicMark+1}}Option{{$index + 1}}" ng-model="topic.spName" id="topic{{topicMark+1}}Option{{$index + 1}}" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">地址：</label>
											<div class="col-sm-4">
												<input type="text"  ng-if="topic.topicType.length > 0" required  class="form-control" ng-model="topic.spAddress" id="topic{{topicMark+11}}Option{{$index + 1}}" />
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
						&nbsp;&nbsp; &nbsp;&nbsp;
						<input class="btn btn-primary" type="submit" value="保存" />
					</div>
				</div>
				</form>
			</div>
		</div>
	</body>

</html>