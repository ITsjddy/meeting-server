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
		<script src="${ctx }/static/sysviews/smsTemplate/js/addEdit.js"></script>

		<title>操作</title>
	</head>

	<body ng-app="addEditPage" ng-controller="sptAECtlr" novalidate>
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form" name="myForm">
					<div class="panel panel-default">
							<c:choose>
								<c:when test="${empty id}">
									<ul class="breadcrumb">
										<li>短信模版添加</li>
									</ul>
								</c:when>
								<c:otherwise>
									<ul class="breadcrumb">
										<li>短信模版修改</li>
									</ul>
								</c:otherwise>
							</c:choose>
						<input type="hidden" value="${id}" id="spValue">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">模版编号<font style="font-size:12px;color:red;">(必填)</font>：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" ng-model="spTemplate.sptNo" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">模板标题<font style="font-size:12px;color:red;">(必填)</font>：</label>
							<div class="col-sm-4">
								<input class="form-control" type="text" ng-model="spTemplate.sptTetle"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">模板正文<font style="font-size:12px;color:red;">(必填)</font>：</label>
							<div class="col-sm-4">
								<textarea class="required" ng-model="spTemplate.sptText" rows="4" cols="1000" 
											style="resize:none;width:435px;" ></textarea>
							</div>
						</div>
					</div>
				</form>
				<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
						&nbsp;&nbsp; &nbsp;&nbsp;
						<a href="javaScript:void(0)" class="btn btn-primary" ng-click="addEditPage()">保存</a>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>