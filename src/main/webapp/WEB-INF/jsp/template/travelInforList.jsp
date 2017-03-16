<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>团导入模板管理</title>
		<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
		<!-- 分页样式 -->
		<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
		<!-- 分页所需js -->
		<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
		<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
		<!-- 模块js -->
		<script src="${ctx }/static/sysviews/template/js/travelInforList.js"></script>
	</head>

	<body ng-app="guestApp" ng-controller="guestController">
		<ul class="breadcrumb">
			<li>行程信息导入模板管理</li>
		</ul>
		<div id="wrapper">
			<div id="page-wrapper">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>模板字段名称:
										<input type="search"  ng-model="searchParam.templateName" class="input-sm" />
									</label>
									<label>实体字段名称:
										<input type="search"  ng-model="searchParam.fieldName" class="input-sm" />
									</label>
									<label>说明:
										<input type="search"  ng-model="searchParam.explain" class="input-sm" />
									</label>
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a>
									<security:authorize access="hasRole('ROLE_GUEST_ADD')">
										<a href="${ctx }${path}/travelInforTemplate/operate" class="btn btn-primary">新建</a>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_GUEST_ADD')">
										<a href="${ctx }${path}/travelInforTemplate/createindex" class="btn btn-primary">导入</a>
									</security:authorize>
								</div>
							</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
									<thead>
										<tr>
											<th>序号</th>
											<th>模版字段名称</th>
											<th>实体字段名称</th>
											<th>说明</th>
											<th>创建时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="guest in items">
											<td><span class="badge">{{ $index + 1 }}</span></td>
											<td ng-bind="guest.templateName"></td>
											<td ng-bind="guest.fieldName"></td>
											<td ng-bind="guest.explain"></td>
											<td ng-bind="guest.createTimes"></td>
											<td>
												<div class="btn-group">
													<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
													<ul class="dropdown-menu">
														<security:authorize access="hasRole('ROLE_GUEST_EDIT')">
															<li><a href="${ctx }${path}/travelInforTemplate/operate?id={{ guest.id }}" class="btn btn btn-info">编辑</a></li>
														</security:authorize>
														<security:authorize access="hasRole('ROLE_GUEST_DELETE')">
															<li class="divider"></li>
															<li><a href="javaScript:void(0);" ng-click="guestDelete(guest.id)" class="btn btn-danger">删除</a></li>
														</security:authorize>
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