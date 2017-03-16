<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html style="overflow-x: hidden; overflow-y: auto;">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="author" content="ms" />
<meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />

<title>info</title>
<link href="${ctx}/static/jsTree/themes/default/style.min.css" rel="stylesheet"/>
<script src="${ctx}js/json2.js" type="text/javascript" type="text/javascript"></script>
<script src="${ctx}/static/jsTree/jstree.min.js" type="text/javascript"></script>
<script src="${ctx }/static/sysviews/sysgroup/js/role.js" type="text/javascript"></script>

<script type="text/javascript">
	// 提交表单
	function submit_authchecked() {
		if(get_authchecked()=='yes'){
			return;
		}
		$('#authchecked').attr("value", get_authchecked());
		$('#inputForm').submit();
	}
	
</script>
</head>
<body ng-app="sysGroupApp" ng-controller="sysGroupController" style="width:100%;height:100%;margin:10px">
	<ul class="breadcrumb">
		<li>授权角色</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<input id="id" name="id" type="hidden" value="${id}" /> 
				
				<div id="jstree"><font color="red">*注:&nbsp;&nbsp;&nbsp;一个分组可以授权多个权限。</font></div><br>
				<div id="jstree2"></div>
				<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
					<div class="col-sm-offset-2 col-sm-10"> 
						<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="roleSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>