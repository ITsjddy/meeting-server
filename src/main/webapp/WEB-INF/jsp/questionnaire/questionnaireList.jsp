<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>调查问卷</title>
<!-- 分页样式 -->
<link rel="stylesheet"
	href="${ctx }/static/sysviews/page/css/pagecss.css">
<!-- 分页所需js -->
<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
<!-- 模块js -->
<script src="${ctx }/static/sysviews/questionnaire/js/list.js"></script>
</head>
<body ng-app="questionnaireApp" ng-controller="questionnaireController">
	<ul class="breadcrumb">
		<li>调查问卷</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div id="dataTables-example_filter" class="dataTables_filter">
								<label>标题:<input type="search"
									ng-model="searchParam.title" class="form-control input-sm"
									placeholder="" aria-controls="dataTables-example"></label> <a
									href="javaScript:void(0)" class="btn btn-primary"
									ng-click="searchByParam()">查询</a> <a
									href="${ctx }/${path}/questionnaire/questionnaireAdd"
									class="btn btn-primary">新建问卷</a>
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
										<th>标题</th>
										<th>说明</th>
										<th>题目数</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="item in items">
										<td><span class="badge ng-binding">{{ $index + 1 }}</span></td>
										<td ng-bind="item.title"></td>
										<td ng-bind="item.description"></td>
										<td ng-bind="item.topicNum"></td>
										<td><a
											href="${ctx }/${path}/questionnaire/questionnaireEdit?questionnaireId={{ item.id }}"
											class="btn btn btn-info">编辑</a> <a
											href="${ctx }/${path}/questionnaire/delete?id={{ item.id }}"
											class="btn btn-danger">删除</a></td>
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