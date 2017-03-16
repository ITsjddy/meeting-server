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
		<script src="${ctx }/static/sysviews/savorPoint/addAndEditPage.js"></script>

		<title>操作</title>

		<body ng-app="addSavorPoint" ng-controller="spAddCtlr">
			<div id="wrapper">
				<div id="page-wrapper">
					<form class="form-horizontal" role="form">
						<fieldset>
							<legend>
								<c:choose>
									<c:when test="${empty id}">
										添加兴趣点
									</c:when>
									<c:otherwise>
										修改兴趣点
									</c:otherwise>
								</c:choose>
							</legend>
							<input type="hidden" value="${id}" id="spValue">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">唯一标识：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spOnly" ng-model="savorPoint.spOnly" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">中文名称：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spZhName" ng-model="savorPoint.spZhName" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">英文名称：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spEnName" ng-model="savorPoint.spEnName" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">中文地址：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spZhAddress" ng-model="savorPoint.spZhAddress" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">英文地址：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spEnAddress" ng-model="savorPoint.spEnAddress" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">房间类型：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spRoomType" ng-model="savorPoint.spRoomType" />
								</div>
								<!-- 	<label  class=" control-label" style="font-size:12px;color:red;">{}</label> -->
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">房间总数：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spRoomCount" ng-model="savorPoint.spRoomCount" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">已使用：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spRoomUseCount" ng-model="savorPoint.spRoomUseCount" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">类型：</label>
								<div class="col-sm-4">
									<select ng-model="savorPoint.spType" class="form-control">
								<option ng-repeat="spType in spTypes" value="{{spType.age}}">{{spType.name}}</option>
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
								<label class="col-sm-2 control-label" for="ds_host">工作人员房间数：</label>
								<div class="col-sm-4">
									<input class="form-control" type="text" name="spRoomJobsCount" ng-model="savorPoint.spRoomJobsCount" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">logo:</label>
								<div class="col-sm-4">
									<input class="form-control" type='file' style="width: 430px;" name="spLogo" ng-model="savorPoint.spLogo" />
								</div>
								<label class=" control-label" style="font-size:12px;color:red;">{照片分辨率必须为：440
								X 320;不能超过300KB;}</label>
							</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10"> 
						<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
						<a href="javaScript:void(0)" class="btn btn-primary" ng-click="addSavorPoint()">保存</a>
					</div>
				</div>
				</fieldset>
				</form>
			</div>
			</div>
		</body>

</html>