<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html style="overflow-x: hidden; overflow-y: auto;">

	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta name="author" content="ms" />
		<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />
		<link rel="stylesheet" href="${ctx }/css/ztree/zTreeStyle.css" type="text/css">
		<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
		<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${ctx }/js/ztree/jquery.ztree.excheck.js"></script>
		<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
		<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
		<script src="${ctx }/static/sysviews/smsTemplate/js/pushSms.js"></script>
		<title>推送</title>
	</head>

	<body ng-app="pushSmsPage" ng-controller="pushSmsCtlr">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form">
					<div class="panel panel-default">
						<ul class="breadcrumb">
							<li>推送</li>
						</ul>
						<input type="hidden" value="${id}" id="spValue" />
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">服务人员：</label>
							<div class="col-sm-8">
								<textarea id="insiderNames" disabled rows="5"  style="background-color:#FEFFD6;cursor:pointer;width:500px;"></textarea>
								<input type="hidden" ng-model="pusms.smspStbers" /> 
								 &nbsp;&nbsp; &nbsp;&nbsp;
								<a class="btn btn-primary" data-toggle="modal" ng-click="insiderButton()">添加 </a> &nbsp;&nbsp;
								<a class="btn btn-primary" ng-click="deleteNames('#insiderNames')"> 删除  </a>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">APP用户：</label>
							<div class="col-sm-8">
								<textarea id="outNames" disabled rows="5" style="background-color:#FEFFD6;cursor:pointer;width:500px;"></textarea>
								<input type="hidden" ng-model="pusms.smspGuests" /> 
								 &nbsp;&nbsp; &nbsp;&nbsp;
								<a class="btn btn-primary" data-toggle="modal" ng-click="outsiderButton('guest')">添加 </a> &nbsp;&nbsp;
								<a class="btn btn-primary" ng-click="deleteNames('#outNames')"> 删除 </a>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">手机号：</label>
							<div class="col-sm-8">
								<textarea class="isPhones" ng-model="pusms.smsphoneNums" rows="6" style="background-color:#FEFFD6;width:500px;"></textarea>
								&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn btn-primary"> 删除 </a>
							</div>
							<label class=" col-sm-6 control-label" style="color:red"> 多个手机号用英文逗号‘,’隔开; 例如： 135********,133******** </label>
						</div>
					</div>
				</form>
				<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
						&nbsp;&nbsp; &nbsp;&nbsp;
						<a href="javaScript:void(0)" class="btn btn-primary" ng-click="pushSms()">推送</a>
					</div>
				</div>
			</div>
		</div>
		
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
			<div class="modal-dialog" style="width:900px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">人员添加</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div id="dataTables-example_filter" class="dataTables_filter">
										<label>人员类型：&nbsp;&nbsp;</label>
										<label>
											<select class="form-control" ng-init="personForTpye.id = personType[0].id" ng-model="member.id" ng-change="creatMember(member.id)">
													<option ng-repeat="person in personType" value="{{person.id}}" >{{person.value}}</option>
											</select>
										</label>
										<label>姓名：&nbsp;&nbsp;<input type="search" ng-model="member.name" class="input-sm"></label>
										
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a>
									</div>
									<div class="panel-body">
											<table id="spTable" class="table table-striped table-bordered table-hover " style="text-align:center">
											 <thead>
                                    <tr>
                                       <th  style="text-align:center"><input type="checkbox" name="checkAll" id="checkAll" ng-model="checkAll" ng-checked="checkAll" ng-click="checkAllItem($event)"></th>
                                       <th  style="text-align:center">序号</th>
                                       <th  style="text-align:center">邀请码</th>
									   <th  style="text-align:center">手机号</th>
									   <th  style="text-align:center">用户名</th>
									   <th  style="text-align:center">姓名</th>
									   <th  style="text-align:center">单位</th>
									   <th  style="text-align:center">职务</th>
                                    </tr>
                                </thead>				
                                <tbody>
									<tr ng-repeat="guest in items" >
										<td><input type="checkbox" name="checkIt" value="{{guest.member.memberId}}" ng-checked="isSelected(guest.member.memberId)" ng-click="updateSelection($event,guest.member.memberId,guest.name)"></td>
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="guest.member.invitationCode"></td>
										<td ng-bind="guest.member.mobile"></td>
										<td ng-bind="guest.member.userName"></td>
										<td ng-bind="guest.name"></td>
										<td ng-bind="guest.workPlace"></td>
										<td ng-bind="guest.job"></td>
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
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" ng-click="appUserConfirmButton()">确定</button>
						<button type="button" class="btn btn-default" ng-click="appUserCloseButton()">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="insiderModal" tabindex="-1" role="dialog" aria-labelledby="insiderModalLabel" aria-hidden="false">
			<div class="modal-dialog" style="width:600px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">人员添加</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div id="dataTables-example_filter" class="dataTables_filter">
									<label>服务人员姓名：&nbsp;&nbsp;<input type="search" ng-model="searchValue" class="input-sm"></label>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchForService()">查询</a>
									</div>
									<div class="panel-body">
										<div  style="width:500px;text-align:left" >
								        	<input type="hidden" id="i" name="i" value="${i}">
											<ul id="treeDemo" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" ng-click="serviceConfirmButton()">确定</button>
						<button type="button" class="btn btn-default" ng-click="serviceCloseButton()">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</body>

</html>