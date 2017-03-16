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
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
		<script src="${ctx }/static/sysviews/stroke/js/hotel.js">
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
<ul class="nav nav-tabs">
		
		<li><a href="${ctx }${path}/stroke/indexs?id=${id}" >嘉宾日程</a></li>
		<li class="active"><a>酒店信息</a></li>
	</ul><br/>
<div id="wrapper">
	<div id="page-wrapper">
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
			
			<div class="form-group"> 
				 
  			</div>
			<div class="form-group"> 
			<label for="firstname" class="col-sm-2 control-label">酒店名称：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<select class="form-control" name="language" required autofocus  ng-model="hotel.hotelId">
						<option value="">--请选择--</option>
						<option ng-selected ="ho.id==hotel.hotelId" ng-repeat="ho in lhotel" value="{{ho.id}}">{{ho.spType}}</option>
<!-- 						<option value="2" ng-model="guest.language" ng-selected="isSelected(guest.language)">英文</option> -->
					</select>
				</div>
<!-- 				<label for="firstname" class="col-sm-2 control-label">酒店名称<font color="red">*</font></label>  -->
<!-- 				<div class="col-sm-3">  -->
<!-- 					<input type="text" placeholder="标题" class="form-control" required autofocus id="hotelName" name="hotelName" ng-model="hotel.hotelName" maxlength="220" /> -->
<!-- 				</div>  -->
				<label for="firstname" class="col-sm-2 control-label">酒店地点</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="地点" class="form-control" required  autofocus id="hotelAddress" name="hotelAddress" ng-model="hotel.hotelAddress" maxlength="220" />
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">房间号</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="房间号" class="form-control" required  autofocus id="roomNum" name="roomNum" ng-model="hotel.roomNum" maxlength="220" />
				</div> 
					<label for="firstname" class="col-sm-2 control-label">特殊要求</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="特殊要求" class="form-control"  autofocus id="specialRequirements" name="specialRequirements" ng-model="hotel.specialRequirements" maxlength="220" />
				</div> 
				 
  			</div>
  			<div class="form-group"> 
  			<label for="firstname" class="col-sm-2 control-label">入住时间</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="入住时间" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" required  autofocus id="checkInTime" name="checkInTime" ng-model="hotel.checkInTime" maxlength="220" />
				</div>
				<label for="firstname" class="col-sm-2 control-label">离店时间</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="离店时间" class="form-control" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" required  autofocus id="checkOutTime" name="checkOutTime" ng-model="hotel.checkOutTime" maxlength="220" />
				</div> 
  			</div>
  			<div class="form-group" style="display: none;" > 
  				<div class="col-sm-3"> 
				<input type="text" placeholder="memberId" class="form-control" autofocus id="memberId" name="memberId" ng-model="hotel.memberId" maxlength="220"/>
  			</div>
  			</div>
<!-- 			<div class="form-group"  id="caozuo1">  -->
<!-- 				<div class="col-sm-offset-2 col-sm-10">  -->
<!-- 					<button type="button" class="btn btn-default" ng-click="guestSave()">保存</button>&nbsp;&nbsp; -->
<!-- 					<button type="button" class="btn btn-default" ng-click="index()">返回</button>  -->
<!-- 				</div>  -->
<!-- 			</div>   -->
<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;">
			<div class="col-sm-offset-2 col-sm-10"> 
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
			</div>
		</div>
		</form>
	</div>
<!-- 	<div class="form-group breadcrumb" id="caozuo" style="width:100%;float:left;margin:10px;"> -->
<!-- 		<div class="col-sm-offset-2 col-sm-10">  -->
<!-- 		<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="servicePersonnelSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>

</body>

</html>