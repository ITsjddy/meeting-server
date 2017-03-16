<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html>
<html style="overflow-x:hidden;overflow-y:auto;">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="author" content="ms"/>
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />

<title>info</title>
<style type="text/css">
/* query style */
.inputQry{
	background:url(${ctx}/images/query.png) no-repeat 180px 3px;
	background-color:#FEFFD6;
	cursor:pointer;
	width: 200px;
}
</style>
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
<script src="${ctx }/static/sysviews/guest/js/serviceStaffing.js"></script>
	
<script type="text/javascript">
	$(document).ready(function() {
		
		roleKey.oninput=function(){   
			roleKey.setCustomValidity("");   
		};   
		roleKey.oninvalid=function(){ 
			roleKey.setCustomValidity("权限 只能由数字、字母、_组成！");   
		};
	});
	
	function checkNull(){
		var ValState = $('#inputForm').valid(); 
		if(ValState == true){
			$("#btnSubmit").attr("disabled",true);
		}
	}
	
</script>
</head>
<body ng-app="guestApp" ng-controller="guestController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li><strong>服务人员设置管理</strong></li>
</ul>
<div id="wrapper">
	<form class="form-horizontal" role="form">
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>嘉宾基本信息</li>
			</ul>
			<input id="id" name="id" type="hidden" value="${id}" />
			<!-- 列表查询条件 -->
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
			<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
			
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">邀请码：</label> 
				<div class="col-sm-3"> 
					<input class="form-control" type="text" ng-model="guest.invitationCode" readonly/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input class="form-control" type="text" ng-model="guest.mobile" readonly/>
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">姓名：</label> 
				<div class="col-sm-3"> 
					<input class="form-control" type="text" ng-model="guest.name" readonly/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">性别：</label> 
				<div class="col-sm-3"> 
					男&nbsp;<input type="radio" ng-checked="guest.gender=='1'" readonly/>&nbsp;&nbsp;
					女&nbsp;<input type="radio" ng-checked="guest.gender=='2'" readonly/>
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">单位：</label> 
				<div class="col-sm-3"> 
					<input class="form-control" type="text" ng-model="guest.workPlace" readonly/>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">职务：</label> 
				<div class="col-sm-3"> 
					<input class="form-control" type="text" ng-model="guest.job" readonly/>
				</div> 
  			</div>
		</div>
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li >服务人员:</li>
			</ul>
			<div ng-repeat="guestSer in lguestSer">
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">{{guestSer.typeName}}：</label> 
					<div class="col-sm-6"> 
						<input id="serid{{$index}}" type="hidden" ng-model="guestSer.servicePersonnelId" />
						<input id="sername{{$index}}" class="input-sm inputQry" type="text" ng-click="servicePerTree($index)" ng-model="guestSer.servicePersonnelName" 
							value="{{guestSer.servicePersonnelName}}" title="选择{{guestSer.typeName}}" readonly/>&nbsp;&nbsp;
						<a href="javaScript:void(0);" ng-click="delService($index)" title="删除{{guestSer.typeName}}">删除</a>
					</div> 
	  			</div> 
			</div>
		</div>
		
		<!-- 选择服务人员 -->
		<%-- <div class="modal fade" id="myServicePersonnel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width:500px;height:450px;" >
		        <div class="modal-content">
		           <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">服务人员tree列表</h4>
		            </div>
		            <iframe src="${ctx }${path}/guest/servicePersonnelTree" style="width:500px;height:450px;">
                    </iframe>
		           <!--  <div class="modal-body">在这里添加一些文本</div> -->
		            <div class="modal-footer">
		                <button id="closeModalButton" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div> 
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div> --%>
	</form>
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
		<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="servicePersonnelSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>

</body>

</html>