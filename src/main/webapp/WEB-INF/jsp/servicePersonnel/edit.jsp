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
<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
<c:choose>
	<c:when test="${empty id}">
		<script src="${ctx }/static/sysviews/servicePersonnel/js/adds.js">
		
		$("#caozuo").show();
		</script>
	</c:when>
	<c:otherwise>
		<script src="${ctx }/static/sysviews/servicePersonnel/js/edits.js">
// 		alert(2);
		$("#caozuo1").show();
		</script>
	</c:otherwise>
</c:choose>
</head>
<body ng-app="servicePersonnelApp" ng-controller="servicePersonnelController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li>
		<c:choose>
		    <c:when test="${empty id}">服务人员添加</c:when>
		    <c:otherwise>服务人员信息修改</c:otherwise>   
		</c:choose>
	</li>
</ul>
<div id="wrapper">
	<div id="page-wrapper">
	<form class="form-horizontal" role="form" ng-submit="servicePersonnelSave()">
		
			<input id="id" name="id" type="hidden" value="${id}" />
			<!-- 列表查询条件 -->
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
			<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
			
<!-- 			<div class="form-group">  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">语言类型：<font color="red">*</font></label>  -->
<!-- 				<div class="col-sm-8">  -->
<!-- 					<select class="form-control" name="language" ng-model="guest.language"> -->
<!-- 						<option value="1" ng-model="guest.language" ng-selected="isSelected(guest.language)">中文</option> -->
<!-- 						<option value="2" ng-model="guest.language" ng-selected="isSelected(guest.language)">英文</option> -->
<!-- 					</select> -->
<!-- 				</div>  -->
<!--   			</div> -->
<%-- 			<c:choose> --%>
<%--   			<c:when test="${empty id}"> --%>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">邀请码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
				<input type="text" id="invitationCode" placeholder="邀请码" class="form-control" oninput="setCustomValidity('');"
						required autofocus ng-model="service.invitationCode" maxlength="220" <c:if test="${not empty id}">readonly</c:if>/>
<!-- 					<input type="text" placeholder="邀请码" class="form-control" required autofocus id="invitationCode" name="invitationCode" ng-model="service.invitationCode" maxlength="220" /> -->
<!-- 					<span id="shortnameError" style="color: #ea5200;font-weight: bold;">用户名必须以 fw_ 开头</span> -->
				</div> 
				<c:if test="${empty id}">
					<label for="firstname" class="col-sm-2 control-label">密码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
				<input type="text" oninvalid="setCustomValidity('密码 只能由数字、字母或_下划线组成，长度要在5到15之间！');" oninput="setCustomValidity('');" pattern="^[0-9a-zA-Z_]{5,15}$"
							required autofocus placeholder="密码 只能由数字、字母或_下划线组成，长度要在5到15之间！" class="form-control" ng-model="service.password" maxlength="20" />
<!-- 					<input type="text" placeholder="密码" class="form-control" required autofocus id="password" name="password" ng-model="service.password" maxlength="220" /> -->
				</div> 
				</c:if>
  			</div> 
  			<div class="form-group"> 
  			
				<label for="firstname" class="col-sm-2 control-label">用户名：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" id="userName" placeholder="用户名" class="form-control" autofocus ng-model="service.userName" maxlength="220" oninput="setCustomValidity('');"/>
<!-- 				<input type="text" id="userName" placeholder="用户名" class="form-control" oninput="setCustomValidity('');" -->
<%-- 						required autofocus ng-model="service.userName" maxlength="220" <c:if test="${not empty id}">readonly</c:if>/> --%>
<!-- 						<input type="text" id="userName" placeholder="用户名" class="form-control" autofocus ng-model="service.userName" maxlength="220" oninput="setCustomValidity('');"/> -->
				</div>
				<label for="firstname" class="col-sm-2 control-label">姓名：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="姓名" class="form-control" autofocus id="name" name="name" ng-model="service.name" maxlength="220"  onblur="validateAccount()"/>
				
<!-- 				<input type="text" id="name" placeholder="姓名" class="form-control" required autofocus ng-model="service.name" maxlength="220" /> -->
<!-- 						<input type="text" id="userName" placeholder="用户名" class="form-control" autofocus ng-model="service.userName" maxlength="220" oninput="setCustomValidity('');"/> -->
				</div>
  			</div>
  			<div class="form-group"> 
  			<label for="firstname" class="col-sm-2 control-label">个人简介：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="个人简介" class="form-control" autofocus id="introduction" name="introduction" ng-model="service.introduction" maxlength="220"  onblur="validateAccount()"/>
<!-- 					<input type="text" placeholder="个人简介" class="form-control" required autofocus id="introduction" name="introduction" ng-model="service.introduction" maxlength="220" /> -->
				</div> 
  			<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="手机号" class="form-control" autofocus id="mobile" name="mobile" ng-model="service.mobile" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
  			<label for="firstname" class="col-sm-2 control-label">email：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="email" class="form-control" autofocus id="email" name="email" ng-model="service.email" maxlength="220" />
				</div> 
				
				<label for="firstname" class="col-sm-2 control-label">单位：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="单位" class="form-control" autofocus id="company" name="company" ng-model="service.company" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">所属团：</label> 
				<div class="col-sm-3"> 
					<input id="groupidId" type="hidden" ng-model="service.groupId" />
						<input id="groupnameId" class=" inputQry" type="text" ng-model="service.groupName" 
							value="{{service.groupName}}" title="选择所属团" data-toggle="modal" data-target="#myGroup" readonly/>&nbsp;&nbsp;
						<a href="javaScript:void(0);" ng-click="delGroup()" title="删除所属团">删除</a>
				</div> 
				
  			</div>
		<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10"> 
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
		<!-- 选择团 -->
		<div class="modal fade" id="myGroup" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width:500px;height:450px;" >
		        <div class="modal-content">
		           <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">团tree列表</h4>
		            </div>
		            <iframe src="${ctx }${path}/servicePersonnel/groupTree" style="width:500px;height:450px;">
                    </iframe>
		           <!--  <div class="modal-body">在这里添加一些文本</div> -->
		            <div class="modal-footer">
		                <button id="closeModalButton" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		            </div> 
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div>
<!-- 		<div class="form-group"  id="caozuo1">  -->
<!-- 				<div class="col-sm-offset-2 col-sm-10">  -->
<!-- 					<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;&nbsp; -->
<!-- 					<button type="button" class="btn btn-default" ng-click="index()">返回</button>  -->
<!-- 				</div>  -->
<!-- 			</div>   -->
<%-- 			</c:otherwise> --%>
<%-- 	</c:choose> --%>
		</form>
	</div>
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${empty id}"> --%>
		
<%-- 		</c:when> --%>
<%-- 		<c:otherwise> --%>
		
<%-- 			</c:otherwise> --%>
<%-- 	</c:choose> --%>
<!-- 	<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;"> -->
<!-- 		<div class="col-sm-offset-2 col-sm-10">  -->
<!-- 		<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="servicePersonnelSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>

</body>

</html>