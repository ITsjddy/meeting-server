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
<link href="${ctx}/static/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>    
<link href="${ctx}/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/common/jeesite.min.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script>	
<script src="${ctx}/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.0/jquery.validate.method.min.js" type="text/javascript"></script>
<script src="${ctx}/static/common/jeesite.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules:{
					
				},
				 messages:{
					 
				 },
				focusInvalid: false, 
				  onkeyup: false,   
				  errorPlacement: function(error, element) {       
					error.appendTo( element.parent());       
				}   
			});
		});

		function index() {
			document.serqueryForm.action='${ctx}/systemConfig.do';
			document.serqueryForm.submit();
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="javascript:index();">系统配置列表</a></li>
		<li class="active">
			<a href="#">系统配置详情</a>
		</li>
	</ul><br/>
	<form name="inputForm" id="inputForm" class="form-horizontal" method="post" >
		<input id="id" name="id" type="hidden" value="${systemConfig.id}" />
		<!-- 列表查询条件 -->
		<input id="sysser_pageSize" name="sysser_pageSize" type="hidden" value="${sysser_pageSize}" />
		<input id="sysser_pageNo" name="sysser_pageNo" type="hidden" value="${sysser_pageNo}" />
		<input id="sysser_uneIdent" name="sysser_uneIdent" type="hidden" value="${sysser_uneIdent}" />
		<input id="sysser_content" name="sysser_content" type="hidden" value="${sysser_content}" />
		<input id="sysser_state" name="sysser_state" type="hidden" value="${sysser_state}" />
		<input id="sysser_type" name="sysser_type" type="hidden" value="${sysser_type}" />
		<input id="sysser_beginTimes" name="sysser_beginTimes" type="hidden" value="${sysser_beginTimes}" />
		<input id="sysser_endTimes" name="sysser_endTimes" type="hidden" value="${sysser_endTimes}" />

		<div style="width:100%;display: block;float:left;">
			<div class="control-group" style="width:100%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">唯一标识：</label>
				<div class="controls">
					<input class="required" type="text" value="${systemConfig.uneIdent}" style="width:300px" readonly/>
				</div>
			</div>
		</div>
		<%-- <div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">类型：</label>
				<div class="controls">
					<input id="type" name="type" type="text" class="required" value="${systemConfig.type}" maxlength="400" style="width:300px"/>
				</div>
			</div>
		</div> --%>
		<div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">内容：</label>
				<div class="controls">
					<textarea rows="3" cols="3" style="width:300px" readonly>${systemConfig.content}</textarea>
				</div>
			</div>
		</div>
		<div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">状态:</label>
				<div class="controls">
					<c:choose>
						<c:when test="${systemConfig.state=='1'}"><font style="color:blue;">启用</font></c:when>
						<c:otherwise><font style="color:red;">禁用</font></c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">说明：</label>
				<div class="controls">
					<textarea rows="3" cols="3" style="width:300px" readonly>${systemConfig.explain}</textarea>
				</div>
			</div>
		</div>
		
		<div class="form-actions" style="width:100%;float:left;">
			<input id="" class="btn " type="button" onclick="index()" value="返回"/>
		</div>
	</form>
	<!-- 列表查询条件 -->
	<form name="serqueryForm" id="serqueryForm" method="post" style="display:none;">
		<input id="sysser_pageSize" name="sysser_pageSize" type="hidden" value="${sysser_pageSize}" />
		<input id="sysser_pageNo" name="sysser_pageNo" type="hidden" value="${sysser_pageNo}" />
		<input id="sysser_uneIdent" name="sysser_uneIdent" type="hidden" value="${sysser_uneIdent}" />
		<input id="sysser_content" name="sysser_content" type="hidden" value="${sysser_content}" />
		<input id="sysser_state" name="sysser_state" type="hidden" value="${sysser_state}" />
		<input id="sysser_type" name="sysser_type" type="hidden" value="${sysser_type}" />
		<input id="sysser_beginTimes" name="sysser_beginTimes" type="hidden" value="${sysser_beginTimes}" />
		<input id="sysser_endTimes" name="sysser_endTimes" type="hidden" value="${sysser_endTimes}" />
	</form>
</body>

</html>