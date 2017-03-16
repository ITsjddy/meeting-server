<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>嘉宾列表</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 多选删除js -->
	<script src="${ctx }/static/checkbox/js/deletes.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/guest/js/list.js"></script>
</head>
<body ng-app="guestApp" ng-controller="guestController">
	<ul class="breadcrumb"><li>嘉宾列表</li></ul>
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>邀请码/用户名:</label>
									<label>
										<input type="search" ng-model="searchParam.member_userName" class="input-sm">
									</label>
									<label>手机号:</label>
									<label>
										<input type="search" ng-model="searchParam.member_mobile" class="input-sm">
									</label>
									<label>姓名:</label>
									<label>
										<input type="search" ng-model="searchParam.member_name" class="input-sm">
									</label>
									<label>单位/职务:</label>
									<label>
										<input type="search" ng-model="searchParam.member_workPlace" class="input-sm">
									</label>
									<label>是否是vip:</label>
									<label>
										<select class="form-control" ng-model="searchParam.member_vip">
											<option value="">--请选择--</option>
											<option value="1" ng-selected="isSelected(searchParam.member_vip=='1')">是</option>
											<option value="2" ng-selected="isSelected(searchParam.member_vip=='2')">否</option>
										</select>
									</label>
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
									<security:authorize access="hasRole('ROLE_GUEST_ADD')">
										<a href="${ctx }${path}/guest/add" class="btn btn-primary">新建</a>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_GUEST_ADD')">
										<a href="javaScript:void(0);" ng-click="deletes()" class="btn btn-danger">删除</a>
									</security:authorize>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th><input type="checkbox" ng-model="selectModel" ng-change="checkAll(selectModel)" ></th>
                                       <!-- <th>序号</th> -->
                                       <th>邀请码</th>
									   <th>手机号</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>单位</th>
									   <th>职务</th>
									   <th>是否已扫描</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items">
										<td><input type="checkbox" ng-model="guest.check" ng-change="idSelect(guest.id,guest.check)" ></td>
										<!-- <td><span class="badge">{{ $index + 1 }}</span></td> -->
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<td ng-if="guest.member.whetherScanning == 1">是</td>
										<td ng-if="guest.member.whetherScanning != 1">否</td>
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<security:authorize access="hasRole('ROLE_GUEST_EDIT')">
														<li><a href="${ctx }${path}/guest/edit?id={{ guest.id }}" class="btn btn btn-info">编辑</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_GUEST_DELETE')">
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="guestDelete(guest.id)" class="btn btn-danger">删除</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_GUEST_DETAILS')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/guest/details?id={{ guest.id }}" class="btn btn btn-info">详情</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_GUEST_EDIT')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/guest/servicePersonnelIndex?id={{ guest.id }}" class="btn btn btn-info">服务人员设置</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_GUEST_EDIT')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/stroke/indexs?id={{ guest.id}}" class="btn btn btn-info">添加日程</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_GUEST_EDIT')">
														<li class="divider"></li>
														<li><a data-toggle="modal" data-target="#myPassword" ng-click="editPassword(guest.member.memberId,guest.member.invitationCode)" class="btn btn btn-info">修改密码</a></li>
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
        <!-- 修改密码 -->
        <%@include file="/WEB-INF/jsp/member/editPassword.jsp"%>
    </div>
</body>
</html>