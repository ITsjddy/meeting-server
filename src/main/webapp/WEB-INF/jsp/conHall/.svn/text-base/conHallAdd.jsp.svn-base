<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>会场添加页面</title>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/conHall/js/add.js"></script>
</head>
<body  ng-app="conHallApp" ng-controller="conHallController">
<ul class="breadcrumb"><li>会场添加</li></ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form"  ng-submit="addSave()">
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>基本信息</li>
			</ul>
			<input type="hidden" ng-model="conHall.language"/>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：</label> 
				<div class="col-sm-8"> 
					<input type="checkbox" checked disabled>中文&nbsp;
					<span ng-repeat="language in languageData">
						<input type="checkbox" ng-model="language.languageModel" ng-change="languageOnChange(language.languageModel,language.uneIdent,$index)" ng-checked="language.check"/>{{language.name}}&nbsp;
					</span>
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">会场类型：<font
							color="red">*</font></label>
				<div class="col-sm-3">
					<select class="form-control" name="type" required ng-model="conHall.type">
						<option value="">--请选择--</option>
						<option ng-repeat="conHallType in conHallTypeList" value="{{conHallType.dDLogo}}">{{conHallType.dDName}}</option>
					</select>
				</div>
				<label for="firstname" class="col-sm-2 control-label">会场面积：<font
							color="red">*</font></label>
				<div class="col-sm-3">
					<input type="text" placeholder="会场面积" class="form-control"
						required  ng-model="conHall.area" oninput="setCustomValidity('')" oninvalid="setCustomValidity('请为会场面积输入数值类型')" pattern="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$" />
				</div>
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">是否是室内导航：<font
							color="red">*</font></label>
				<div class="col-sm-3" ng-init="conHall.isIndoor='0'">
					是&nbsp;<input type="radio"  ng-model="conHall.isIndoor"
						value="1" checked="{{conHall.isIndoor=='1'}}" />&nbsp;&nbsp;
					否&nbsp;<input type="radio"  ng-model="conHall.isIndoor"
						value="0" checked="{{conHall.isIndoor=='0'}}" />
				</div>
  			</div>
  			<div class="form-group" ng-if="conHall.isIndoor==0">
						<label for="firstname" class="col-sm-2 control-label">会场经度：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场经度" class="form-control"
								required  ng-model="conHall.deskNumber" oninput="setCustomValidity('')" oninvalid="setCustomValidity('经度：要求经度整数部分为0-180小数部分为0到5位！')" maxlength="60" pattern="^(((\d|[1-9]\d|1[1-7]\d|0)\.\d{0,5})|(\d|[1-9]\d|1[1-7]\d|0{1,3})|180\.0{0,5}|180)$" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">会场纬度：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场纬度" class="form-control"
								required  ng-model="conHall.theatreNumber" oninput="setCustomValidity('')" oninvalid="setCustomValidity('纬度：要求纬度整数部分为0-90小数部分为0到5位！')" maxlength="60" pattern="^([0-8]?\d{1}\.\d{0,5}|90\.0{0,5}|[0-8]?\d{1}|90)$"  />
						</div>
			</div>
			<div class="form-group" ng-if="conHall.isIndoor==1">
						<label for="firstname" class="col-sm-2 control-label">室内导航唯一标示：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="室内导航唯一标示" class="form-control"
								required  ng-model="conHall.indoorUnique" pattern="^[^\u4e00-\u9fa5]{0,}$"
								oninput="setCustomValidity('')" oninvalid="setCustomValidity('不能填写中文')"
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
								required  ng-model="conHall.name" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">会场联系人：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人" class="form-control"
								required  ng-model="conHall.venueContact"
								maxlength="60" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">会场联系人电话：</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人电话" class="form-control"
								required ng-model="conHall.venueMobile" oninput="setCustomValidity('')" oninvalid="setCustomValidity('手机号格式不正确')"  pattern="^((\+?86)|(\(\+86\)))?1\d{10}$"/>
						</div>
					</div>
				</div>
				<!-- 其他语言信息 -->
				<div ng-repeat="conHallLan in conHallList">
					<div class="panel panel-default" id="guestDiv{{$index}}"
						ng-if="languageData[$index].check">
						<input type="hidden" ng-model="conHallLan.language" /> <input
							type="hidden" ng-model="conHallLan.check" />
						<ul class="breadcrumb">
							<li>{{languageData[$index].name}}信息</li>
							<li><a href="javaScript:void(0);"
								ng-click="delGuestDiv($index)"> <img
									src="${ctx }/images/minus.png" alt="minus" width="25px"
									height="25px" />
							</a></li>
						</ul>
						<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="conHall.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="conHall.language=='2'">name：<font color="red">*</font></div> -->
							会场名称({{languageData[$index].name}})：<font color="red">*</font>
						</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场名称({{languageData[$index].name}})" class="form-control"
								required  ng-model="conHallLan.name" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">会场联系人({{languageData[$index].name}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人({{languageData[$index].name}})" class="form-control"
								required  ng-model="conHallLan.venueContact"
								maxlength="60" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">会场联系人电话({{languageData[$index].name}})：</label>
						<div class="col-sm-3">
							<input type="text" placeholder="会场联系人电话({{languageData[$index].name}})" class="form-control"
								required ng-model="conHallLan.venueMobile" oninput="setCustomValidity('')" oninvalid="setCustomValidity('手机号格式不正确')"  pattern="^((\+?86)|(\(\+86\)))?1\d{10}$" />
						</div>
					</div>
					</div>
				</div>
				<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10"> 
					<input id="btnSubmit" class="btn btn-primary" type="submit"  value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
					</div>
				</div>
			</form>
	</div>
</div>
</body>
</html>