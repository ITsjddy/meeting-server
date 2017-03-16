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
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx }/js/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
<title>info</title>
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
		<script src="${ctx }/static/sysviews/stroke/js/details.js">
		</script>
<script type="text/javascript">
	$(document).ready(function() {
// 		$('.datepicker').datepicker();
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
<body ng-app="strokeApp" ng-controller="strokeController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li>
		<c:choose>
		    <c:when test="${empty id}">嘉宾日程添加</c:when>
		    <c:otherwise>嘉宾日程修改</c:otherwise>   
		</c:choose>
	</li>
</ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form">
			<input id="id" name="id" type="hidden" value="${id}" />
			
			<input id="ids" name="ids" type="hidden" value="${ids}" />
			<input id="member" name="member" type="hidden" value="${member}" />
<%-- 			<input id="memberids" name="memberids" type="hidden" value="${memberId}" /> --%>
			<!-- 列表查询条件 -->
<%-- 			<input  id="id"  type="hidden" value="${id}" /> --%>
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
			<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
			
<!-- 			<div class="form-group">  -->
<!-- 				<label for="firstname" class="col-sm-2 control-label">语言类型：<font color="red">*</font></label>  -->
<!-- 				<div class="col-sm-8">  -->
<!-- 					<select class="form-control" name="language" ng-model="stroke.language"> -->
<!-- 						<option value="1" ng-model="stroke.language" ng-selected="isSelected(stroke.language)">中文</option> -->
<!-- 						<option value="2" ng-model="stroke.language" ng-selected="isSelected(stroke.language)">英文</option> -->
<!-- 					</select> -->
<!-- 				</div>  -->
<!--   			</div> -->
.		<input type="hidden" ng-model="stroke.language"/>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：</label> 
				<div class="col-sm-8"> 
					<input type="checkbox" checked disabled>中文&nbsp;
					<span ng-repeat="language in languageData">
						<input type="checkbox" checked disabled ng-model="language.languageModel" ng-change="languageOnChange(language.languageModel,language.uneIdent,$index)" ng-checked="language.check"/>{{language.name}}&nbsp;
					</span>
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">标题(中文)：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="标题" readonly="readonly" class="form-control" required autofocus id="title" name="title" ng-model="stroke.title" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">地点(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="地点" readonly="readonly" class="form-control" autofocus id="location" name="location" ng-model="stroke.location" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">开始日期：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="日期"  readonly="readonly" class="form-control" autofocus id="startStrokedate" name="startStrokedate" ng-model="stroke.startStrokedate" maxlength="220" />
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间"  readonly="readonly" class="form-control" autofocus id="startStroketime" name="startStroketime" ng-model="stroke.startStroketime" maxlength="220" /> -->
<!-- 				</div>  -->
				<label for="firstname" class="col-sm-2 control-label">结束日期：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="日期" readonly="readonly"  class="form-control" autofocus id="stopStrokedate" name="stopStrokedate" ng-model="stroke.stopStrokedate" maxlength="220" />
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间" readonly="readonly"  class="form-control" autofocus id="stopStroketime" name="stopStroketime" ng-model="stroke.stopStroketime" maxlength="220" /> -->
<!-- 				</div>  -->
  			</div>
  			<div class="form-group" style="display: none;"> 
  				<div class="col-sm-3"> 
				<input type="text" placeholder="memberId" class="form-control" autofocus id="memberId" name="memberId" ng-model="stroke.memberId" maxlength="220"/>
  			</div>
  			</div>
<!-- 			<div class="form-group"  id="caozuo1">  -->
<!-- 				<div class="col-sm-offset-2 col-sm-10">  -->
<!-- 					<button type="button" class="btn btn-default" ng-click="guestSave()">保存</button>&nbsp;&nbsp; -->
<!-- 					<button type="button" class="btn btn-default" ng-click="index()">返回</button>  -->
<!-- 				</div>  -->
<!-- 			</div>   -->
			<div ng-repeat="strokelan in lguest">
			<div class="panel panel-default" id="guestDiv{{$index}}" ng-show="languageData[$index].check">
				<input type="hidden" ng-model="strokelan.language"/>
				<input type="hidden" ng-model="strokelan.check"/>
				<ul class="breadcrumb">
					<li>{{languageData[$index].name}}信息</li>
					<li>
						<a href="javaScript:void(0);" ng-click="delGuestDiv($index)" >
							<img src="${ctx }/images/minus.png" alt="minus" width="25px" height="25px" />
						</a>
					</li>
				</ul>
	  			<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">标题({{languageData[$index].name}})：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="标题" readonly="readonly" class="form-control" required autofocus id="title" name="title" ng-model="strokelan.title" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">地点({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="地点" readonly="readonly" class="form-control" autofocus id="location" name="location" ng-model="strokelan.location" maxlength="220" />
					</div>
	  			</div>
	  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">开始日期({{languageData[$index].name}})：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="日期" readonly="readonly"  class="form-control" autofocus id="startStrokedate" name="startStrokedate" ng-model="stroke.startStrokedate" maxlength="220" />
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间" readonly="readonly"  class="form-control" autofocus id="startStroketime" name="startStroketime" ng-model="stroke.startStroketime" maxlength="220" /> -->
<!-- 				</div>  -->
				<label for="firstname" class="col-sm-2 control-label">结束日期({{languageData[$index].name}})：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="日期" readonly="readonly"  class="form-control" autofocus id="stopStrokedate" name="stopStrokedate" ng-model="stroke.stopStrokedate" maxlength="220" />
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间" readonly="readonly"  class="form-control" autofocus id="stopStrokedate" name="stopStrokedate" ng-model="stroke.stopStrokedate" maxlength="220" /> -->
<!-- 				</div>  -->
  			</div>
	  			<div class="form-group" style="display: none;"> 
  				<div class="col-sm-3"> 
				<input type="text" placeholder="memberId" class="form-control" autofocus id="memberId" name="memberId" ng-model="strokelan.memberId" maxlength="220"/>
  			</div>
  			</div>
			</div>
		</div>
	<c:choose>
		<c:when test="${empty ids}">
		
		</c:when>
		<c:otherwise>
		<div class="form-group"  id="caozuo1"> 
				<div class="col-sm-offset-2 col-sm-10"> 
<!-- 					<button type="button" class="btn btn-default" ng-click="strokeSavest()">保存</button>&nbsp;&nbsp; -->
					<button type="button" class="btn btn-default" ng-click="index()">返回</button> 
				</div> 
			</div>  
			</c:otherwise>
	</c:choose>
		</form>
	</div>
	<c:choose>
		<c:when test="${empty ids}">
		<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10"> 
				<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="strokeSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
		</c:when>
		<c:otherwise>
		
			</c:otherwise>
	</c:choose>
<!-- 	<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;"> -->
<!-- 		<div class="col-sm-offset-2 col-sm-10">  -->
<!-- 		<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="servicePersonnelSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>

</body>

</html>