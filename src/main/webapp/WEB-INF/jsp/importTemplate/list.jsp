<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>导入模板维护</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/importTemplate/js/list.js"></script>
</head>
<body ng-app="guestApp" ng-controller="guestController">
	<ul class="breadcrumb"><li>导入模板维护</li></ul>
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
                                       <th>模版名称</th>
									   <th>表名</th>
									   <th>导入唯一标识</th>
									   <th>创建时间</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest$index in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest$index.explain"></td>
										<td ng-bind="guest$index.tableName"></td>
										<td>
											<select class="form-control" name="type"   ng-model="guest$index.uniqueIden" ng-change="uniqueIdenchange(guest$index)">
												<option value="invitationCode"  ng-selected="guest$index.uniqueIden == 'invitationCode'">邀请码</option>
												<option value="mobile"  ng-selected="guest$index.uniqueIden == 'mobile'">手机号</option>
												<option value="email"  ng-selected="guest$index.uniqueIden == 'email'">邮箱</option>
											</select>	
										</td>
										<td ng-bind="guest$index.createTimes"></td>
									</tr>
								</tbody>
                            </table>
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