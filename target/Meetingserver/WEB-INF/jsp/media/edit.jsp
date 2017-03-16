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
<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>

<c:choose>
	<c:when test="${empty id}">
		<script src="${ctx }/static/sysviews/media/js/add.js"></script>
	</c:when>
	<c:otherwise>
		<script src="${ctx }/static/sysviews/media/js/edit.js"></script>
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
<body ng-app="mediaApp" ng-controller="mediaController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li><strong>
		<c:choose>
		    <c:when test="${empty id}">媒体信息添加</c:when>
		    <c:otherwise>媒体信息修改</c:otherwise>   
		</c:choose>
	</strong></li>
</ul>
<div id="wrapper">
	<form class="form-horizontal" role="form">
		<input id="id" name="id" type="hidden" value="${id}" />
		<!-- 列表查询条件 -->
		<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
		<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
		<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>基本信息</li>
			</ul>
			
			<input type="hidden" ng-model="media.language"/>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：</label> 
				<div class="col-sm-8"> 
					<input type="checkbox" checked disabled>中文&nbsp;
					<span ng-repeat="language in languageData">
						<input type="checkbox" ng-model="language.languageModel" ng-change="languageOnChange(language.languageModel,language.uneIdent,$index)" ng-checked="language.check"/>{{language.name}}&nbsp;
					</span>
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">邀请码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="邀请码" class="form-control" required autofocus ng-model="media.invitationCode" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="手机号" class="form-control" autofocus ng-model="media.mobile" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">email：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="email" class="form-control" autofocus ng-model="media.email" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="用户名" class="form-control" autofocus ng-model="media.userName" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">性别：</label> 
				<div class="col-sm-3"> 
					男&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="media.gender" value="1" ng-checked="isSelected(media.gender=='1')"/>&nbsp;&nbsp;
					女&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="media.gender" value="2" ng-checked="isSelected(media.gender=='2')"/>
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">证件类型：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" ng-model="media.idType" >
						<option value="">--请选择--</option>
						<option ng-repeat="idType in idTypes" value="{{idType.uneIdent}}" >{{idType.name}}</option>
					</select>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">证件号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="证件号" class="form-control" autofocus ng-model="media.documentNumber" maxlength="220" />
				</div> 
  			</div>
		</div>
		
		
			<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>中文信息</li>
			</ul>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">姓名(中文)：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="media.name" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">民族(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="民族" class="form-control" autofocus ng-model="media.nation" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">单位(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="单位" class="form-control" autofocus ng-model="media.workPlace" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">职务(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="职务" class="form-control" autofocus ng-model="media.job" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">国籍(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="国籍" class="form-control" autofocus ng-model="media.nationality" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">籍贯(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="籍贯" class="form-control" autofocus ng-model="media.nativePlace" maxlength="220" />
				</div> 
  			</div>
		</div>
		<!-- 内容 -->
		<div ng-repeat="medialan in lmedia">
			<div class="panel panel-default" id="mediaDiv{{$index}}" ng-show="languageData[$index].check">
				<input type="hidden" ng-model="medialan.language"/>
				<input type="hidden" ng-model="medialan.check"/>
				<ul class="breadcrumb">
					<li>{{languageData[$index].name}}信息</li>
					<li>
						<a href="javaScript:void(0);" ng-click="delMediaDiv($index)" >
							<img src="${ctx }/images/minus.png" alt="minus" width="25px" height="25px" />
						</a>
					</li>
				</ul>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">姓名({{languageData[$index].name}})：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="medialan.name" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">民族({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="民族" class="form-control" autofocus ng-model="medialan.nation" maxlength="220" />
					</div>
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">单位({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="单位" class="form-control" autofocus ng-model="medialan.workPlace" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">职务({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="职务" class="form-control" autofocus ng-model="medialan.job" maxlength="220" />
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">国籍({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="国籍" class="form-control" autofocus ng-model="medialan.nationality" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">籍贯({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="籍贯" class="form-control" autofocus ng-model="medialan.nativePlace" maxlength="220" />
					</div> 
	  			</div>
			</div>
		</div>
	</form>
		
		
<%-- <div id="wrapper">
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
					<select class="form-control" name="language" ng-model="media.language">
						<option value="1" ng-model="media.language" ng-selected="isSelected(media.language)">中文</option>
						<option value="2" ng-model="media.language" ng-selected="isSelected(media.language)">英文</option>
					</select>
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">邀请码：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="邀请码" class="form-control" required autofocus id="invitationCode" name="invitationCode" ng-model="media.invitationCode" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="手机号" class="form-control" autofocus id="mobile" name="mobile" ng-model="media.mobile" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">email：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="email" class="form-control" autofocus id="email" name="email" ng-model="media.email" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="用户名" class="form-control" autofocus id="userName" name="userName" ng-model="media.userName" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">姓名：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="姓名" class="form-control" required autofocus id="name" name="name" ng-model="media.name" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">性别：</label> 
				<div class="col-sm-3"> 
					男&nbsp;<input type="radio" placeholder="性别" autofocus id="gender" name="gender" ng-model="media.gender" value="1" ng-checked="isSelected(media.gender)"/>&nbsp;&nbsp;
					女&nbsp;<input type="radio" placeholder="性别" autofocus id="gender" name="gender" ng-model="media.gender" value="2" ng-checked="isSelected(media.gender)"/>
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">单位：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="单位" class="form-control" autofocus id="workPlace" name="workPlace" ng-model="media.workPlace" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">职务：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="职务" class="form-control" autofocus id="job" name="job" ng-model="media.job" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">证件类型：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" name="idType" ng-model="media.idType">
						<option value="">--请选择--</option>
						<option value="shenfenzheng" ng-model="media.idType" ng-selected="isSelected(media.idType)">身份证</option>
						<option value="huzhao" ng-model="media.idType" ng-selected="isSelected(media.idType)">护照</option>
					</select>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">证件号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="证件号" class="form-control" autofocus id="documentNumber" name="documentNumber" ng-model="media.documentNumber" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">国籍：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="国籍" class="form-control" autofocus id="nationality" name="nationality" ng-model="media.nationality" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">籍贯：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="籍贯" class="form-control" autofocus id="nativePlace" name="nativePlace" ng-model="media.nativePlace" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">民族：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="民族" class="form-control" autofocus id="nation" name="nation" ng-model="media.nation" maxlength="220" />
				</div> 
  			</div>
		</form>
	</div>
	--%>
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
		<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="mediaSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>

</body>

</html>