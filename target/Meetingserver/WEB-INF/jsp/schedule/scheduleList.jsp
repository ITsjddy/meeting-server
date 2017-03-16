<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>论坛列表页面</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/schedule/js/list.js"></script>
</head>
<body  ng-app="scheduleApp" ng-controller="scheduleController">
<ul class="breadcrumb"><li>论坛列表</li></ul>
<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>姓名:<input type="search"  ng-model="searchParam.name"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
										<a href="${ctx }/${path}/schedule/toAdd" class="btn btn-primary" >新建</a>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
									   <th>会议/论坛名称</th>
									   <th>会场名称</th>
									   <th>时间描述</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="schedule in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="schedule.name"></td>
										<td ng-bind="schedule.conHallName"></td>
										<td ng-bind="schedule.dateDir"></td>
										<td>
											<a href="${ctx }/${path}/schedule/toDetail?id={{ schedule.id }}&uniqueCode={{schedule.uniqueCode}}" class="btn btn-primary" >详情</a>
											<a href="${ctx }/${path}/schedule/toEdit?id={{ schedule.id }}&uniqueCode={{schedule.uniqueCode}}" class="btn btn btn-info" >编辑</a>
											<a href="javascript:void(0);" ng-click="deleteItem(schedule.uniqueCode)" class="btn btn-danger"  >删除</a>
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