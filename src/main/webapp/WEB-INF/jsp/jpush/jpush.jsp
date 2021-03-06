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
		<script src="${ctx }/static/sysviews/jpush/js/jpush.js"></script>

		<title>操作</title>
	</head>

	<body ng-app="addJpushEntiy" ng-controller="jpAddCtlr">
		<div id="wrapper">
			<div id="page-wrapper">
				<form class="form-horizontal" role="form" name="form" ng-submit="addJpushEntiy()">
					<div class="panel panel-default">
						<ul class="breadcrumb">
							<li>极光推送</li>
						</ul>
						<hr />
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">广播：</label>
							<div class="radio col-sm-4">
								<input value="yes" type="checkbox" ng-model="jpushEntiy.broadCast" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">服务人员：</label>
							<div  class="col-sm-8">
								<textarea id="insiderNames"   disabled rows="5" style="background-color:#FEFFD6;cursor:pointer;width:550px;"></textarea>
								<input type="hidden" ng-model="jpushEntiy.insiderIds" /> 
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a class="btn btn-primary" data-toggle="modal" ng-click="insiderButton()">添加 </a> &nbsp;&nbsp;
								<a class="btn btn-primary" ng-click="deleteNames('#insiderNames')"> 删除  </a>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">APP用户：</label>
							<div  class="col-sm-8">
								<textarea  disabled rows="5" style="background-color:#FEFFD6;cursor:pointer;width:550px;" id="outNames"></textarea>
								<input type="hidden" ng-model="jpushEntiy.outsiderIds" /> 
								&nbsp;&nbsp;&nbsp;&nbsp;
								<!-- <a data-toggle="modal" class="btn btn-primary" data-target="#myModal">添加嘉宾</a> -->
								<a class="btn btn-primary" data-toggle="modal" ng-click="outsiderButton('guest')">添加 </a> &nbsp;&nbsp;
								<a class="btn btn-primary" ng-click="deleteNames('#outNames')"> 删除 </a>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文标题：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" required  autofocus ng-model="jpushEntiy.jpTitle" maxlength="200">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ds_host">中文正文：</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" required  autofocus ng-model="jpushEntiy.jpText" maxlength="200">
							</div>
						</div>
						<div class="form-group ">
							<!-- <hr/> -->
							<label class="col-md-2 control-label" for="ds_host">其他语言：</label>
							<div class="col-md-10">
								<div ng-repeat="topic in spAddressNames">
									<div class="form-group">
										<div ng-bind="topicMark=$index" style="display:none"></div>
										<div class="col-sm-3">
											<select class="form-control"  id="topicType{{$index + 1}}" ng-model="topic.languageId" ng-change="fchat.topicOnChange({{$index+1}},topic.languageId)">
													<option value="">请选择</option>
													<option ng-repeat="type in topicTypesData" value="{{type.dDLogo}}" >{{type.dDName}}</option>
											</select>
										</div>
										<div class="col-md-2 img-link">
											<a href="" ng-click="fchat.incrTopic($index)" ng-show="fchat.canIncrTopic">
												<img src="${ctx }/images/plus.png" alt="plus" width="30px" height="30px" />
											</a>
											<img src="${ctx }/images/dis_plus.png" alt="minus" width="30px" height="30px" ng-show="!fchat.canIncrTopic" />
											<a href="" ng-click="fchat.decrTopic($index)" ng-show="fchat.canDescTopic">
												<img src="${ctx }/images/minus.png" alt="minus" width="30px" height="30px" />
											</a>
											<img src="${ctx }/images/dis_minus.png" alt="minus" width="30px" height="30px" ng-show="!fchat.canDescTopic" />
										</div>
									</div>
									<div id="optionsDiv{{topicMark+1}}" style="display: none;">
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">标题：</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" ng-if="topic.languageId.length > 0" required  autofocus ng-model="topic.jpTitle" id="topic{{topicMark+1}}Option{{$index + 1}}" />
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-1 control-label" for="ds_host">正文：</label>
											<div class="col-sm-5">
												<input type="text" class="form-control" ng-if="topic.languageId.length > 0" required  autofocus ng-model="topic.jpText" id="topic{{topicMark+11}}Option{{$index + 1}}" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- <hr/> -->
					</div>
					<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
						<div class="col-sm-offset-2 col-sm-10">
						<input class="btn btn-primary" style="width: 160px;" type="submit" value="推送" />
						</div>
					</div>
				</form>
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