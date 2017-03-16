<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>系统用户列表</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 多选删除js -->
	<script src="${ctx }/static/checkbox/js/deletes.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/sysuser/js/list.js"></script>
	
</head>
<body ng-app="sysUserApp" ng-controller="sysUserController">
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                	<ul class="breadcrumb"><li>系统用户列表</li></ul>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>姓名/联系电话:</label>
									<label>
										<input type="search" ng-model="searchParam.sysUser_name" class="input-sm" >
									</label>
									<label>用户名:</label>
									<label>
										<input type="search" ng-model="searchParam.sysUser_userName" class="input-sm" >
									</label>
									<label>是否启用:</label>
									<label>
										<select ng-model="searchParam.sysUser_state" class="form-control">
											<option value=""></option>
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
									</label>
									<label>权限:</label>
									<label>
										<select ng-model="searchParam.sysUser_roleId" class="form-control">
											<option value=""></option>
											<option ng-repeat="role in lSysRole" value="{{role.id}}" >{{role.name}}</option>
										</select>
									</label>
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
									<security:authorize access="hasRole('ROLE_SYS_ROLE_ADD')">
										<a href="${ctx }${path}/sysUser/add" class="btn btn-primary" >新建</a>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_SYS_ROLE_ADD')">
										<a href="javaScript:void(0)" class="btn btn-danger" ng-click="deletes()" >删除</a>
									</security:authorize>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                   	   <th><input type="checkbox" ng-model="selectModel" ng-change="checkAll(selectModel)" ></th>
									   <th>用户名</th>
									   <th>姓名/联系电话</th>
									   <th>是否启用</th>
									   <th>所属权限</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="user in items">
										<td><input type="checkbox" ng-model="user.check" ng-change="idSelect(user.id,user.check)" ></td>
										<td ng-bind="user.userName"></td>
										<td>{{user.name}}/{{user.mobile}}</td>
										<td ng-if="user.state == 1" style="color:blue;">是</td>
										<td ng-if="user.state != 1" style="color:red;">否</td>
										<td>
											<span ng-repeat="role in lSysRole">
												<span ng-if="user.roleId == role.id">{{role.name}}</span>
											</span>
										</td>
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<security:authorize access="hasRole('ROLE_SYS_ROLE_EDIT')">
														<li><a href="${ctx }${path}/sysUser/edit?id={{ user.id }}" class="btn btn btn-info">编辑</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_SYS_ROLE_DELETE')">
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="sysUserDelete(user.id)" class="btn btn-danger">删除</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_SYS_ROLE_DETAILS')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/sysUser/details?id={{ user.id }}" class="btn btn btn-info">详情</a></li>
													</security:authorize>
													<security:authorize access="hasRole('ROLE_SYS_ROLE_DETAILS')">
														<li class="divider"></li>
														<li><a data-toggle="modal" data-target="#myPassword" ng-click="editPassword(user.id,user.userName)" class="btn btn btn-info">修改密码</a></li>
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
		<div class="modal fade" id="myPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<form class="form-horizontal" role="form" ng-submit="savePassword()">
			    <div class="modal-dialog">
			        <div class="modal-content">
			        	<div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
			            </div>
			            <div class="modal-body">
			            	用户名： <input type="text" name="userName" ng-model="puserName" readonly>
							新密码： <input type="text" name="password" ng-model="ppassword" required
									oninvalid="setCustomValidity('密码 只能由数字、字母或_下划线组成，长度要在5到15之间！');" oninput="setCustomValidity('');" pattern="^[0-9a-zA-Z_]{5,15}$">
						</div>
			            <div class="modal-footer">
			                <button type="submit" class="btn btn-primary">提交更改</button>
			                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			            </div>
			        </div>
			    </div>
		    </form>
		</div>
		
    </div>
</body>
</html>