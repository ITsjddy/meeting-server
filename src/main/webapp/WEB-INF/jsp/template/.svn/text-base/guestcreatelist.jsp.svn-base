<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
<meta charset="utf-8"> 
<title>嘉宾导入模板管理</title>
<%@ include file="/WEB-INF/inc/httpOnly.jsp"%>
<script src="${ctx }/static/sysviews/template/js/guestcreatelist.js"></script>	
<script type="text/javascript">
	$(document).ready(function() {
		$("#excelFile").bind('change',function(){
			var dFile = document.getElementById('excelFile');
			if(!dFile.value.match(/.xls|.xlsx/i)){
				alert('上传文件必须匹配: .xls 或 .xlsx !');
				dFile.value=null;
				return;
			}
		});
	});
</script>
</head>
<body ng-app="guestApp" ng-controller="guestController">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog" style="width:870px;height:420px;">
		        <div class="modal-content">
		           <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">模板导入字段选择</h4>
		            </div>
		            <iframe name="selectMember" id="selectMember"src="${ctx }/dispatcher/guesttemplate/guestimportlist" style="width:100%;height:420px;">
                    </iframe>
		           <!--  <div class="modal-body">在这里添加一些文本</div> -->
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary" ng-click="getSelectedItem()">确认选择</button>
		            </div> 
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
	</div>
	<ul class="breadcrumb"><li>嘉宾导入模板管理</li></ul>
	<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form">
			<input id="id" name="id" type="hidden" value="${id}" />
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">嘉宾信息导入：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<input id="excelFile" name="excelFile" type="file" file-model="myFile"/>
				</div> 
  			</div> 
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">说明：</label> 
				<div class="col-sm-8"> 
					<textarea class="form-control" placeholder="说明" rows="3" autofocus id="shuoming" name="shuoming" ng-model="template.explain"></textarea>
				</div>
  			</div>
  			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">注意事项：</label> 
				<div class="controls" style="font-weight:bold;">
					&nbsp;&nbsp;{注意：Excel模版里 如果导入内容由数字组成(如日期、时间等)要把对应数据的单元格格式设置为"文本";<br>
					&nbsp;&nbsp;团名称重复时、则视为修改;}*
				</div>
  			</div>
  			<div class="form-group" id="importinfo"> 
				<label for="firstname" class="col-sm-2 control-label">导入信息：</label> 
				<div class="controls" id="excelinfo" style="color:red;font-weight:bold;">
				</div>
  			</div>
  			
		</div>
		</form>
	</div>
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
			<input id="" class="btn btn-success" data-toggle="modal" type="button" data-target="#myModal" value="下载Excel模版"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="save()" value="确认导入"/>
			<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>	
</body>
</html>