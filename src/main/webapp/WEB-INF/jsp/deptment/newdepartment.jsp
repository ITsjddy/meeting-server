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
<script src="${ctx }/static/sysviews/department/js/new.js"></script>	
<script type="text/javascript">
	var treeDialog;
	function tongxunlu(){
		var url = CONTEXT_PATH + '/dispatcher/department/deptztree';
			
			treeDialog =new $.Zebra_Dialog('', {
				'source': {'iframe': {
						'id' : 'pro',
						'name': 'pro',
				        'src':  url,
				        'height': 300
				    }},
			    'type' : '',
			    'width': 300,
			    'title': '团管理树',
			    'buttons': 'none'
			});
	};  
	function yijituan(){
		$('#groupid').val("0");
		$('#redepartname').val("");
	};  
	function selectdept(id,name){
		$('#groupid').val(id);
		$('#redepartname').val(name);
	};
	function treeDialogcloseforcreate(){
		treeDialog.close();
	};
</script>
</head>
<body ng-app="guestApp" ng-controller="guestController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li>新建团</li>
</ul>
<div id="wrapper">
	<form class="form-horizontal" role="form">
		<input id="id" name="id" type="hidden" value="${id}" />
		<input id="groupid" name="groupid" type="hidden" value="${id}" />
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
				<label for="firstname" class="col-sm-2 control-label">团分类：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" name="classification" ng-model="guest.classification">
						<option value="">--请选择--</option>
						<option value="tneib" ng-model="guest.classification" ng-selected="isSelected(guest.classification)">内宾团</option>
						<option value="twaib" ng-model="guest.classification" ng-selected="isSelected(guest.classification)">外宾团</option>
					</select>
				</div>  
				<label for="firstname" class="col-sm-2 control-label">团类型：</label> 
				<div class="col-sm-3"> 
					<select class="form-control" name="type" ng-model="guest.type">
						<option value="">--请选择--</option>
						<option value="tguojzzt" ng-model="guest.type" ng-selected="isSelected(guest.type)">国际组织团</option>
						<option value="tzhengyt" ng-model="guest.type" ng-selected="isSelected(guest.type)">政要团</option>
						<option value="tshengst" ng-model="guest.type" ng-selected="isSelected(guest.type)">省市团</option>
						<option value="qita" ng-model="guest.type" ng-selected="isSelected(guest.type)">其他</option>
					</select>
				</div>   
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">所属团：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="请选择团" class="form-control" autofocus id="redepartname" name="redepartname"  readonly="readonly" onclick="javascript:tongxunlu()" maxlength="220" value="${parentname}"/>
				</div>
				<div class="col-sm-3"> 
					<input id="btnSubmit" class="btn btn-success" type="button" onclick="javascript:yijituan()" value="建立一级团请点击"/>
				</div>   
  			</div>
		</div>
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>中文信息</li>
			</ul>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">团名称(中文)：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="团名称" class="form-control" required autofocus id="departname" name="departname" ng-model="guest.departname" maxlength="220" />
				</div> 
				<label for="firstname" class="col-sm-2 control-label">团简介(中文)：</label> 
				<div class="col-sm-3"> 
					<input type="text" placeholder="团名称英文" class="form-control" autofocus id="endepartname" name="endepartname" ng-model="guest.endepartname" maxlength="220" />
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
					<label for="firstname" class="col-sm-2 control-label">团名称({{languageData[$index].name}})：<font color="red">*</font></label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="团名称" class="form-control" required autofocus id="departname" name="departname" ng-model="guestlan.departname" maxlength="220" />
					</div> 
					<label for="firstname" class="col-sm-2 control-label">团简介({{languageData[$index].name}})：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="团简介" class="form-control" autofocus id="endepartname" name="endepartname" ng-model="guestlan.endepartname" maxlength="220" />
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