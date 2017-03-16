<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>info</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/publicshComment/js/list.js"></script>
</head>
<body ng-app="publicshCommentApp" ng-controller="publicshCommentController">
	<!-- <ul class="breadcrumb">
		<li>主题列表</li>
	</ul> -->
	<ul class="nav nav-tabs"  >
		<!-- <li ng-class="active"><a href="javaScript:;" ng-click="index()">未审核列表</a></li>
		<li ng-class=""><a href="javaScript:;" ng-click="checkList()">已审核列表</a></li> -->
		<li class="active"><a href="index()">未审核列表</a></li>
		<li class=""><a href="javaScript:;" ng-click="checkList()">已审核列表</a></li>
	</ul></br>
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>发布人（用户名/姓名）:
										<input type="search"  ng-model="searchParam.public_member" class="form-control input-sm" 
											 aria-controls="dataTables-example">
									</label>
									<label>主题/评论内容:
										<input type="search"  ng-model="searchParam.public_message" class="form-control input-sm" 
											 aria-controls="dataTables-example">
									</label>
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
								</div>
						</div>
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                    	<th>序号</th>
										<th>发布内容</th>
										<th>发布人</th>
										<th>发布时间</th>
										<th>审核状态</th>
										<th>发布类型</th>
										<th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="publics in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="publics.message"></td>
										<td ng-bind="publics.member.name"></td>
										<td ng-bind="publics.createTimes"></td>
										<td ng-if="publics.status == 1" >未审核</td>
										<td ng-if="publics.status == 2">审核通过</td>
										<td ng-if="publics.status == 3">审核不通过</td>
										<td ng-if="publics.type == 1" >主题</td>
										<td ng-if="publics.type == 2" >评论</td>
										<td>
									    	<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<li><a href="${ctx }${path}/publicshComment/details?id={{ publics.id }}" class="btn btn btn-info">详情</a></li>
												  	<li class="divider"></li>
												  	<li><a href="${ctx }${path}/publicshComment/auditing?id={{ publics.id }}" class="btn btn btn-danger">审核</a></li>
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