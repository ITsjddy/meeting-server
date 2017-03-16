<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>新闻栏目详情页面</title>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/newsColumn/js/detail.js"></script>
</head>
<body ng-app="newsColumnApp" ng-controller="newsColumnController">
 <ul class="breadcrumb"><li>新闻栏目详情</li></ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form">
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>基本信息</li>
			</ul>
			<input id="uniqueCode" name="uniqueCode" type="hidden" value="${uniqueCode}" />
			<input id="id" name="id" type="hidden" value="${id}" />
			<input type="hidden" ng-model="newsColumn.language"/>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型：</label> 
				<div class="col-sm-8"> 
					<input type="checkbox" checked disabled>中文&nbsp;
					<span ng-repeat="language in languageData">
						<input type="checkbox" disabled ng-model="language.languageModel" ng-change="languageOnChange(language.languageModel,language.uneIdent,$index)" ng-checked="language.check"/>{{language.name}}&nbsp;
					</span>
				</div> 
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">数据类别：</label>
				<div class="col-sm-3">
					<select class="form-control" disabled ng-change="changeType()" name="type" ng-model="newsColumn.type">
						<option value="">--请选择--</option>
						<option value="column" ng-model="newsColumn.type">栏目</option>
						<option value="category" ng-model="newsColumn.type">类别</option>
					</select>
				</div>
				<div ng-init="showDiv=true" ng-show="showDiv">
				<label for="firstname" class="col-sm-2 control-label">所属类别：</label>
				<div class="col-sm-3">
					<select class="form-control" disabled name="column" ng-model="newsColumn.columnId">
						<option value="">--请选择--</option>
						<option ng-repeat="columnInfo in columnListInfo" ng-model="column" value="{{columnInfo.id}}" ng-selected="{{newsColumn.columnId == columnInfo.id}}">{{columnInfo.name}}</option>
					</select>
				</div>
				</div>
  			</div>
			</div>
  			<!-- 中文信息 -->
				<div class="panel panel-default">
					<ul class="breadcrumb">
						<li>中文信息</li>
					</ul>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">
							栏目/分类名称：<font color="red">*</font>
						</label>
						<div class="col-sm-3">
							<input type="text" disabled placeholder="栏目/分类名称" class="form-control"
								required autofocus ng-model="newsColumn.name" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">描述：<font
						color="red">*</font></label>
						<div class="col-sm-3">
							<textarea type="text" disabled placeholder="描述" class="form-control"
								required autofocus ng-model="newsColumn.remark" maxlength="60" maxlength="1000" class="input-xlarge isSpace" rows="5" style="width:800px;resize:none;"></textarea>
						</div>
					</div>
				</div>
				<!-- 其他语言信息 -->
				<div ng-repeat="newsColumnLan in newsColumnList">
					<div class="panel panel-default" id="guestDiv{{$index}}"
						ng-show="languageData[$index].check">
						<input type="hidden" ng-model="newsColumnLan.language" /> <input
							type="hidden" ng-model="newsColumnLan.check" />
						<ul class="breadcrumb">
							<li>{{languageData[$index].name}}信息</li>
						</ul>
						<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label"> <!-- <div ng-if="newsColumn.language=='1'">会场名称：<font color="red">*</font></div><div ng-if="newsColumn.language=='2'">name：<font color="red">*</font></div> -->
							栏目/分类名称：({{languageData[$index].name}})：<font color="red">*</font>
						</label>
						<div class="col-sm-3">
							<input type="text" disabled placeholder="栏目/分类名称：({{languageData[$index].name}})" class="form-control"
								required autofocus ng-model="newsColumnLan.name" maxlength="60" />
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">描述({{languageData[$index].name}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<textarea type="text" disabled placeholder="描述({{languageData[$index].name}})" class="form-control"
							required autofocus ng-model="newsColumnLan.remark" maxlength="60" maxlength="1000" class="input-xlarge isSpace" rows="5" style="width:800px;resize:none;"></textarea>
						</div>
					</div>
					</div>
				</div>
			</form>
	</div>
	<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
		<div class="col-sm-offset-2 col-sm-10"> 
		<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
		</div>
	</div>
</div>
</body>
</html>