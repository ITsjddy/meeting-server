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
		<script src="${ctx }/static/sysviews/cloudGroup/js/list.js"></script>
		<title>融云群组</title>
	</head>

	<body ng-app="cloudGroupList" ng-controller="cloudGroupCller">
		<div id="wrapper">
			<div>
				<div id="page-wrapper">
					<ul class="breadcrumb">
						<li>融云群组</li>
					</ul>
					<div class="row">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div id="dataTables-example_filter" class="dataTables_filter">
										<label>群组名称：&nbsp;&nbsp;&nbsp;&nbsp;<input type="search" ng-model="searchParam.fgName" class="input-sm"></label> &nbsp;&nbsp;&nbsp;&nbsp;
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a> &nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${ctx }/dispatcher/cloudGroup/adet" class="btn btn-primary">新建</a>
									</div>
								</div>
								<div class="panel-body">
									<table id="spTable" class="table table-striped table-bordered table-hover " style="text-align:center">
										<thead>
											<tr>
												<th style="text-align:center">序号</th>
												<th style="text-align:center">群组名称</th>
												<th style="text-align:center">创建时间</th>
												<th style="text-align:center">操作</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="cloudItem in items">
												<td style="width:2%"><span class="badge">{{ $index + 1 }}</span></td>
												<td style="width:6%" ng-bind="cloudItem.fgName"></td>
												<td style="width:8%" ng-bind="cloudItem.createTimes"></td>
												<td style="width:3%">
													<div class="btn-group">
														<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
														<ul class="dropdown-menu" style="min-width: 110px;">
															<li><a href="${ctx }/dispatcher/cloudGroup/adet?id={{ cloudItem.id }}" class="btn btn-info">编辑</a></li>
															<li class="divider"></li>
															<li><a href="${ctx }/dispatcher/cloudGroup/details?id={{ cloudItem.id }}" class="btn btn-success">详情</a></li>
															<li class="divider"></li>
															<li><a href="javaScript:void(0);" ng-click="deleteCloudGroup(cloudItem)" class="btn btn-danger">删除</a></li>
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