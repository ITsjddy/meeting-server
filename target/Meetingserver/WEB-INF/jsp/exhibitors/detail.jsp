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
<%-- <c:choose>
	<c:when test="${empty id}">
		<script src="${ctx }/static/sysviews/exhibitors/js/add.js"></script>
	</c:when>
	<c:otherwise> --%>
		<script src="${ctx }/static/sysviews/exhibitors/js/edit.js"></script>
<%-- 	</c:otherwise>
</c:choose> --%>
	
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
<body ng-app="exhibitorsApp" ng-controller="exhibitorsController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li>展商信息详情</li>
</ul>
<div id="wrapper">
	<div id="page-wrapper">
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
				<input type="hidden" ng-model="exhibitors.language"/>
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">语言类型：</label> 
					<div class="col-sm-8"> 
						<input type="checkbox" checked disabled>中文&nbsp;
						<span ng-repeat="language in languageData">
							<input type="checkbox" ng-model="language.languageModel" ng-change="languageOnChange(language.languageModel,language.uneIdent,$index)" ng-checked="language.check" disabled="disabled"/>{{language.name}}&nbsp;
						</span>
					</div> 
	  			</div>
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">邀请码：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="邀请码" class="form-control" required autofocus ng-model="exhibitors.invitationCode" maxlength="220" readonly="readonly"/>
					</div> 
					<label for="firstname" class="col-sm-2 control-label">用户名：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="用户名" class="form-control" autofocus ng-model="exhibitors.userName" maxlength="220" readonly="readonly"/>
					</div> 
					
	  			</div> 
	  			<div class="form-group"> 
	  				<label for="firstname" class="col-sm-2 control-label">手机号：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="手机号" class="form-control" autofocus ng-model="exhibitors.mobile" maxlength="220" readonly="readonly"/>
					</div> 
					<label for="firstname" class="col-sm-2 control-label">email：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="email" class="form-control" autofocus ng-model="exhibitors.email" maxlength="220" readonly="readonly"/>
					</div> 
	  			</div>
	  			<div class="form-group">
	  				<label for="firstname" class="col-sm-2 control-label">性别：</label> 
					<div class="col-sm-3"> 
						男&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="exhibitors.gender" value="1" ng-checked="isSelected(exhibitors.gender)" disabled="disabled"/>&nbsp;&nbsp;
						女&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="exhibitors.gender" value="2" ng-checked="isSelected(exhibitors.gender)" disabled="disabled"/>
					</div> 
	  			</div>
			</div>
			<div class="panel panel-default">
				<ul class="breadcrumb">
					<li>中文信息</li>
				</ul>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">姓名：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="exhibitors.name" maxlength="220" readonly="readonly"/>
					</div> 
	  			</div>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">工作单位：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="工作单位" class="form-control" autofocus ng-model="exhibitors.workPlace" maxlength="220" readonly="readonly"/>
					</div> 
					<label for="firstname" class="col-sm-2 control-label">职位：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="职位" class="form-control" autofocus ng-model="exhibitors.job" maxlength="220" readonly="readonly"/>
					</div> 
	  			</div>
	  			<div class="form-group"> 
	  				<label for="firstname" class="col-sm-2 control-label">单位简介：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="单位简介" class="form-control" autofocus id="unitExplain" name="unitExplain" ng-model="exhibitors.unitExplain" maxlength="220" readonly="readonly"/>
					</div> 
					<label for="firstname" class="col-sm-2 control-label">个人简介：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="个人简介" class="form-control" autofocus id="introduction" name="introduction" ng-model="exhibitors.introduction" maxlength="220" readonly="readonly"/>
					</div> 
	  			</div>
	  			<div class="form-group"> 
	  				<label for="firstname" class="col-sm-2 control-label">联系人：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="联系人" class="form-control" autofocus id="contactPerson" name="contactPerson" ng-model="exhibitors.contactPerson" maxlength="220" readonly="readonly"/>
					</div> 
					<label for="firstname" class="col-sm-2 control-label">联系人电话：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="联系人电话" class="form-control" autofocus id="contactPhone" name="contactPhone" ng-model="exhibitors.contactPhone" maxlength="220" readonly="readonly"/>
					</div> 
	  			</div>
			</div>
				<!-- 内容 -->
			<div ng-repeat="exhibitorslan in lexhibitors">
				<div class="panel panel-default" id="exhibitorsDiv{{$index}}" ng-show="languageData[$index].check">
					<input type="hidden" ng-model="exhibitorslan.language"/>
					<input type="hidden" ng-model="exhibitorslan.check"/>
					<ul class="breadcrumb">
						<li>{{languageData[$index].name}}信息</li>
						<li>
							<a href="javaScript:void(0);" ng-click="delAudienceDiv($index)" >
								<img src="${ctx }/images/minus.png" alt="minus" width="25px" height="25px" />
							</a>
						</li>
					</ul>
		  			<div class="form-group"> 
						<label for="firstname" class="col-sm-2 control-label">姓名({{languageData[$index].name}})：<font color="red">*</font></label> 
						<div class="col-sm-3"> 
							<input type="text" placeholder="姓名" class="form-control" required autofocus ng-model="exhibitorslan.name" maxlength="220" readonly="readonly"/>
						</div> 
						<!-- <label for="firstname" class="col-sm-2 control-label">性别({{languageData[$index].name}})：</label> 
						<div class="col-sm-3"> 
							男&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="exhibitorslan.gender" value="1" ng-checked="isSelected(exhibitorslan.gender)"/>&nbsp;&nbsp;
							女&nbsp;<input type="radio" placeholder="性别" autofocus ng-model="exhibitorslan.gender" value="2" ng-checked="isSelected(exhibitorslan.gender)"/>
						</div>  -->
		  			</div>
		  			<div class="form-group"> 
						<label for="firstname" class="col-sm-2 control-label">工作单位({{languageData[$index].name}})：</label> 
						<div class="col-sm-3"> 
							<input type="text" placeholder="工作单位" class="form-control" autofocus ng-model="exhibitorslan.workPlace" maxlength="220" readonly="readonly"/>
						</div> 
						<label for="firstname" class="col-sm-2 control-label">职务({{languageData[$index].name}})：</label> 
						<div class="col-sm-3"> 
							<input type="text" placeholder="职务" class="form-control" autofocus ng-model="exhibitorslan.job" maxlength="220" readonly="readonly"/>
						</div> 
		  			</div>
		  			<div class="form-group"> 
						<label for="firstname" class="col-sm-2 control-label">单位简介({{languageData[$index].name}})：</label> 
						<div class="col-sm-3"> 
							<input type="text" placeholder="单位简介" class="form-control" autofocus ng-model="exhibitorslan.unitExplain" maxlength="220" readonly="readonly"/>
						</div> 
						<label for="firstname" class="col-sm-2 control-label">个人简介({{languageData[$index].name}})：</label> 
						<div class="col-sm-3"> 
							<input type="text" placeholder="个人简介" class="form-control" autofocus ng-model="exhibitorslan.introduction" maxlength="220" readonly="readonly"/>
						</div> 
		  			</div>
		  			<div class="form-group"> 
	  					<label for="firstname" class="col-sm-2 control-label">联系人({{languageData[$index].name}})：</label> 
						<div class="col-sm-3"> 
							<input type="text" placeholder="联系人" class="form-control" autofocus id="contactPerson" name="contactPerson" ng-model="exhibitorslan.contactPerson" maxlength="220" readonly="readonly"/>
						</div> 
						<label for="firstname" class="col-sm-2 control-label">联系人电话({{languageData[$index].name}})：</label> 
						<div class="col-sm-3"> 
							<input type="text" placeholder="联系人电话" class="form-control" autofocus id="contactPhone" name="contactPhone" ng-model="exhibitorslan.contactPhone" maxlength="220" readonly="readonly" />
						</div> 
	  				</div>
				</div>
			</div>
		</form>
	</div>
	
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
		<!-- <input id="btnSubmit" class="btn btn-primary" type="button" ng-click="exhibitorsSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>

</body>

</html>