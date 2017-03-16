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
		<script src="${ctx }/static/sysviews/dataDictionary/js/allAdd.js"></script>
		<script src="${ctx }/static/angular-1.2.32/angular-messages.js"></script>
		<title>操作</title>
	</head>

	<body ng-app="addDataDictionary" ng-controller="spAddCtlr">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form" name="form" ng-submit="addDataDictionary()">
					<div class="panel panel-default">
						<ul class="breadcrumb">
							<li>添加</li>
						</ul>
						<hr/>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">类型：</label>
							<div class="col-sm-4">
								<select class="form-control"  required name="dDType" ng-model="dataDictionary.dDTypeLogo" ng-change="changeTypeLogo(dataDictionary.dDTypeLogo)">
										<option value="" > 请选择类型 </option>
										<option ng-repeat="type in findAllType" value="{{type.dTypeLogo}}" >{{type.dTypeName}}</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文名称：</label>
							<div class="col-sm-4">
								<input class="form-control" id="dDName" required  autofocus oninput="setCustomValidity('');" type="text" name="dDName" ng-model="dataDictionary.dDName" ng-tames="dDName"/>
							</div>
						</div>
						<div class="form-group" id="languageDiv" style="display: none;">
							<label class="col-md-2 control-label" for="ds_host">其他语言名称：</label>
							<div class="col-md-10">
								<div ng-repeat="topic in spAddressNames">
									<div class="form-group">
										<div ng-bind="topicMark=$index" style="display:none"></div>
										<div class="col-sm-3">
											<select class="form-control" id="topicType{{$index + 1}}" ng-model="topic.topicType" ng-change="fchat.topicOnChange({{$index+1}},topic.topicType)">
													<option value="">请选择</option>
													<option ng-repeat="type in topicTypesData" value="{{type.dDLogo}}" >{{type.dDName}}</option>
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
									<div id="optionsDiv{{topicMark+1}}" style="display: none;">
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">名称：</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" ng-if="topic.topicType.length > 0" id="{{topic.topicType}}" required  autofocus oninput="setCustomValidity('');" name="{{topic.topicType}}" ng-model="topic.dDName"  ng-tames="{{topic.topicType}}"/>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">唯一标识：</label>
							<div class="col-sm-4">
								<input class="form-control" id="dDLogo" required  autofocus oninput="setCustomValidity('');" type="text" name="dDLogo" ng-model="dataDictionary.dDLogo" ng-tlogos="dDLogo"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">排序标识：</label>
							<div class="col-sm-4">
								<input class="form-control" id="dDNum" required  autofocus type="number" name="dDNum" ng-model="dataDictionary.dDNum"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">说明：</label>
							<div class="col-sm-4">
								<textarea class="required" required autofocus ng-model="dataDictionary.dDexplanation" name="dDexplanation" rows="4" cols="1000" 
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