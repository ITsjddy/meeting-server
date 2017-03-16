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
		<script src="${ctx }/static/sysviews/sysrole/js/add.js"></script>
	</c:when>
	<c:otherwise>
		<script src="${ctx }/static/sysviews/sysrole/js/edit.js"></script>
	</c:otherwise>
</c:choose>
	
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
<body ng-app="sysRoleApp" ng-controller="sysRoleController" style="width:100%;height:100%;margin:10px">
	<ul class="breadcrumb">
		<li>
			<c:choose>
			    <c:when test="${empty id}">
					角色信息添加 
			    </c:when>
			    <c:otherwise>
			      	角色信息修改
			    </c:otherwise>   
			</c:choose>
		</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<input id="id" name="id" type="hidden" value="${id}" />
				<!-- 列表查询条件 -->
				<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
				<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
				<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
				
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">名称：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="名称" class="form-control" required autofocus id="name" name="name" ng-model="sysRole.name" maxlength="220" />
					</div> 
	  			</div> 
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">备注：</label> 
					<div class="col-sm-3"> 
						<textarea placeholder="备注" class="form-control" autofocus rows="3" cols="3" id="remark" name="remark" maxlength="900" ng-model="sysRole.remark"></textarea>
					</div> 
	  			</div>
			</form>
		</div>
		
		<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10"> 
				<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="sysRoleSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
	</div>
</body>
</html>