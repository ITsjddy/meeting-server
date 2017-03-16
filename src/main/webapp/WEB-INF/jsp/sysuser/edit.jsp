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
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
<c:choose>
	<c:when test="${empty id}">
		<script src="${ctx }/static/sysviews/sysuser/js/add.js"></script>
	</c:when>
	<c:otherwise>
		<script src="${ctx }/static/sysviews/sysuser/js/edit.js"></script>
	</c:otherwise>
</c:choose>
</head>
<body ng-app="sysUserApp" ng-controller="sysUserController" style="width:100%;height:100%;margin:10px">
	<ul class="breadcrumb">
		<li>
			<c:choose>
			    <c:when test="${empty id}">
					系统用户信息添加 
			    </c:when>
			    <c:otherwise>
			      	系统用户信息修改
			    </c:otherwise>   
			</c:choose>
		</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" ng-submit="sysUserSave()">
				<input id="id" name="id" type="hidden" value="${id}" />
				<!-- 列表查询条件 -->
				<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
				<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
				<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
				
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">用户名：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" oninput="setCustomValidity('');" pattern="^[0-9a-zA-Z]{5,15}$" 
							id="userName" name="userName" placeholder="用户名" class="form-control" required autofocus ng-model="sysUser.userName" maxlength="20" />
					</div> 
	  			</div> 
	  			<c:if test="${empty id}">
		  			<div class="form-group"> 
						<label for="firstname" class="col-sm-2 control-label">密码：<font color="red">*</font></label> 
						<div class="col-sm-3"> 
							<input type="text" oninvalid="setCustomValidity('密码 只能由数字、字母或_下划线组成，长度要在5到15之间！');" oninput="setCustomValidity('');" pattern="^[0-9a-zA-Z_]{5,15}$" 
								placeholder="密码" class="form-control" required autofocus ng-model="sysUser.password" maxlength="20" />
						</div> 
		  			</div>
	  			</c:if>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">姓名：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="sysUser.name" maxlength="20" />
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">联系电话：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="联系电话" class="form-control" autofocus ng-model="sysUser.mobile" maxlength="20" />
					</div> 
	  			</div>
	  			<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label">权限：<font color="red">*</font></label>
					<div class="col-sm-3"> 
						<select class="form-control" ng-model="sysUser.roleId" required autofocus>
							<option value="">--请选择--</option>
							<option ng-repeat="role in lSysRole" value="{{role.id}}" >{{role.name}}</option>
						</select>
					</div>
				</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">是否启用：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="radio" name="state" value="1" ng-model="sysUser.state" ng-checked="isSelected(sysUser.state=='1')" required/>&nbsp;是&nbsp;&nbsp;
						<input type="radio" name="state" value="0" ng-model="sysUser.state" ng-checked="isSelected(sysUser.state=='0')" required/>&nbsp;否
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">备注：</label> 
					<div class="col-sm-3"> 
						<textarea placeholder="备注" rows="5" class="form-control" autofocus maxlength="900" ng-model="sysUser.remark"></textarea>
					</div> 
	  			</div>
	  			<!-- 保存、返回 -->
	  			<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10"> 
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
					</div>
				</div>
			</form>
		</div>
		
		
	</div>
</body>
</html>