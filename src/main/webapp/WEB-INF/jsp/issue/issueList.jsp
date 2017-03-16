<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>议题列表页面</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 多选删除js -->
	<script src="${ctx }/static/checkbox/js/deletes.js"></script>
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/issue/js/list.js"></script>
</head>
<body  ng-app="issueApp" ng-controller="issueController">
<ul class="breadcrumb"><li>议题列表</li></ul>
<div id="wrapper">
        <div id="page-wrapper">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>议题名称:<input type="search"  ng-model="searchParam.name"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
									<label>
										会场名称:<select class="form-control" name="conHallId" ng-model="searchParam.conHallId">
													<option value="">--请选择--</option>
													<option ng-repeat="conHall in conHallList" value="{{conHall.id}}" ng-model="conHall.conHallId">{{conHall.name}}</option>
												</select>
										
									</label>
									<label>
										所属论坛:<select class="form-control" ng-model="searchParam.scheduleId">
													<option value="">--请选择--</option>
													<option ng-repeat="schedule in scheduleList" value="{{schedule.id}}">{{schedule.name}}</option>
												</select>
										
									</label>
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
										<a href="${ctx }/${path}/issue/toAdd" class="btn btn-primary" >新建</a>
										<a href="javaScript:void(0);" ng-click="deletes()" class="btn btn-danger">删除</a>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                    <th><input type="checkbox" ng-model="selectModel" ng-change="checkAll(selectModel)" ></th>
                                       <th>序号</th>
									   <th>议题名称</th>
									   <th>会场名称</th>
									   <th>所属论坛</th>
									   <th>时间描述</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="issue in items">
									<td><input type="checkbox" ng-model="issue.check" ng-change="idSelect(issue.id,issue.check)" ></td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="issue.name"></td>
										<td ng-bind="issue.conHallName"></td>
										<td ng-bind="issue.scheduleName"></td>
										<td ng-bind="issue.dateDir"></td>
										<td>
											<a href="${ctx }/${path}/issue/toDetail?id={{ issue.id }}&uniqueCode={{issue.uniqueCode}}" class="btn btn-primary" >详情</a>
											<a href="${ctx }/${path}/issue/toEdit?id={{ issue.id }}&uniqueCode={{issue.uniqueCode}}" class="btn btn btn-info" >编辑</a>
											<a href="javascript:void(0);" ng-click="deleteItem(issue.uniqueCode)" class="btn btn-danger"  >删除</a>
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