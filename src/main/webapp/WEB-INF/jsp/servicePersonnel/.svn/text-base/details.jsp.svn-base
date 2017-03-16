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
		<script src="${ctx }/static/sysviews/servicePersonnel/js/details.js">
		
		</script>
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
		<form class="form-horizontal" role="form">
			<input id="id" name="id" type="hidden" value="${id}" />
			<!-- 列表查询条件 -->
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
			<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
			
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：<font color="red">*</font></label> 
				<div class="col-sm-8"> 
					<select class="form-control" name="language" ng-model="service.language">
						<option value="1" ng-model="service.language" ng-selected="isSelected(service.language)">中文</option>
						<option value="2" ng-model="service.language" ng-selected="isSelected(service.language)">英文</option>
					</select>
					<input type="text" readonly="readonly" placeholder="邀请码" class="form-control" required autofocus id="language" name="language" ng-model="service.language" maxlength="220" />
					
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">邀请码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="邀请码" class="form-control" required autofocus id="invitationCode" name="invitationCode" ng-model="service.invitationCode" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input type="text" readonly="readonly" placeholder="手机号" class="form-control" autofocus id="mobile" name="mobile" ng-model="service.mobile" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">email：</label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="email" class="form-control" autofocus id="email" name="email" ng-model="service.email" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="用户名" class="form-control" autofocus id="userName" name="userName" ng-model="service.userName" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
  			<label for="firstname" class="col-sm-2 control-label">密码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="密码" class="form-control" required autofocus id="password" name="password" ng-model="service.password" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">姓名：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="姓名" class="form-control" required autofocus id="name" name="name" ng-model="service.name" maxlength="220" />
				</div> 
<!-- 				<label for="firstname" class="col-sm-2 control-label">性别：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					男&nbsp;<input type="radio" placeholder="性别" autofocus id="gender" name="gender" ng-model="guest.gender" value="1" ng-checked="isSelected(guest.gender)"/>&nbsp;&nbsp; -->
<!-- 					女&nbsp;<input type="radio" placeholder="性别" autofocus id="gender" name="gender" ng-model="guest.gender" value="2" ng-checked="isSelected(guest.gender)"/> -->
<!-- 				</div>  -->
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">个人简介：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="个人简介" class="form-control" required autofocus id="introduction" name="introduction" ng-model="service.introduction" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">所属团：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="所属团" class="form-control" required autofocus id="groupId" name="groupId" ng-model="service.groupId" maxlength="220" />
				</div> 
<!-- 				<label for="firstname" class="col-sm-2 control-label">性别：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					男&nbsp;<input type="radio" placeholder="性别" autofocus id="gender" name="gender" ng-model="guest.gender" value="1" ng-checked="isSelected(guest.gender)"/>&nbsp;&nbsp; -->
<!-- 					女&nbsp;<input type="radio" placeholder="性别" autofocus id="gender" name="gender" ng-model="guest.gender" value="2" ng-checked="isSelected(guest.gender)"/> -->
<!-- 				</div>  -->
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">单位：</label> 
				<div class="col-sm-3"> 
					<input type="text"  readonly="readonly" placeholder="单位" class="form-control" autofocus id="companyId" name="companyId" ng-model="service.companyId" maxlength="220" />
				</div> 
<!-- 				<label for="firstname" class="col-sm-2 control-label">职务：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<input type="text" placeholder="职务" class="form-control" autofocus id="job" name="job" ng-model="guest.job" maxlength="220" /> -->
<!-- 				</div>  -->
  			</div>
<!--   			<div class="form-group">  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">证件类型：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<select class="form-control" name="idType" ng-model="guest.idType"> -->
<!-- 						<option value="">--请选择--</option> -->
<!-- 						<option value="shenfenzheng" ng-model="guest.idType" ng-selected="isSelected(guest.idType)">身份证</option> -->
<!-- 						<option value="huzhao" ng-model="guest.idType" ng-selected="isSelected(guest.idType)">护照</option> -->
<!-- 					</select> -->
<!-- 				</div>  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">证件号：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<input type="text" placeholder="证件号" class="form-control" autofocus id="documentNumber" name="documentNumber" ng-model="guest.documentNumber" maxlength="220" /> -->
<!-- 				</div>  -->
<!--   			</div> -->
<!--   			<div class="form-group">  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">是否领导关注：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<select class="form-control" name="leadAttention" ng-model="guest.leadAttention"> -->
<!-- 						<option value="">--请选择--</option> -->
<!-- 						<option value="1" ng-model="guest.leadAttention" ng-selected="isSelected(guest.leadAttention)">是</option> -->
<!-- 						<option value="0" ng-model="guest.leadAttention" ng-selected="isSelected(guest.leadAttention)">否</option> -->
<!-- 					</select> -->
<!-- 				</div>  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">民族：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<input type="text" placeholder="民族" class="form-control" autofocus id="nation" name="nation" ng-model="guest.nation" maxlength="220" /> -->
<!-- 				</div>  -->
<!--   			</div> -->
<!--   			<div class="form-group">  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">国籍：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<input type="text" placeholder="国籍" class="form-control" autofocus id="nationality" name="nationality" ng-model="guest.nationality" maxlength="220" /> -->
<!-- 				</div>  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">籍贯：</label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<input type="text" placeholder="籍贯" class="form-control" autofocus id="nativePlace" name="nativePlace" ng-model="guest.nativePlace" maxlength="220" /> -->
<!-- 				</div>  -->
<!--   			</div> -->

			
		</form>
	</div>
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${empty id}"> --%>
		
<%-- 		</c:when> --%>
<%-- 		<c:otherwise> --%>
		<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10"> 
<!-- 				<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="servicePersonnelSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
<%-- 			</c:otherwise> --%>
<%-- 	</c:choose> --%>
	
</div>

</body>

</html>