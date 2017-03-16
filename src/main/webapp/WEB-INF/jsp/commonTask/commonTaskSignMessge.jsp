<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>任务签到设置</title>
<!-- 模块js -->
<script src="${ctx }/static/sysviews/commonTask/js/taskSign.js"></script>
</head>
<body ng-app="commonTaskApp" ng-controller="commonTaskController" style="width: 100%; height: 100%; margin: 10px">
	<ul class="breadcrumb">
		<li>任务签到设置</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" novalidate="novalidate"
				name="form" ng-submit="commonTaskSign()">
				<input type="hidden" value="${id }" id="id">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">任务名称/唯一标识:<font color="red">*</font></label>
						<label class="col-sm-2">{{commonTask.name }}/{{commonTask.uniqueCode }}</label>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">嘉宾:</label>
						<div class="col-sm-3"> 
							<a data-toggle="modal" class="btn btn-primary" data-target="#myModal">添加嘉宾</a>
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"></label>
						<div class="col-sm-8 b_r_div" style="overflow: auto;">
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>序号</th>
										<th>邀请码</th>
										<th>手机号</th>
										<th>用户名</th>
										<th>姓名</th>
										<th>单位</th>
										<th>职务</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="guest in guests">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown"
													class="btn dropdown-toggle btn-primary">
													操作 <span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
	
													<li class="divider"></li>
													<li><a href="javaScript:void(0);"
														ng-click="deleteFromIds(guest.id)" class="btn btn-danger">删除</a></li>
	
												</ul>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
							<input class="btn btn-primary" type="submit" value="保存" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<!-- 嘉宾选人 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 800px; height: 600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">论坛选参会嘉宾</h4>
				</div>
				<iframe name="selectMember" id="selectMember"
					src="${ctx }/dispatcher/schedule/toMemberList"
					style="width: 800px; height: 400px;"> </iframe>
				<!--  <iframe name="selectMember" id="selectMember" src="" style="width:1070px;height:860px;">
                    </iframe> -->
				<!--  <div class="modal-body">在这里添加一些文本</div> -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						ng-click="getSelectedItem()">确认选择</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>