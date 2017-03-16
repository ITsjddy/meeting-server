<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>新闻栏目列表页面</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/newsColumn/js/list.js"></script>
</head>
<body  ng-app="newsColumnApp" ng-controller="newsColumnController">
<ul class="breadcrumb"><li>新闻栏目列表</li></ul>
<div id="wrapper">
        <div id="page-wrapper">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>名称:<input type="search"  ng-model="searchParam.name"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
										<a href="${ctx }/${path}/newsColumn/toAdd" class="btn btn-primary" >新建</a>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
									   <th>名称</th>
									   <th>类型</th>
									   <th>父级栏目</th>
									   <th>描述</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="newsColumn in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="newsColumn.name"></td>
										<td ng-if="newsColumn.type == 'column'">栏目</td>
										<td ng-if="newsColumn.type == 'category'">分类</td>
										<td ng-bind="newsColumn.parentName"></td>
										<td ng-bind="newsColumn.remark"></td>
										<td>
											<a href="${ctx }/${path}/newsColumn/toDetail?uniqueCode={{ newsColumn.uniqueCode }}&id={{newsColumn.id}}" class="btn btn-primary" >详情</a>
											<a href="${ctx }/${path}/newsColumn/toEdit?uniqueCode={{ newsColumn.uniqueCode }}&id={{newsColumn.id}}" class="btn btn btn-info" >编辑</a>
											<a href="javascript:void(0);" ng-click="deleteItem(newsColumn.uniqueCode)" class="btn btn-danger" >删除</a>
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