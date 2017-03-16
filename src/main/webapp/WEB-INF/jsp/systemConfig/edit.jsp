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
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/bootstrap-select.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.3.7/css/bootstrap-theme.min.css"/>
<script type="text/javascript" src="${ctx }/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap-select.min.js"></script>


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
 			jQuery.validator.addMethod("uneIdentCheck", function(value, element) { 	
		    	var jg=false;
				var zhi=uniqueCheck('${systemConfig.id}','uneIdent',value);
				var test=/^[a-zA-Z0-9_]+$/;
				//校验不能为0 唯一性 格式
				if(value!='0'&&zhi!='1'&&test.test(value)){
					jg=true;
				}
		        return this.optional(element) || jg;       
		    }, "唯一标识  不能重复、只能是数字、拼音、_下划线  不能为0");
		});
		
		function uniqueCheck(id,name,value){
			var zhi=0;
			if(name==null||name==''){
				return 'cuowu';
			}
            $.ajax({
				   async:false,
				   cache:false,
				   type:"POST",
				   dataType : 'json',
				   data:"name="+name+"&id="+id+"&value="+value+"",  //传参  
				   url:"${ctx}dispatcher/systemConfig/typeUniqueCheck",
				   error:function(json){
				   	zhi='cuowu';
				   },
				   timeout:60000,
				   success:function(json){
				   	zhi=json;
				   }
			});
			return zhi;			
		}

		function index() {
			document.serqueryForm.action='${ctx}dispatcher/systemConfig/index';
			document.serqueryForm.submit();
		}
		
		function checkNull(){
			var ValState = $('#inputForm').valid(); 
			if(ValState==true){
				$("#btnSubmit").attr("disabled",true);
			}
			return biaoshi;
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="javascript:index();">系统配置列表</a></li>
		<li class="active">
			<a href="#">
				<c:choose>
				    <c:when test="${empty systemConfig.id}">
						系统配置添加 
				    </c:when>
				    <c:otherwise>
				      	系统配置修改
				    </c:otherwise>   
				</c:choose>
			</a>
		</li>
	</ul><br/>
	<form name="inputForm" id="inputForm" class="form-horizontal" action="${ctx}dispatcher/systemConfig/save" method="post" onsubmit="return checkNull()" >
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
				<label class="control-label">唯一标识：<font color='red'>*</font></label>
				<div class="controls">
					<input id="uneIdent" class="required uneIdentCheck" name="uneIdent" type="text" value="${systemConfig.uneIdent}" maxlength="50" style="width:300px"/>
				</div>
			</div>
		</div>
		<%-- <div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">类型：<font color='red'>*</font></label>
				<div class="controls">
					<input id="type" name="type" type="text" class="required" value="${systemConfig.type}" maxlength="400" style="width:300px"/>
				</div>
			</div>
		</div> --%>
		<div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">内容：<font color='red'>*</font></label>
				<div class="controls">
					<textarea rows="3" cols="3" id="content" name="content" class="required" maxlength="900" style="width:300px">${systemConfig.content}</textarea>
				</div>
			</div>
		</div>
		<div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">状态:<font color='red'>*</font></label>
				<div class="controls">
					<select id="state" name="state">
						<option value="1" >启用</option>
						<option value="-1" >禁用</option>
					</select>
				</div>
			</div>
		</div>
		<div style="width:100%;float:left;">
			<div class="control-group" style="width:40%;display: inline;float:left;margin-bottom: 3px;margin-top: 3px;">
				<label class="control-label">说明：</label>
				<div class="controls">
					<textarea rows="3" cols="3" id="explain" name="explain" maxlength="900" style="width:300px">${systemConfig.explain}</textarea>
				</div>
			</div>
		</div>
		
		<div class="form-actions" style="width:100%;float:left;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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