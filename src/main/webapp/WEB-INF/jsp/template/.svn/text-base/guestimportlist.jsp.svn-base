<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache"> 
	<META HTTP-EQUIV="Expires" CONTENT="0"> 
	<title>导入字段列表</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/template/js/guestimportlist.js"></script>
</head>
<body  ng-app="memberListApp" ng-controller="memberListController">
	<input id="arrIds" type="hidden" value="">
	<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                <div ng-repeat="id in selectedname" >
					<input type="hidden" name="ids" value="{{id}}">
				</div>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th width="5%"></th>
                                       <th>模版字段名称</th>
									   <th>实体字段名称</th>
									   <th>说明</th>
									   <th>创建时间</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="guest in items" on-finish-render-filters>
										<td ng-if="guest.isMust=='1'"><input checked disabled type="checkbox" name="checkIt" value="{{guest.id}}" ng-checked="isSelected(guest.id)" ng-click="updateSelection($event,guest.id,guest.templateName)"></td>
										<td ng-if="guest.isMust!='1'"><input type="checkbox" name="checkIt" value="{{guest.id}}" ng-checked="isSelected(guest.id)" ng-click="updateSelection($event,guest.id,guest.templateName)"></td>
										<td ng-bind="guest.templateName"></td>
										<td ng-bind="guest.fieldName"></td>
										<td ng-bind="guest.explain"></td>
										<td ng-bind="guest.createTimes"></td>
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