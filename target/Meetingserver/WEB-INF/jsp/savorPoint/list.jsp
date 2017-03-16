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
		<script src="${ctx }/static/sysviews/savorPoint/js/list.js"></script>
		<title>兴趣点管理</title>
	</head>

	<body ng-app="savorPointList" ng-controller="spCtlr">
		<div id="wrapper">
			<div>
				<div id="page-wrapper">
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<ul class="breadcrumb">
									<li>兴趣点列表</li>
								</ul>
								<div class="panel-heading">
									<div id="dataTables-example_filter" class="dataTables_filter">
										<label>名称：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.spName" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<label>唯一标识：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.spOnly" class="input-sm"></label>
										<!-- &nbsp;&nbsp;&nbsp;&nbsp;<label>语言类型：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.spLanguageType" class="input-sm"></label> -->
										&nbsp;&nbsp;&nbsp;&nbsp;<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a> &nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${ctx }/dispatcher/savorPoint/addPage" class="btn btn-primary">新建</a>
									</div>
								</div>
								<div class="panel-body">
									<table id="spTable" class="table table-striped table-bordered table-hover " style="text-align:center">
										<thead>
											<tr>
												<th style="text-align:center">序号</th>
												<th style="text-align:center">唯一标识</th>
												<th style="text-align:center">类型</th>
												<th style="text-align:center">名称</th>
												<th style="text-align:center">地址</th>
												<th style="text-align:center">经度</th>
												<th style="text-align:center">维度</th>
												<th style="text-align:center">操作</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="savorPoint in items">
												<td style="width:2%"><span class="badge">{{ $index + 1 }}</span></td>
												<td style="width:6%" ng-bind="savorPoint.spOnly"></td>
												<td style="width:6%" ng-bind="savorPoint.spType"></td>
												<td style="width:10%" ng-bind="savorPoint.spName"></td>
												<td style="width:20%" ng-bind="savorPoint.spAddress"></td>
												<td style="width:5%" ng-bind="savorPoint.spLongitude"></td>
												<td style="width:5%" ng-bind="savorPoint.spDimensions"></td>
												<td style="width:7%">
													<div class="btn-group" >
														<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
														<ul class="dropdown-menu" style="min-width: 110px;">
															<li><a href="${ctx }/dispatcher/savorPoint/editPage?spUniteId={{ savorPoint.spUniteId }}" class="btn btn btn-info">编辑</a></li>
															<li class="divider"></li>
															<li><a href="javaScript:void(0);" ng-click="deleteSavorPoint(savorPoint)" class="btn btn-danger">删除</a></li>
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