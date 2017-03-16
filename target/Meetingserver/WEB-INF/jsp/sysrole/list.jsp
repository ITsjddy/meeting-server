<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>角色列表</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/sysrole/js/list.js"></script>
</head>
<body ng-app="sysRoleApp" ng-controller="sysRoleController">
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                	<ul class="breadcrumb"><li>角色列表</li></ul>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>名称:
										<input type="search"  ng-model="searchParam.name" class="form-control input-sm" 
											laceholder="" aria-controls="dataTables-example">
									</label>
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
									<security:authorize access="hasRole('ROLE_SYS_ROLE_ADD')">
										<a href="${ctx }${path}/sysRole/add" class="btn btn-primary" >新建</a>
									</security:authorize>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
									   <th>名称</th>
									   <th>备注</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="role in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="role.name"></td>
										<td ng-bind="role.remark"></td>
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<security:authorize access="hasRole('ROLE_SYS_ROLE_EDIT')">
														<li><a href="${ctx }${path}/sysRole/edit?id={{ role.id }}" class="btn btn btn-info">编辑</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_SYS_ROLE_DELETE')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/sysRole/authorization?id={{ role.id }}" class="btn btn btn-success">授权</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_SYS_ROLE_DELETE')">
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="sysRoleDelete(role.id)" class="btn btn-danger">删除</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_SYS_ROLE_DETAILS')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/sysRole/details?id={{ role.id }}" class="btn btn btn-info">详情</a></li>
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