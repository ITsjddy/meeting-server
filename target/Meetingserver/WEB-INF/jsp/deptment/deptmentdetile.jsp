<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>tableTest</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- ztree用的css和js -->
	<link rel="stylesheet" href="${ctx }/css/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="${ctx }/css/ztree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.excheck.js"></script>
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<script src="${ctx }/js/json2.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/groupAdministrator/js/list.js"></script>
	<script src="${ctx }/static/sysviews/department/js/departmentdetile.js"></script>
</head>
<body ng-app="groupAdministratorApp" ng-controller="groupAdministratorController" style="width:100%;height:100%;">
	<input type="hidden" id="groupid" value="${groupid}"/>
	<ul class="nav nav-tabs">
		<li class="active"><a>基本信息</a></li>
		<li><a href="${ctx }dispatcher/department/indexinclude?groupid=${groupid}" >子团信息</a></li>
		<li><a href="${ctx }dispatcher/department/indexguest?groupid=${groupid}" >嘉宾信息</a></li>
		<li><a href="${ctx }dispatcher/department/indexserver?groupid=${groupid}" >服务人员</a></li>
	</ul><br/>
	 <div id="page-wrappertow" class="tab-pane active">
            <div class="tab-content form">
				<div class="tab-pane active" id="profile">
					<div id="dataTables-example_filter" class="dataTables_filter">
						<a href="${ctx }/dispatcher/department/edit?id=${groupid}"  class="btn btn-primary" >编辑</a>
					</div>
					<table width="100%" id="departdetile" border="0" cellspacing="0" cellpadding="0" class="table table-bordered table-form" style="margin-top: 10px">
						<tbody>
							<tr>
								<th width="20%" style="background-color: #f9f9f9">团名称</th>
								<td width="80%"></td>
							</tr>
							<tr>
								<th style="background-color: #f9f9f9">团名称英文</th>
								<td></td>
							</tr>
							<tr>
								<th style="background-color: #f9f9f9">上级团</th>
								<td></td>
							</tr>
							<tr>
								<th style="background-color: #f9f9f9">团分类</th>
								<td></td>
							</tr>
							<tr>
								<th style="background-color: #f9f9f9">说明</th>
								<td></td>
							</tr>		
						</tbody>
					</table>		
				</div>
	 		</div>
	 		<ul class="breadcrumb"><li>团管理员</li></ul>
	 		<div id="dataTables-example_filter" class="dataTables_filter">
				<a href="${ctx }/dispatcher/groupAdministrator/newAdmin?id=${groupid}" class="btn btn-primary" >新建</a>
			</div>
	 		<div class="row">
	 		<div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
                                       <th>用户名</th>
                                       <th>手机号</th>
									   <th>姓名</th>
									   <th>邮箱</th>
									   <th>操作</th>
									 </tr>
								</thead>
                                <tbody>
                                	<tr ng-repeat="guest in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.member.email"></td>
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">												  	
													<security:authorize access="hasRole('ROLE_SYS_ROLE_DELETE')">
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="groupAdministratorDelete(guest.id)" class="btn btn-danger">删除</a></li>
													</security:authorize>						
												</ul>
									    	</div>
										</td>
									</tr>
								</tbody>
							</table>
							<div style="display:none;">
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>