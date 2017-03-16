<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>公共任务管理</title>
<!-- 分页样式 -->
<link rel="stylesheet"
	href="${ctx }/static/sysviews/page/css/pagecss.css">
<!-- 分页所需js -->
<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
<!-- 模块js -->
<script src="${ctx }/static/sysviews/commonTask/js/list.js"></script>
</head>
<body ng-app="commonTaskApp" ng-controller="commonTaskController">
	<ul class="breadcrumb">
		<li>公共任务管理</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div id="dataTables-example_filter" class="dataTables_filter">
								<label>名称:<input type="search"
									ng-model="searchParam.name"
									class="form-control input-sm" placeholder=""
									aria-controls="dataTables-example"></label>
									<label>唯一标识:<input type="search"
									ng-model="searchParam.uniqueCode"
									class="form-control input-sm" placeholder=""
									aria-controls="dataTables-example"></label> <a
									href="javaScript:void(0)" class="btn btn-primary"
									ng-click="searchByParam()">查询</a> <a
									href="${ctx }/${path}/commonTask/commonTaskAdd"
									class="btn btn-primary">新建</a>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%"
								class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>序号</th>
										<th>名称</th>
										<th>创建时间</th>
										<th>唯一标识</th>
										<th>描述</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in items">
										<td><span class="badge ng-binding">{{ $index + 1 }}</span></td>
										<td ng-bind="item.name"></td>
										<td ng-bind="item.createTimes"></td>
										<td ng-bind="item.uniqueCode"></td>
										<td ng-bind="item.remark"></td>
										<td><a
											href="${ctx }/${path}/commonTask/commonTaskEdit?id={{ item.id }}"
											class="btn btn btn-info">编辑</a> <a
											href="${ctx }/${path}/commonTask/delete?id={{ item.id }}"
											class="btn btn-danger">删除</a> <a
											href="${ctx }/${path}/commonTask/commonTaskSign?id={{ item.id }}"
											class="btn btn btn-info">设置</a></td>
									</tr>
								</tbody>
							</table>
							<!-- 分页的标签 -->
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>

		</div>
		<!-- /#page-wrapper -->
	</div>
</body>
</html>