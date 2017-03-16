<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>服务人员列表</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/servicePersonnel/js/lists.js"></script>
</head>
<body ng-app="servicePersonnelApp" ng-controller="servicePersonnelController">
	<ul class="breadcrumb"><li>服务人员列表</li></ul>
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
<!-- 								<label>姓名:</label> -->
<!-- 									<label> -->
<!-- 									<input type="search" ng-model="searchParam.member_name" class="input-sm"> -->
<!-- <!-- 										<input type="search"  ng-model="searchParam.member_name" class="form-control input-sm"  --> 
<!-- <!-- 											 aria-controls="dataTables-example"> --> 
<!-- 									</label> -->
									
									<label>邀请码/用户名:</label>
									<label>
										<input type="search" ng-model="searchParam.member_userName" class="input-sm">
									</label>
									<label>姓名:</label>
									<label>
										<input type="search" ng-model="searchParam.member_name" class="input-sm">
									</label>
									<label>单位/职务:</label>
									<label>
										<input type="search" ng-model="searchParam.member_workPlace" class="input-sm">
									</label>
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
									<security:authorize access="hasRole('ROLE_GUEST_ADD')">
										<a href="${ctx }${path}/servicePersonnel/add" class="btn btn-primary" >新建</a>
									</security:authorize>
								</div>
						</div>
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
                                        <th>邀请码</th>
                                       <th>用户名</th>
                                       <th>姓名</th>
									   <th>个人简介</th>
<!-- 									   <th>所属团</th> -->
<!-- 									   <th>姓名</th> -->
									   <th>单位</th>
<!-- 									   <th>职务</th> -->
<!-- 									   <th>是否已扫描</th> -->
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="service in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="service.member.invitationCode"></td>
										<td ng-bind="service.member.userName"></td>
										<td ng-bind="service.name"></td>
										<td ng-bind="service.introduction"></td>
										<td ng-bind="service.company"></td>
<!-- 										<td ng-bind="service.groupId"></td> -->
<!-- 										<td ng-bind="service.workPlace"></td> -->
<!-- 										<td ng-bind="service.job"></td> -->
<!-- 										<td ng-if="service.member.whetherScanning == 1">是</td> -->
<!-- 										<td ng-if="service.member.whetherScanning != 1">否</td> -->
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<security:authorize access="hasRole('PER_SERVICE_EDIT')">
														<li><a href="${ctx }${path}/servicePersonnel/edit?id={{ service.id }}" class="btn btn btn-info">编辑</a></li>
													</security:authorize>
													<security:authorize access="hasRole('PER_SERVICE_DELETE')">
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="guestDelete(service.id)" class="btn btn-danger">删除</a></li>
													</security:authorize>
													<security:authorize access="hasRole('PER_SERVICE_DETAILS')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/servicePersonnel/details?id={{ service.id }}" class="btn btn btn-info">详情</a></li>
													</security:authorize>
													<security:authorize access="hasRole('PER_SERVICE_EDIT')">
														<li class="divider"></li>
														<li><a data-toggle="modal" data-target="#myPassword" ng-click="editPassword(service.member.memberId,service.member.invitationCode)" class="btn btn btn-info">修改密码</a></li>
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
        
         <!-- 修改密码 -->
        <%@include file="/WEB-INF/jsp/member/editPassword.jsp"%>
    </div>
</body>
</html>