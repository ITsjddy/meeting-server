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
<script src="${ctx }/static/checkbox/js/deletes.js"></script>
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
								<label>手机类型:</label>
								<label><select class="form-control" ng-model="searchParam.type">
										<option value="">请选择</option>
										<option value="1">Android</option>
										<option value="2">IOS</option>
									</select></label>
									<label>版本名称:<input type="search" ng-model="searchParam.versionName" class="input-sm" >
									</label>
									&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a> 
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${ctx }/${path}/appVersion/add" class="btn btn-primary">新建</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="javaScript:void(0);" ng-click="deletes()" class="btn btn-primary">删除</a>
							</div>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%" class="table table-striped table-bordered table-hover"  style="text-align:center" id="dataTables-example">
								<thead>
									<tr>
										<th  style="text-align:center"><input type="checkbox" ng-model="selectModel" ng-change="checkAll(selectModel)" ></th>
										<th style="text-align:center">序号</th>
										<th style="text-align:center">手机类型</th>
										<th style="text-align:center">版本名称</th>
										<th style="text-align:center">版本号</th>
										<th style="text-align:center">更新描述</th>
										<th style="text-align:center">创建时间</th>
										<th style="text-align:center">操作</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in items">
									<td style="width:2%"><input type="checkbox" ng-model="item.check" ng-change="idSelect(item.id,item.check)" ></td>
										<td><span class="badge ng-binding">{{ $index + 1 }}</span></td>
										<td ng-if="item.type == 1">Android</td>
										<td ng-if="item.type == 2">IOS</td>
										<td ng-bind="item.versionName"></td>
										<td ng-bind="item.versionNum"></td>
										<td ng-bind="item.remark"></td>
										<td ng-bind="item.createTimes"></td>
										<td>
										<div class="btn-group">
														<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
														<ul class="dropdown-menu" style="min-width: 110px;">
															<li><a href="${ctx }/${path}/appVersion/edit?id={{ item.id }}" class="btn btn-info">编辑</a></li>
															<li class="divider"></li>
															<li><a href="${ctx }/${path}/appVersion/details?id={{ item.id }}" class="btn btn-success">详情</a></li>
															<li class="divider"></li>
															<li><a href="javaScript:void(0);" ng-click="deleteById(item)" class="btn btn-danger">删除</a></li>
														</ul>
													</div>
											
											
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