<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>info</title>
	<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/publicshComment/js/audited.js"></script>
	<script type="text/javascript">
		function showOriginalPhoto(o){
			var src = o.src;
			var newwin = window.open();
			newwin.document.write("<img src='"+src+"'>");
		}
	</script>
</head>
<body ng-app="publicshCommentApp" ng-controller="publicshCommentController" style="width:100%;height:100%;margin:10px">
<ul class="breadcrumb">
	<li>审核</li>
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
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">发布内容：</label> 
					<div class="col-sm-8"> 
						<textarea placeholder="发布内容" class="form-control" autofocus ng-model="publicshCom.publicsh.message" maxlength="220" readonly="readonly"></textarea>
					</div> 
	  			</div>
				<div class="form-group"> 
					<label for="firstname" class="col-sm-2 control-label">发布图片：</label> 
					<div class="col-sm-3" ng-show="publicshCom.fileUpload==null">
						无图片
					</div> 
	  			</div> 
	  			<div class="form-group" ng-show="publicshCom.fileUpload!=null">
	  				<span ng-repeat="lupt in publicshCom.fileUpload">
						<img src="${ctx}{{lupt.bigFileurl}}" style="width:100px; height:100px;cursor:pointer;padding-left:15px;margin-left: 100px;margin-top: 10px;" onclick="showOriginalPhoto(this)" alt="图片丢失" title='点击放大图片'/>
					</span>
	  			</div>
	  			<div class="form-group">
	  				<label for="firstname" class="col-sm-2 control-label">发布人：</label> 
	  				<div class="col-sm-3">
	  					<input type="text" placeholder="发布人" class="form-control" autofocus ng-model="publicshCom.publicsh.member.name" maxlength="220" readonly="readonly"/>
	  				</div>
	  			</div>
	  			<div class="form-group"> 
	  				<label for="firstname" class="col-sm-2 control-label">发布时间：</label> 
					<div class="col-sm-3"> 
						<input type="text" placeholder="发布时间" class="form-control" autofocus ng-model="publicshCom.publicsh.createTimes" maxlength="220" readonly="readonly"/>
					</div>
	  			</div>
	  			<div class="form-group">
	  				<label for="firstname" class="col-sm-2 control-label">审核状态：</label> 
					<div class="col-sm-3"> 
						<select class="form-control input-sm" type="search" placeholder="" aria-controls="dataTables-example" ng-model="publicshCom.publicsh.status" disabled="disabled">
							<option value="1" <c:if test="${'1'== publicshCom.publicsh.status}">selected</c:if> >未审核</option>
							<option value="2" <c:if test="${'2'== publicshCom.publicsh.status}">selected</c:if> >通过</option>
							<option value="3" <c:if test="${'3'== publicshCom.publicsh.status}">selected</c:if> >未通过</option>    
						</select>
					</div>
	  			</div>
	  			<div class="form-group">
					<label for="firstname" class="col-sm-2 control-label">审核未通过原因：</label> 
					<div class="col-sm-8"> 
						<input type="hidden" name="auditReason" id="auditReason" value="">
						<textarea class="form-control" id="auditReasontext" name="auditReason" placeholder="审核未通过原因" autofocus ng-model="publicshCom.auditReason" maxlength="220"></textarea>
					</div> 
	  			</div>
			</div>
		</form>
	</div>
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
			<a href="javaScript:void(0)" id="btnSubmit" name="btnSubmit" ng-click="publicshCommentSave('2')" class="btn btn-primary" value="2">通过</a>
			<a href="javaScript:void(0)" id="btnSubmit" name="btnSubmit" ng-click="publicshCommentSave('3')" class="btn btn-primary" value="3">不通过</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>

</body>

</html>