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
	<c:when test="${empty ids}">
		<script src="${ctx }/static/sysviews/stroke/js/adds.js">
		</script>
	</c:when>
	<c:otherwise>
		<script src="${ctx }/static/sysviews/stroke/js/edits.js">
		</script>
	</c:otherwise>
</c:choose>
</head>
<body ng-app="strokeApp" ng-controller="strokeController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li><strong>
			<c:choose>
		    <c:when test="${empty id}">嘉宾日程添加</c:when>
		    <c:otherwise>嘉宾日程修改</c:otherwise>   
		</c:choose>
	</strong></li>
</ul>
<div id="wrapper">
	<form class="form-horizontal" role="form" ng-submit="strokeSave()">
		<input id="id" name="id" type="hidden" value="${id}" />
			
			<input id="ids" name="ids" type="hidden" value="${ids}" />
			<input id="member" name="member" type="hidden" value="${member}" />
<%-- 			<input id="memberids" name="memberids" type="hidden" value="${memberId}" /> --%>
			<!-- 列表查询条件 -->
<%-- 			<input  id="id"  type="hidden" value="${id}" /> --%>
			<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}" /> 
			<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}" />
			<input id="sys_name" type="hidden" name="sys_name" value="${sys_name}" />
			
		<div class="panel panel-default">
			<input type="hidden" ng-model="stroke.language"/>
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
				<label for="firstname" class="col-sm-2 control-label">标题(中文)：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="标题" class="form-control" required autofocus  ng-model="stroke.title" maxlength="220" />
<!-- 					<input type="text" placeholder="标题" class="form-control" required autofocus id="title" name="title" ng-model="stroke.title" maxlength="220" /> -->
				</div> 
				<label for="firstname" class="col-sm-2 control-label">地点(中文)：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="地点" class="form-control" required autofocus ng-model="stroke.location" maxlength="220" />
<!-- 					<input type="text" placeholder="地点" class="form-control" autofocus id="location" name="location" ng-model="stroke.location" maxlength="220" /> -->
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">开始日期：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="开始日期"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" required autofocus id="startStrokedate"  ng-model="stroke.startStrokedate"maxlength="220" />
<!-- 					<input type="text" placeholder="日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" autofocus id="startStrokedate" name="startStrokedate" ng-model="stroke.startStrokedate" maxlength="220" /> -->
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间" onclick="WdatePicker({dateFmt:' HH:mm:ss'})" class="form-control" autofocus id="startStroketime" name="startStroketime" ng-model="stroke.startStroketime" maxlength="220" /> -->
<!-- 				</div>  -->
				<label for="firstname" class="col-sm-2 control-label">结束时间：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="开始日期"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" required autofocus id="stopStrokedate" ng-model="stroke.stopStrokedate"maxlength="220" />
<!-- 					<input type="text" placeholder="结束日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="form-control" autofocus id="stopStrokedate" name="stopStrokedate" ng-model="stroke.stopStrokedate" maxlength="220" /> -->
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间" onclick="WdatePicker({dateFmt:' HH:mm:ss'})" class="form-control" autofocus id="stopStroketime" name="stopStroketime" ng-model="stroke.stopStroketime" maxlength="220" /> -->
<!-- 				</div>  -->
  			</div>
  			<div class="form-group" style="display: none;"> 
  				<div class="col-sm-3"> 
				<input type="text" placeholder="memberId" class="form-control" autofocus id="memberId"  ng-model="stroke.memberId" maxlength="220"/>
  			</div>
  			</div>
			
		</div>
		<!-- 内容 -->
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
					<span ng-if="languageData[$index].check == true">
					<input type="text" placeholder="标题" class="form-control" required autofocus ng-model="strokelan.title" maxlength="220" />
					</span>
<!-- 						<input type="text" placeholder="标题" class="form-control"  id="title"  ng-model="strokelan.title" maxlength="220" /> -->
					</div> 
					<label for="firstname" class="col-sm-2 control-label">地点({{languageData[$index].name}})：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
					<span ng-if="languageData[$index].check == true">
						<input type="text" placeholder="地点" class="form-control" required autofocus ng-model="strokelan.location" maxlength="220" />
						</span>
<!-- 						<input type="text" placeholder="地点" class="form-control"  id="location"  ng-model="strokelan.location" maxlength="220" /> -->
					</div>
	  			</div>
	  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">开始日期({{languageData[$index].name}})：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="日期" readonly="readonly"  class="form-control"  id="startStrokedate"  ng-model="stroke.startStrokedate" maxlength="220" />
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间" readonly="readonly"  class="form-control" autofocus id="startStroketime" name="startStroketime" ng-model="stroke.startStroketime" maxlength="220" /> -->
<!-- 				</div>  -->
				<label for="firstname" class="col-sm-2 control-label">结束日期({{languageData[$index].name}})：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="日期" readonly="readonly"  class="form-control"  id="stopStrokedate"  ng-model="stroke.stopStrokedate" maxlength="220" />
				</div> 
<!-- 				<div class="col-sm-1">  -->
<!-- 				<input type="text" placeholder="时间" readonly="readonly"  class="form-control" autofocus id="stopStrokedate" name="stopStrokedate" ng-model="stroke.stopStrokedate" maxlength="220" /> -->
<!-- 				</div>  -->
  			</div>
	  			<div class="form-group" style="display: none;"> 
  				<div class="col-sm-3"> 
				<input type="text" placeholder="memberId" class="form-control"  id="memberId"  ng-model="strokelan.memberId" maxlength="220"/>
  			</div>
  			</div>
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

</body>

</html>