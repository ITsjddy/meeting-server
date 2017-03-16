<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>menu</title>
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/bootstrap-select.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.3.7/css/bootstrap-theme.min.css"/>
<script type="text/javascript" src="${ctx }/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/bootstrap-select.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#checkAll").click(function() {
			$("input[name='ids']").prop("checked", this.checked);
		});
		$("input[name='ids']").click(function() {
			var $temids = $("input[name='ids']");
			$("#checkAll").prop("checked" , $temids.length == $temids.filter(":checked").length ? true :false);
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">系统配置列表</a></li>
		<li><a href="javascript:click('','${ctx}dispatcher/systemConfig/edit');">系统配置添加</a></li>
	</ul>
	<br />
	<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="${ctx}dispatcher/systemConfig/index" method="post" style="display: none">
		<input id="sysser_pageSize" name="sysser_pageSize" type="hidden" value="${sysser_pageSize}" />
		<input id="sysser_pageNo" name="sysser_pageNo" type="hidden" value="${sysser_pageNo}" />
		<input id="sysser_uneIdent" name="sysser_uneIdent" type="hidden" value="${sysser_uneIdent}" />
		<input id="sysser_content" name="sysser_content" type="hidden" value="${sysser_content}" />
		<input id="sysser_state" name="sysser_state" type="hidden" value="${sysser_state}" />
		<input id="sysser_type" name="sysser_type" type="hidden" value="${sysser_type}" />
		<input id="sysser_beginTimes" name="sysser_beginTimes" type="hidden" value="${sysser_beginTimes}" />
		<input id="sysser_endTimes" name="sysser_endTimes" type="hidden" value="${sysser_endTimes}" />
		<input id="id" name="id" type="hidden" />
	</form>
	<form action="${ctx}dispatcher/systemConfig/index" method="post" style="margin-top:5; margin-bottom:0;">
		<div id="TableTail" class="breadcrumb">
			<table style="width:100%;border:0;">
				<tr>
					<td align=left width="100%">
						唯一标识：&nbsp;&nbsp; 
						<input id="sysser_uneIdent" class="input-medium" type="text" value="${ sysser_uneIdent}" name="sysser_uneIdent"> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容：&nbsp;&nbsp; 
						<input id="sysser_content" class="input-medium" type="text" value="${ sysser_content}" name="sysser_content">
						<%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 类型：&nbsp;&nbsp; 
						<input id="sysser_type" class="input-medium" type="text" value="${ sysser_type}" name="sysser_type"> --%> 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：&nbsp;&nbsp; 
						<select id="sysser_state" name="sysser_state">
							<option value=""></option>
							<option value="1" <c:if test="${sysser_state=='1'}">selected</c:if>>启用</option>
							<option value="-1" <c:if test="${sysser_state=='-1'}">selected</c:if>>禁用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align=left width="100%">
						创建时间： &nbsp;&nbsp; 
						<input id="sysser_beginTimes" class="input-medium" type="text" value="${ sysser_beginTimes}" name="sysser_beginTimes" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						&nbsp;&nbsp;至&nbsp;&nbsp; 
						<input id="sysser_endTimes" class="input-medium" type="text" value="${ sysser_endTimes}" name="sysser_endTimes" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
						<input type="submit" class="btn btn-primary" value="查询" />
						<input type="button" class="btn btn-primary" value="删除" onclick="del()"/>
					</td>
				</tr>
			</table>
		</div>
	</form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="checkAll" id="checkAll"></th>
				<th>唯一标识</th>
				<th>内容</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${result}">
				<tr>
					<td width="2%"><input type="checkbox" value="${item.id}" name="ids" id="ids"/></td>
					<td width="15%">${item.uneIdent}</td>
					<td width="20%">${item.content}</td>
					<td width="15%">
						<c:choose>
							<c:when test="${item.state=='1'}"><font style="color:blue;">启用</font></c:when>
							<c:otherwise><font style="color:red;">禁用</font></c:otherwise>
						</c:choose>
					</td>
					<td width="15%">
						<fmt:formatDate value="${item.createTimes}" pattern="yyyy年MM月dd日 HH:mm:ss" />
					</td>
					<td width="35%">
						<a href="javascript:click('${item.id}','${ctx}dispatcher/systemConfig/edit');" class="btn ">修 改</a>&nbsp;&nbsp;&nbsp;&nbsp; 
						<c:choose>
						    <c:when test="${item.state=='1'}">
								<a href="javascript:click('${item.id}','${ctx}dispatcher/systemConfig/enable?state=-1');" class="btn ">禁 用</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    </c:when>
						    <c:otherwise>
						      	<a href="javascript:click('${item.id}','${ctx}dispatcher/systemConfig/enable?state=1');" class="btn ">启 用</a>&nbsp;&nbsp;&nbsp;&nbsp;
						    </c:otherwise>   
						</c:choose>
						<a href="javascript:click('${item.id}','${ctx}dispatcher/systemConfig/details');" class="btn ">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript">
		function page(n, s) {
			$("#sysser_pageNo").val(n);
			$("#sysser_pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		function click(id,action) {	
			$("#id").val(id);
			document.searchForm.action=action;
			document.searchForm.submit();
			return false;
		}
		function del(){
			var ids_selected = "";
		    $('input:checkbox[name=ids]:checked').each(function(i){
			    if(0==i){
			 	   ids_selected = $(this).val();
			    }else{
			 	   ids_selected += (","+$(this).val());
			    }
		    });
		    if(ids_selected!="" && ids_selected !=null){
				if(confirm('删除此配置信息吗?')){
					$("#id").val(ids_selected);
					document.searchForm.action='${ctx}dispatcher/systemConfig/del';
					document.searchForm.submit();
					return false;  
				}
		    }else{
				alert("请选择删除信息！");
				return false;
		    }
		}
	</script>
</body>
</html>