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
		<script src="${ctx }/static/sysviews/dataDictionary/js/allDetails.js"></script>
		<script src="${ctx }/static/angular-1.2.32/angular-messages.js"></script>
		<title>操作</title>
	</head>

	<body ng-app="editDataDictionary" ng-controller="spAddCtlr">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form" name="form" ng-submit="editDataDictionary()">
					<div class="panel panel-default">
						<ul class="breadcrumb">
							<li>详情</li>
						</ul>
						<hr/>
						<input type="hidden" value="${id}" id="idValue">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文名称：</label>
							<div class="col-sm-4">
								<input class="form-control" id="dDName" disabled  oninput="setCustomValidity('');" type="text" name="dDName" ng-model="dataDictionary.dDName" ng-tames="dDName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label" for="ds_host">其他语言名称：</label>
							<div class="col-md-10">
								<div ng-repeat="topic in spAddressNames">
									<div class="form-group">
										<div ng-bind="topicMark=$index" style="display:none"></div>
										<div class="col-sm-3">
											<select class="form-control" id="topicType{{$index + 1}}" disabled ng-model="topic.topicType" ng-change="fchat.topicOnChange({{$index+1}},topic.topicType)">
													<option value="">请选择</option>
													<option ng-repeat="type in topicTypesData" value="{{type.dDLogo}}" ng-selected="{{type.id==topic.topicType}}">{{type.dDName}}</option>
											</select>
										</div>
									</div>
									<div id="optionsDiv{{topicMark+1}}" style="display: block;" ng-hide="topic.topicType==''">
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">名称：</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" ng-if="topic.topicType.length > 0" id="{{topic.topicType}}" disabled  autofocus oninput="setCustomValidity('');" name="{{topic.topicType}}" ng-model="topic.dDName"  ng-tames="{{topic.topicType}}"/>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">唯一标识：</label>
							<div class="col-sm-4">
								<input class="form-control" id="dDLogo" disabled  autofocus oninput="setCustomValidity('');" type="text" name="dDLogo" ng-model="dataDictionary.dDLogo" ng-tlogos="dDLogo"/>
							</div>
						</div>
							<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">排序标识：</label>
							<div class="col-sm-4">
								<input class="form-control" id="dDNum" disabled type="number" name="dDNum" ng-model="dataDictionary.dDNum"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">类型：</label>
							<div class="col-sm-4">
								<input class="form-control" disabled  type="text" name="dDType" ng-model="dataDictionary.dDType" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">说明：</label>
							<div class="col-sm-4">
								<textarea class="required" disabled autofocus ng-model="dataDictionary.dDexplanation" name="dDexplanation" rows="4" cols="1000" 
											style="resize:none;width:400px;" ></textarea>
							</div>
						</div>
					</div>
				<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
						&nbsp;&nbsp; &nbsp;&nbsp;
					</div>
			   </div>
				</form>
			</div>
		</div>
	</body>

</html>