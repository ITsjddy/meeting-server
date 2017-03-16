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
		<script src="${ctx }/static/sysviews/guest/js/add.js"></script>
	</c:when>
	<c:otherwise>
		<script src="${ctx }/static/sysviews/guest/js/edit.js"></script>
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
<body ng-app="guestApp" ng-controller="guestController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li><strong>
		<c:choose>
		    <c:when test="${empty id}">嘉宾信息添加</c:when>
		    <c:otherwise>嘉宾信息修改</c:otherwise>   
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
			<input type="hidden" ng-model="guest.language"/>
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
					<input type="text" placeholder="邀请码" class="form-control" required autofocus ng-model="guest.invitationCode" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="手机号" class="form-control" autofocus ng-model="guest.mobile" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">email：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="email" class="form-control" autofocus ng-model="guest.email" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="用户名" class="form-control" autofocus ng-model="guest.userName" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
  				<label for="firstname" class="col-sm-2 control-label">是否领导关注：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" ng-model="guest.leadAttention">
						<option value="">--请选择--</option>
						<option value="1" ng-selected="isSelected(guest.leadAttention=='1')">是</option>
						<option value="2" ng-selected="isSelected(guest.leadAttention=='2')">否</option>
					</select>
				</div>
				<label for="firstname" class="col-sm-2 control-label">性别：</label> 
				<div class="col-sm-3"> 
					男&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="guest.gender" value="1" ng-checked="isSelected(guest.gender=='1')"/>&nbsp;&nbsp;
					女&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="guest.gender" value="2" ng-checked="isSelected(guest.gender=='2')"/>
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">证件类型：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" ng-model="guest.idType" >
						<option value="">--请选择--</option>
						<option ng-repeat="idType in idTypes" value="{{idType.uneIdent}}" >{{idType.name}}</option>
					</select>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">证件号：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="证件号" class="form-control" autofocus ng-model="guest.documentNumber" maxlength="220" />
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
					<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="guest.name" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">民族(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="民族" class="form-control" autofocus ng-model="guest.nation" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">单位(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="单位" class="form-control" autofocus ng-model="guest.workPlace" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">职务(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="职务" class="form-control" autofocus ng-model="guest.job" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">国籍(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="国籍" class="form-control" autofocus ng-model="guest.nationality" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">籍贯(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="籍贯" class="form-control" autofocus ng-model="guest.nativePlace" maxlength="220" />
				</div> 
  			</div>
		</div>
		<!-- 内容 -->
		<div ng-repeat="guestlan in lguest">
			<div class="panel panel-default" id="guestDiv{{$index}}" ng-show="languageData[$index].check">
				<input type="hidden" ng-model="guestlan.language"/>
				<input type="hidden" ng-model="guestlan.check"/>
				<ul class="breadcrumb">
					<li>{{languageData[$index].name}}信息</li>
					<li>
						<a href="javaScript:void(0);" ng-click="delGuestDiv($index)" >
							<img src="${ctx }/images/minus.png" alt="minus" width="25px" height="25px" />
						</a>
					</li>
				</ul>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">姓名({{languageData[$index].name}})：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="guestlan.name" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">民族({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="民族" class="form-control" autofocus ng-model="guestlan.nation" maxlength="220" />
					</div>
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">单位({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="单位" class="form-control" autofocus ng-model="guestlan.workPlace" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">职务({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="职务" class="form-control" autofocus ng-model="guestlan.job" maxlength="220" />
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">国籍({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="国籍" class="form-control" autofocus ng-model="guestlan.nationality" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">籍贯({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="籍贯" class="form-control" autofocus ng-model="guestlan.nativePlace" maxlength="220" />
					</div> 
	  			</div>
			</div>
		</div>
	</form>
	
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
		<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="guestSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>

</body>

</html>