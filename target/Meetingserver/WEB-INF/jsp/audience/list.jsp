<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>观众列表</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/audience/js/list.js"></script>
</head>
<body ng-app="audienceApp" ng-controller="audienceController">
	<ul class="breadcrumb"><li>观众列表</li></ul>
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>邀请码/用户名:
										<input type="search"  ng-model="searchParam.member_userName" class="form-control input-sm" 
											 aria-controls="dataTables-example">
									</label>
									<label>手机号:
										<input type="search"  ng-model="searchParam.member_mobile" class="form-control input-sm" 
											 aria-controls="dataTables-example">
									</label>
									<label>姓名:
										<input type="search"  ng-model="searchParam.member_name" class="form-control input-sm" 
											 aria-controls="dataTables-example">
									</label>
									<label>单位/职务:
										<input type="search"  ng-model="searchParam.member_workPlace" class="form-control input-sm" 
											 aria-controls="dataTables-example">
									</label>
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
									<%-- <security:authorize access="hasRole('ROLE_Audience_ADD')"> --%>
										<a href="${ctx }/dispatcher/audience/add" onclick="javascript:loadhtml('/audience/add')" class="btn btn-primary">新建</a>
									<%-- </security:authorize> --%>
									<a href="javaScript:void(0);" ng-click="deleteAll()" class="btn btn-primary">删除</a>
									<!-- <input type="button" class="btn btn-primary" value="删除" onclick="deleteAll()"/> -->
								</div>
						</div>
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
									  <th><input type="checkbox" name="checkAll" id="checkAll"> 
									   <!--  <input type="checkbox" ng-model="master" ng-click="all(master,tesarry)">--></th>
                                       <th>序号</th>
                                       <th>邀请码</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>手机号</th> 
									   <th>联系人</th>
									   <th>联系人电话</th>
									   <th>工作单位</th>
									   <th>职位</th>
									   <!-- <th>是否已扫描</th> -->
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="audience in items">
 										<td>
 										<input type="checkbox" value="{{audience.id}}" ng-bind="audience.id" name="ids" id="ids" />
 										<!-- <input id={{audience}} type="checkbox"  ng-model="x" ng-checked="master" ng-click="chk(audience,x)"> -->
 										</td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="audience.member.invitationCode"></td>
										<td ng-bind="audience.member.userName"></td>
										<td ng-bind="audience.name"></td>
										<td ng-bind="audience.member.mobile"></td>
										<td ng-bind="audience.contactPerson"></td>
										<td ng-bind="audience.contactPhone"></td>
										<td ng-bind="audience.workPlace"></td>
										<td ng-bind="audience.job"></td>
										<!-- <td ng-if="audience.member.whetherScanning == 1">是</td>
										<td ng-if="audience.member.whetherScanning != 1">否</td> -->
										<td>
									    	<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<%-- <security:authorize access="hasRole('ROLE_AUDIENCE_EDIT')"> --%>
														<li><a href="${ctx }${path}/audience/edit?id={{ audience.id }}" class="btn btn btn-info">编辑</a></li>
													<%-- </security:authorize>
													<security:authorize access="hasRole('ROLE_AUDIENCE_DELETE')"> --%>
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="guestDelete(audience.id)" class="btn btn-danger">删除</a></li>
													<%-- </security:authorize>
													<security:authorize access="hasRole('ROLE_AUDIENCE_DETAIL')"> --%>
														<li class="divider"></li>
														<li><a href="${ctx }${path}/audience/detail?id={{ audience.id }}" class="btn btn btn-info">详情</a></li>
													<%-- </security:authorize> --%>
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