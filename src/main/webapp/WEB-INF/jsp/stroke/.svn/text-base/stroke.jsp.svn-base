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
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/stroke/js/stroke.js"></script>
	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body ng-app="stroketApp" ng-controller="strokeController">
	<ul class="nav nav-tabs">
		<li class="active"><a>嘉宾日程</a></li>
		<li><a href="${ctx }${path}/memberHotel/edit?id=${id}" >酒店信息</a></li>
	</ul><br/>
	<input  id="id" type="hidden" value="${id}"/>
	<input  id="member"  type="hidden" value="${member}"/>
<%-- 	<input  id="memberid"  type="hidden" value="${memberId}"/> --%>
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                  <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
								
								<label>标题:</label>
									<label>
									<input type="search" ng-model="searchParam.stroke_title" class="input-sm">
<!-- 										<input type="search"  ng-model="searchParam.member_name" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
									</label>
									<label>日期:</label>
									<label>
									<input type="search"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="stroke_date" ng-model="searchParam.stroke_date" class="input-sm">
<!-- 										<input type="search"  ng-model="searchParam.member_name" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
									</label>
									
<!-- 									<label>标题: -->
<!-- 										<input type="search"  ng-model="searchParam.stroke_title" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
<!-- 									</label> -->
<!-- 									<label>日期: -->
<!-- 										<input type="search"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" ng-model="searchParam.stroke_date" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
<!-- 									</label> -->
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
									<security:authorize access="hasRole('ROLE_GUEST_ADD')">
										<a href="${ctx }${path}/stroke/edit?id=${id}&ids=&member=${member}" class="btn btn-primary">新建日程</a>
									</security:authorize>
									
										<security:authorize access="hasRole('ROLE_GUEST_ADD')">
										<a href="${ctx }${path}/guest/index" class="btn btn-primary">返回</a>
									</security:authorize>
								</div>
						</div>
                <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
                                       <th>标题</th>
                                       <th>地点</th>
                                        <th>日期</th>
<!--                                           <th>时间</th> -->
                                           <th>操作</th>
									 
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="stroke in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="stroke.title"></td>
										<td ng-bind="stroke.location"></td>
										<td ng-bind="stroke.startStrokedate"></td>
<!-- 										<td ng-bind="stroke.startStroketime"></td> -->
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
												  	<security:authorize access="hasRole('MEM_SCHEDULE_EDIT')">
														<li><a href="${ctx }${path}/stroke/edit?id={{ stroke.id}}&ids=1&member={{ stroke.memberId}}" class="btn btn btn-info">编辑</a></li>
													</security:authorize>
													<security:authorize access="hasRole('MEM_SCHEDULE_DELETE')">
														<li class="divider"></li>
														<li><a href="javaScript:void(0);" ng-click="strokeDelete(stroke.id,stroke.memberId)" class="btn btn-danger">删除</a></li>
													</security:authorize>
													<security:authorize access="hasRole('MEM_SCHEDULE_DETAILS')">
														<li class="divider"></li>
														<li><a href="${ctx }${path}/stroke/details?id={{ stroke.id}}&ids=1&member={{ stroke.memberId}}" class="btn btn btn-info">详情</a></li>
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
<!--                     <div class="panel panel-default"> -->
<!--                         <div class="panel-heading"> -->
<!-- 								<div id="dataTables-example_filter" class="dataTables_filter"> -->
								
<!-- 									<label>邀请码/用户名: -->
<!-- 										<input type="search"  ng-model="searchParam.member_userName" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
<!-- 									</label> -->
<!-- 									<label>手机号: -->
<!-- 										<input type="search"  ng-model="searchParam.member_mobile" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
<!-- 									</label> -->
<!-- 									<label>姓名: -->
<!-- 										<input type="search"  ng-model="searchParam.member_name" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
<!-- 									</label> -->
<!-- 									<label>单位/职务: -->
<!-- 										<input type="search"  ng-model="searchParam.member_workPlace" class="form-control input-sm"  -->
<!-- 											 aria-controls="dataTables-example"> -->
<!-- 									</label> -->
<!-- 									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a> -->
<%-- 									<security:authorize access="hasRole('ROLE_GUEST_ADD')"> --%>
<%-- 										<a href="${ctx }${path}/guest/add" class="btn btn-primary">新建</a> --%>
<%-- 									</security:authorize> --%>
<!-- 								</div> -->
<!-- 						</div> -->
<!--                         /.panel-heading -->
<!--                         <div class="panel-body"> -->
<!--                             <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example"> -->
<!--                                 <thead> -->
<!--                                     <tr> -->
<!--                                        <th>序号</th> -->
<!--                                        <th>邀请码</th> -->
<!-- 									   <th>手机号</th> -->
<!-- 									   <th>用户名</th> -->
<!-- 									   <th>姓名</th> -->
<!-- 									   <th>单位</th> -->
<!-- 									   <th>职务</th> -->
<!-- 									   <th>是否已扫描</th> -->
<!-- 									   <th>操作</th> -->
<!--                                     </tr> -->
<!--                                 </thead> -->
<!--                                 <tbody> -->
<!-- 									<tr ng-repeat="guest in items"> -->
<!-- 										<td><span class="badge">{{ $index + 1 }}</span></td> -->
<!-- 										<td ng-bind="guest.member.invitationCode"></td> -->
<!-- 										<td ng-bind="guest.member.mobile"></td> -->
<!-- 										<td ng-bind="guest.member.userName"></td> -->
<!-- 										<td ng-bind="guest.name"></td> -->
<!-- 										<td ng-bind="guest.workPlace"></td> -->
<!-- 										<td ng-bind="guest.job"></td> -->
<!-- 										<td ng-if="guest.member.whetherScanning == 1">是</td> -->
<!-- 										<td ng-if="guest.member.whetherScanning != 1">否</td> -->
										
<!-- 									</tr> -->
<!-- 								</tbody> -->
<!--                             </table> -->
<!--                             分页的标签 -->
<!-- 							<nav> -->
<!-- 								<tm-pagination conf="paginationConf"></tm-pagination> -->
<!-- 							</nav> -->
<!--                         </div> -->
<!--                         /.panel-body -->
<!--                     </div> -->
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
           
        </div>
        <!-- /#page-wrapper -->
    </div>
</body>
</html>