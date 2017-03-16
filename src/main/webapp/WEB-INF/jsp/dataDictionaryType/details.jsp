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
		<script src="${ctx }/static/sysviews/dataDictionaryType/js/details.js"></script>
		<title>操作</title>
	</head>

	<body ng-app="detailsdDType" ng-controller="detailsqwCller">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form">
					<div class="panel panel-default">
									<ul class="breadcrumb">
										<li>详情</li>
									</ul>
						<input type="hidden" value="${id}" id="spValue">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">名称：</label>
							<div class="col-sm-4">
								<input class="form-control"  type="text" disabled ng-model="dateType.dtypeName" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">唯一标识：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text"   disabled ng-model="dateType.dtypeLogo"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">说明：</label>
							<div class="col-sm-4">
								<textarea class="required" disabled ng-model="dateType.dtypeExplanation"  rows="4" cols="1000" 
											style="resize:none;width:400px;" ></textarea>
							</div>
						</div>
					</div>
				</form>
				<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10">
						<a class="btn btn-primary" style="width: 160px;" ng-click="pageBackButton()">返回</a>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>