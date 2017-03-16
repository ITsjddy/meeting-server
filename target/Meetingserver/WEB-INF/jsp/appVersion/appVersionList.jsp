<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>App版本管理</title>
<!-- 分页样式 -->
<link rel="stylesheet"
	href="${ctx }/static/sysviews/page/css/pagecss.css">
<!-- 分页所需js -->
<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
<!-- 模块js -->
<script src="${ctx }/static/sysviews/appVersion/js/list.js"></script>
<script src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx }/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
</head>
<body ng-app="appVersionApp" ng-controller="appVersionController">
	<ul class="breadcrumb">
		<li>App版本管理</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div id="dataTables-example_filter" class="dataTables_filter">
								<label>手机类型:
									<select class="form-control" ng-model="appVersion.type">
										<option value="">请选择</option>
										<option value="1">Android</option>
										<option value="2">IOS</option>
									</select>
									版本名称:<input type="search" ng-model="appVersion.versionName" class="form-control input-sm" placeholder="" aria-controls="dataTables-example">
									</label>
									<label>
									创建时间:
									<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" autofocus id="startDate" name="startDate" />
									至：
									<input type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="form-control" autofocus id="endDate" name="endDate" />
								</label><label>
								<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a> 
								<a href="${ctx }/${path}/appVersion/appVersionAdd" class="btn btn-primary">新建</a></label>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
								<thead>
									<tr>
										<th>序号</th>
										<th>手机类型</th>
										<th>版本名称</th>
										<th>版本号</th>
										<th>更新描述</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in items">
										<td><span class="badge ng-binding">{{ $index + 1 }}</span></td>
										<td ng-bind="item.type"></td>
										<td ng-bind="item.versionName"></td>
										<td ng-bind="item.versionNum"></td>
										<td ng-bind="item.remark"></td>
										<td ng-bind="item.createTimes"></td>
										<td>
											<a href="${ctx }/${path}/appVersion/appVersionEdit?id={{ item.id }}" class="btn btn btn-info">编辑</a> 
											<a href="${ctx }/${path}/appVersion/delete?id={{ item.id }}" class="btn btn-danger">删除</a>
										</td>
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