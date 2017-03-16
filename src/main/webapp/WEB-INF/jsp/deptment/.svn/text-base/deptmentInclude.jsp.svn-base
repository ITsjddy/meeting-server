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
	<script src="${ctx }/static/sysviews/groupAdministrator/js/includelist.js"></script>
</head>
<body  ng-app="groupAdministratorApp" ng-controller="groupAdministratorController" style="width:100%;height:100%;">
	<input type="hidden" id="groupid" value="${groupid}"/>
	<ul class="nav nav-tabs">
		<li><a href="${ctx }dispatcher/department/indexdetial?groupid=${groupid}" >基本信息</a></li>
		<li class="active"><a>子团信息</a></li>
		<li><a href="${ctx }dispatcher/department/indexguest?groupid=${groupid}" >嘉宾信息</a></li>
		<li><a href="${ctx }dispatcher/department/indexserver?groupid=${groupid}" >服务人员</a></li>
	</ul><br/>
	<div id="wrapper">
        <div id="page-wrapper">
        	<div id="dataTables-example_filter" class="dataTables_filter">
				<a href="${ctx }/dispatcher/department/newdepartment?id=${groupid}" class="btn btn-primary" >新建</a>
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
                                       <th>团名称</th>
									   <th>团名称英文</th>
									   <th>上级团</th>
									   <th>团分类</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.departname"></td>
										<td ng-bind="guest.endepartname"></td>
										<td ng-bind="guest.parentname"></td>
										<td ng-if="guest.classification == 'tneib'">内宾团</td>
										<td ng-if="guest.classification != 'tneib'">外宾团</td>
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<security:authorize access="hasRole('ROLE_GUEST_EDIT')">
														<li><a href="${ctx }${path}/department/editdepartment?id={{ guest.id }}&groupid=${groupid}" class="btn btn btn-info">编辑</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_GUEST_EDIT')">
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="guestDelete(guest.id)" class="btn btn-danger">删除</a></li>
													</security:authorize>
												</ul>
									    	</div>
										</td>
									</tr>
								</tbody>
                            </table>
                            <!-- 分页的标签 -->
                            <div style="display:none;">
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
							</div>
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