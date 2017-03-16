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
		<script src="${ctx }/static/sysviews/cloudGroup/js/details.js"></script>
		<title>操作</title>
	</head>

	<body ng-app="detailscloudGroup" ng-controller="detailsCller">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form">
					<div class="panel panel-default">
									<ul class="breadcrumb">
										<li>详情</li>
									</ul>
						<input type="hidden" value="${id}" id="spValue">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">群组名称：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" ng-model="cloudGroup.fgName" maxlength="200" disabled>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">服务人员：</label>
							<div class="radio col-sm-3">
								<textarea id="insiderNames" disabled ng-model="cloudGroup.fgServPersonNames" rows="5" style="background-color:#FEFFD6;cursor:pointer;width:550px;"></textarea>
								<input type="hidden" ng-model="cloudGroup.insiderIds" /> 
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">APP用户：</label>
							<div class="radio col-sm-3">
								<textarea ng-model="cloudGroup.fgAppUserNames" disabled id="outNames" rows="5" style="background-color:#FEFFD6;cursor:pointer;width:550px;"></textarea>
								<input type="hidden" ng-model="cloudGroup.outsiderIds" /> 
							</div>
						</div>
					</div>
				</form>
				<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10">
						<a class="btn btn-success" style="width: 160px;" ng-click="pageBackButton()">返回</a>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>