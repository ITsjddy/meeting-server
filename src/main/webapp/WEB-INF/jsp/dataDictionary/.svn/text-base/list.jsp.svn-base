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
		<script src="${ctx }/static/sysviews/dataDictionary/js/list.js"></script>
		<title>数据字典</title>
	</head>

	<body ng-app="dataDictionaryList" ng-controller="smsTempCtlr">
		<div id="wrapper">
			<div>
				<div id="page-wrapper">
					<ul class="breadcrumb">
						<li>数据字典</li>
					</ul>
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div id="dataTables-example_filter" class="dataTables_filter">
										<label>名称：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.dDName" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<label>说明：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.dDexplanation" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<label>唯一标识：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.dDLogo" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a>&nbsp;&nbsp;
										<a href="${ctx }/dispatcher/dataDictionary/add?typeName=${typeName}&dDTypeLogo=${dDTypeLogo}" class="btn btn-primary">新建</a>
										&nbsp;&nbsp;
										<a href="javaScript:void(0);" ng-click="deletes()" class="btn btn-primary">删除</a>
										&nbsp;&nbsp;
										<a href="javaScript:void(0);" ng-click="pageBack()" class="btn btn-primary">返回</a>
									</div>
								</div>
								<input type="hidden" value="${typeName}" id="typeName">
								<input type="hidden" value="${dDTypeLogo}" id="dDTypeLogo">
								<div class="panel-body">
									<table id="spTable" class="table table-striped table-bordered table-hover " style="text-align:center">
										<thead>
											<tr>
												<th  style="text-align:center"><input type="checkbox" ng-model="selectModel" ng-change="checkAll(selectModel)" ></th>
												<th style="text-align:center">序号</th>
												<th style="text-align:center">名称</th>
												<th style="text-align:center">类型</th>
												<th style="text-align:center">唯一标识</th>
												<th style="text-align:center">说明</th>
												<th style="text-align:center">创建时间</th>
												<th style="text-align:center">操作</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="DataDictionary in items">
												<td style="width:2%"><input type="checkbox" ng-model="DataDictionary.check" ng-change="idSelect(DataDictionary.dDcommId,DataDictionary.check)" ></td>
												<td style="width:2%"><span class="badge">{{ $index + 1 }}</span></td>
												<td style="width:6%" ng-bind="DataDictionary.dDName"></td>
												<td style="width:8%" ng-bind="DataDictionary.dDType"></td>
												<td style="width:8%" ng-bind="DataDictionary.dDLogo"></td>
												<td style="width:8%" ng-bind="DataDictionary.dDexplanation"></td>
												<td style="width:8%" ng-bind="DataDictionary.createTimes"></td>
												<td style="width:3%">
													<div class="btn-group">
														<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
														<ul class="dropdown-menu" style="min-width: 110px;">
															<li><a href="${ctx }/dispatcher/dataDictionary/edit?id={{DataDictionary.dDcommId}}&typeName=${typeName}&dDTypeLogo=${dDTypeLogo}" class="btn btn-info">编辑</a></li>
															<li class="divider"></li>
															<li><a href="${ctx }/dispatcher/dataDictionary/details?id={{DataDictionary.dDcommId}}&typeName=${typeName}&dDTypeLogo=${dDTypeLogo}" class="btn btn-success">详情</a></li>
															<li class="divider"></li>
															<li><a href="javaScript:void(0);" ng-click="deleteSpTemplate(DataDictionary)" class="btn btn-danger">删除</a></li>
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