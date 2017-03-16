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
		<script src="${ctx }/static/sysviews/jpush/jpushPage.js"></script>

		<title>操作</title>

		<body ng-app="addJpushEntiy" ng-controller="jpAddCtlr">
			<div id="wrapper">
				<div id="page-wrapper">
					<form class="form-horizontal" role="form">
						<fieldset>
							<legend>
								极光推送-推送消息添加
							</legend>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">广播：</label>
								<div class="radio col-sm-4">
									<input value="yes" type="checkbox"  ng-model="jpushEntiy.broadCast" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host"> 内部人员：</label>
								<div class="radio col-sm-6">
									<textarea id="insiderNames" ng-model="jpushEntiy.insiderIds" style="background-color:#FEFFD6;cursor:pointer;width:300px;"></textarea>
									<input type="hidden" ng-model="jpushEntiy.insiderIds"/> &nbsp;&nbsp;
									<a class="btn btn-primary" data-toggle="modal" ng-click="insiderButton()">人员添加 </a> &nbsp;&nbsp;
									<a class="btn btn-primary" ng-click= "deleteNames('#insiderNames')"> 删除  </a> &nbsp;&nbsp;&nbsp;&nbsp;全选 
									<input value="yes" type="checkbox" name="huiwu" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ds_host">外部人员：</label>
								<div class="radio col-sm-6">
									<textarea style="background-color: #FEFFD6; cursor: pointer; width: 300px;"  id="outNames"></textarea>
									<input type="hidden" ng-model="jpushEntiy.outsiderIds"  /> &nbsp;&nbsp;
									<a class="btn btn-primary" data-toggle="modal" ng-click="outsiderButton()"> 人员添加 </a> &nbsp;&nbsp;
									<a class="btn btn-primary" ng-click= "deleteNames('#outNames')"> 删除 </a> &nbsp;&nbsp;&nbsp;&nbsp;全选 
									<input value="yes" id="huiyi" type="checkbox" name="huiyi" />
								</div>
							</div>
							<div ng-repeat="jpushTitleText in languages">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="ds_host">{{jpushTitleText.languageId}}标题
							<label  class="control-label" style="font-size:12px;color:red;">{必填}</label>：</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" ng-model="jpushTitleText.jpTitle" maxlength="200">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="ds_host">{{jpushTitleText.languageId}}正文
							<label  class="control-label" style="font-size:12px;color:red;">{必填}</label>：</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" ng-model="jpushTitleText.jpText" maxlength="200">
									</div>
								</div>
							</div>
							<div class="col-sm-offset-2 col-sm-10"> 
								<a class="btn btn-primary"  ng-click="addJpushEntiy()">保存</a>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
			
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
											<label>公司名称：&nbsp;&nbsp;<input type="search"  class="input-sm"></label> 
											<label>人员姓名：&nbsp;&nbsp;<input type="search"  class="input-sm"></label> 
											<label>电话：&nbsp;&nbsp;<input type="search"  class="input-sm"></label> 
											&nbsp;&nbsp;<a href="javaScript:void(0)" class="btn btn-primary" ng-click="">查询</a> 
										</div>
										<div class="panel-body">
								<table id="spTable" class="table table-striped table-bordered table-hover " style="text-align:center" >
									<thead>
										<tr >
											<th style="text-align:center"><input value="yes" type="checkbox" name="huiwu" /></th>
											<th style="text-align:center">序号</th>
											<th style="text-align:center">公司名称</th>
											<th style="text-align:center">人员姓名</th>
											<th style="text-align:center">电话</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat=" in items">
											<td style="width:2%"><input value="yes" type="checkbox" name="huiwu" /></td>
											<td style="width:5%">{{ $index + 1 }}</td>
											<td style="width:10%" ng-bind=""></td>
											<td style="width:6%" ng-bind=""></td>
											<td style="width:10%" ng-bind=""></td>
											
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
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary">确定</button>
		            </div>
		        </div>
		    </div>
		</div>
		</body>

</html>