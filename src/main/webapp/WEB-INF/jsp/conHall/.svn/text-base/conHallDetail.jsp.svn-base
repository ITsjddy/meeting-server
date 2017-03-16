<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>会场详情页面</title>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/conHall/js/detail.js"></script>
</head>
<body  ng-app="conHallApp" ng-controller="conHallController">
<ul class="breadcrumb"><li>会场详情</li></ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form">
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>基本信息</li>
			</ul>
			<input id="uniqueCode" name="uniqueCode" type="hidden" value="${uniqueCode}" />
			<input id="id" name="id" type="hidden" value="${id}" />
			<input type="hidden" ng-model="conHall.language"/>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：</label> 
				<div class="col-sm-8"> 
					<input type="checkbox" checked disabled>中文&nbsp;
					<span ng-repeat="language in languageData">
						<input type="checkbox" ng-model="language$index" disabled ng-change="languageOnChange(language$index,language.uneIdent,$index)" ng-checked="language.check"/>{{language.name}}&nbsp;
					</span>
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">会场类型：</label>
				<div class="col-sm-3">
					<select class="form-control" name="type" disabled ng-model="conHall.type">
						<option value="">--请选择--</option>
						<option ng-repeat="conHallType in conHallTypeList" value="{{conHallType.dDLogo}}">{{conHallType.dDName}}</option>
					</select>
				</div>
				<label for="firstname" class="col-sm-2 control-label">会场面积：<font
							color="red">*</font></label>
				<div class="col-sm-3">
					<input type="text" placeholder="会场面积" class="form-control"
						required autofocus ng-model="conHall.area" maxlength="60" disabled />
				</div>
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">是否是室内导航：</label>
				<div class="col-sm-3" ng-init="conHall.isIndoor='0'">
					是&nbsp;<input type="radio" disabled autofocus ng-model="conHall.isIndoor"
						value="1" checked="{{conHall.isIndoor=='1'}}" />&nbsp;&nbsp;
					否&nbsp;<input type="radio" disabled autofocus ng-model="conHall.isIndoor"
						value="0" checked="{{conHall.isIndoor=='0'}}" />
				</div>
  			</div>
  			<div class="form-group" ng-show="conHall.isIndoor==0">
						<label for="firstname" class="col-sm-2 control-label">会场经度：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场经度" class="form-control"
								required autofocus disabled ng-model="conHall.deskNumber" maxlength="60" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">会场纬度：</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场纬度" class="form-control"
								required autofocus disabled ng-model="conHall.theatreNumber" />
						</div>
			</div>
			<div class="form-group" ng-show="conHall.isIndoor==1">
						<label for="firstname" class="col-sm-2 control-label">室内导航唯一标示：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="室内导航唯一标示" class="form-control"
								required autofocus disabled ng-model="conHall.indoorUnique"
								maxlength="60" />
						</div>
			</div>
			</div>
  			<!-- 中文信息 -->
				<div class="panel panel-default">
					<ul class="breadcrumb">
						<li>中文信息</li>
					</ul>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							会场名称：<font color="red">*</font>
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场名称" class="form-control"
								required autofocus disabled ng-model="conHall.name" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">会场联系人：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人" class="form-control"
								required autofocus disabled ng-model="conHall.venueContact"
								maxlength="60" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">会场联系人电话：</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人电话" class="form-control" disabled
								ng-model="conHall.venueMobile" />
						</div>
					</div>
				</div>
				<!-- 其他语言信息 -->
				<div ng-repeat="conHallLan in conHallList">
					<div class="panel panel-default" id="guestDiv{{$index}}"
						ng-show="languageData[$index].check">
						<input type="hidden" ng-model="conHallLan.language" /> <input
							type="hidden" ng-model="conHallLan.check" />
						<ul class="breadcrumb">
							<li>{{languageData[$index].name}}信息</li>
							<li><a href="javaScript:void(0);"> <img
									src="${ctx }/images/minus.png" alt="minus" width="25px"
									height="25px" disabled />
							</a></li>
						</ul>
						<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							会场名称({{languageData[$index].name}})：<font color="red">*</font>
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场名称({{languageData[$index].name}})" class="form-control"
								required autofocus disabled ng-model="conHallLan.name" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">会场联系人({{languageData[$index].name}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人({{languageData[$index].name}})" class="form-control"
								required autofocus disabled ng-model="conHallLan.venueContact"
								maxlength="60" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">会场联系人电话({{languageData[$index].name}})：</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人电话({{languageData[$index].name}})" class="form-control" disabled
								ng-model="conHallLan.venueMobile" />
						</div>
					</div>
					</div>
				</div>
			</form>
	</div>
</div>
</body>
</html>