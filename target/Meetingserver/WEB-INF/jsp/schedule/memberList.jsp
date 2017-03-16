<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Expires" CONTENT="0"> 
	<title>
		<c:if test="${type =='guest' }">嘉宾列表</c:if>
		<c:if test="${type =='exhibitors' }">展商列表</c:if>
		<c:if test="${type =='media' }">媒体列表</c:if>
		<c:if test="${type =='audience' }">观众列表</c:if>
		<c:if test="${type =='servicePersonnel' }">服务人员列表</c:if>
	</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/schedule/js/memberList.js"></script>
</head>
<body  ng-app="memberListApp" ng-controller="memberListController">
	<input id="arrIds" type="hidden" value="">
	<input id="type" type="hidden" value="${type }">
	<div id="wrapper">
        <div id="page-wrapper">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>姓名:<input type="search"  ng-model="searchParam.name"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
								</div>
								
								<div ng-repeat="id in selected" >
									<input type="hidden" name="ids" value="{{id}}">
								</div>
						</div>
						<c:if test="${type =='guest' }">
							  <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th width="5%"><input type="checkbox" name="checkAll" id="checkAll" ng-model="checkAll" ng-checked="checkAll" ng-click="checkAllItem($event)"></th>
                                       <!-- <th width="5%"><input type="checkbox" ng-click="checkAllItem($event)"></th> -->
                                       <th>序号</th>
                                       <th>邀请码</th>
									   <th>手机号</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>单位</th>
									   <th>职务</th>
									   <!-- <th>操作</th> -->
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items" on-finish-render-filters>
									<!-- <tr ng-repeat="guest in items"> -->
										<td><input type="checkbox" name="checkIt" value="{{guest.id}}" ng-checked="isSelected(guest.id)" ng-click="updateSelection($event,guest.id)"></td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<!-- <td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">												  	
													
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="groupAdministratorDelete(guest.id)" class="btn btn-danger">删除</a></li>
																			
												</ul>
									    	</div>
										</td> -->
									</tr>
								</tbody>
                            </table>
                            <!-- 分页的标签 -->
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
                        </div>
                        <!-- /.panel-body -->
						</c:if>
						
						<c:if test="${type =='exhibitors' }">
								  <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th width="5%"><input type="checkbox" name="checkAll" id="checkAll" ng-model="checkAll" ng-checked="checkAll" ng-click="checkAllItem($event)"></th>
                                       <!-- <th width="5%"><input type="checkbox" ng-click="checkAllItem($event)"></th> -->
                                       <th>序号</th>
                                       <th>邀请码</th>
									   <th>手机号</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>单位</th>
									   <th>职务</th>
									   <!-- <th>操作</th> -->
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items" on-finish-render-filters>
									<!-- <tr ng-repeat="guest in items"> -->
										<td><input type="checkbox" name="checkIt" value="{{guest.id}}" ng-checked="isSelected(guest.id)" ng-click="updateSelection($event,guest.id)"></td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<!-- <td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">												  	
													
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="groupAdministratorDelete(guest.id)" class="btn btn-danger">删除</a></li>
																			
												</ul>
									    	</div>
										</td> -->
									</tr>
								</tbody>
                            </table>
                            <!-- 分页的标签 -->
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
                        </div>
                        <!-- /.panel-body -->
						</c:if>
						<c:if test="${type =='media' }">
								  <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th width="5%"><input type="checkbox" name="checkAll" id="checkAll" ng-model="checkAll" ng-checked="checkAll" ng-click="checkAllItem($event)"></th>
                                       <!-- <th width="5%"><input type="checkbox" ng-click="checkAllItem($event)"></th> -->
                                       <th>序号</th>
                                       <th>邀请码</th>
									   <th>手机号</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>单位</th>
									   <th>职务</th>
									   <!-- <th>操作</th> -->
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items" on-finish-render-filters>
									<!-- <tr ng-repeat="guest in items"> -->
										<td><input type="checkbox" name="checkIt" value="{{guest.id}}" ng-checked="isSelected(guest.id)" ng-click="updateSelection($event,guest.id)"></td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<!-- <td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">												  	
													
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="groupAdministratorDelete(guest.id)" class="btn btn-danger">删除</a></li>
																			
												</ul>
									    	</div>
										</td> -->
									</tr>
								</tbody>
                            </table>
                            <!-- 分页的标签 -->
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
                        </div>
                        <!-- /.panel-body -->
						</c:if>
						<c:if test="${type =='audience' }">
								  <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th width="5%"><input type="checkbox" name="checkAll" id="checkAll" ng-model="checkAll" ng-checked="checkAll" ng-click="checkAllItem($event)"></th>
                                       <!-- <th width="5%"><input type="checkbox" ng-click="checkAllItem($event)"></th> -->
                                       <th>序号</th>
                                       <th>邀请码</th>
									   <th>手机号</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>单位</th>
									   <th>职务</th>
									   <!-- <th>操作</th> -->
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items" on-finish-render-filters>
									<!-- <tr ng-repeat="guest in items"> -->
										<td><input type="checkbox" name="checkIt" value="{{guest.id}}" ng-checked="isSelected(guest.id)" ng-click="updateSelection($event,guest.id)"></td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<!-- <td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">												  	
													
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="groupAdministratorDelete(guest.id)" class="btn btn-danger">删除</a></li>
																			
												</ul>
									    	</div>
										</td> -->
									</tr>
								</tbody>
                            </table>
                            <!-- 分页的标签 -->
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
                        </div>
                        <!-- /.panel-body -->
						</c:if>
						<c:if test="${type =='servicePersonnel' }">
								  <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th width="5%"><input type="checkbox" name="checkAll" id="checkAll" ng-model="checkAll" ng-checked="checkAll" ng-click="checkAllItem($event)"></th>
                                       <!-- <th width="5%"><input type="checkbox" ng-click="checkAllItem($event)"></th> -->
                                       <th>序号</th>
                                       <th>邀请码</th>
									   <th>手机号</th>
									   <th>用户名</th>
									   <th>姓名</th>
									   <th>单位</th>
									   <th>职务</th>
									   <!-- <th>操作</th> -->
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items" on-finish-render-filters>
									<!-- <tr ng-repeat="guest in items"> -->
										<td><input type="checkbox" name="checkIt" value="{{guest.id}}" ng-checked="isSelected(guest.id)" ng-click="updateSelection($event,guest.id)"></td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<!-- <td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">												  	
													
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="groupAdministratorDelete(guest.id)" class="btn btn-danger">删除</a></li>
																			
												</ul>
									    	</div>
										</td> -->
									</tr>
								</tbody>
                            </table>
                            <!-- 分页的标签 -->
							<nav>
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
                        </div>
                        <!-- /.panel-body -->
						</c:if>
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