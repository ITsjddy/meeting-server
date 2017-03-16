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
		<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
		<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
		<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
		<script src="${ctx }/static/checkbox/js/deletes.js"></script>
		<script src="${ctx }/static/sysviews/dataDictionaryType/js/list.js"></script>
		<title>短信模板</title>
	</head>

	<body ng-app="dDtypeList" ng-controller="dDtypeListCtlr">
		<div id="wrapper">
			<div>
				<div id="page-wrapper">
					<ul class="breadcrumb">
						<li>数据字典类型</li>
					</ul>
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div id="dataTables-example_filter" class="dataTables_filter">
										<label>名称：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.dTypeName" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<label>唯一标识：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.dTypeLogo" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<label>说明：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.dTypeExplanation" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a>
										 &nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${ctx }/dispatcher/dataDictionaryType/add" class="btn btn-primary">新建</a>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="javaScript:void(0);" ng-click="deletes()" class="btn btn-primary">删除</a>
									</div>
								</div>
								<div class="panel-body">
									<table id="spTable" class="table table-striped table-bordered table-hover " style="text-align:center">
										<thead>
											<tr>
												<th  style="text-align:center"><input type="checkbox" ng-model="selectModel" ng-change="checkAll(selectModel)" ></th>
												<th style="text-align:center">序号</th>
												<th style="text-align:center">名称</th>
												<th style="text-align:center">唯一标识</th>
												<th style="text-align:center">说明</th>
												<th style="text-align:center">时间</th>
												<th style="text-align:center">操作</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="dDType in items">
												<td style="width:2%"><input type="checkbox" ng-if="dDType.dTypeLogo!=='languagetype'" ng-model="dDType.check" ng-change="idSelect(dDType.id,dDType.check)" ></td>
												<td style="width:4%"><span class="badge">{{ $index + 1 }}</span></td>
												<td style="width:15%">
													<a href="javaScript:void(0);" style="color:bule;" ng-click="toDataPage(dDType.dTypeName,dDType.dTypeLogo)">{{dDType.dTypeName}}</a>
												</td>
												<td style="width:15%" ng-bind="dDType.dTypeLogo"></td>
												<td style="width:15%" ng-bind="dDType.dTypeExplanation"></td>
												<td style="width:8%" ng-bind="dDType.createTimes"></td>
												<td style="width:3%">
													<div class="btn-group">
														<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
														<ul class="dropdown-menu" style="min-width: 110px;">
															<li><a href="${ctx }/dispatcher/dataDictionaryType/edit?id={{ dDType.id }}" ng-if="dDType.dTypeLogo!=='languagetype'" class="btn btn-info">编辑</a></li>
															<li class="divider"></li>
															<li><a href="${ctx }/dispatcher/dataDictionaryType/details?id={{ dDType.id }}" class="btn btn-success">详情</a></li>
															<li class="divider"></li>
															<li><a href="javaScript:void(0);" ng-if="dDType.dTypeLogo!=='languagetype'" ng-click="deleteDtye(dDType)" class="btn btn-danger">删除</a></li>
														</ul>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
									<nav>
										<tm-pagination conf="paginationConf"></tm-pagination>
									</nav>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>