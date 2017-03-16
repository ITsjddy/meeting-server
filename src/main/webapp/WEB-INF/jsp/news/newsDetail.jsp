<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>新闻详情页面</title>
	<script src="${ctx }/static/sysviews/news/js/detail.js"></script>
	 <!--  富文本编辑器 -->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<script type="text/javascript">
    	var myLanguage = '${languageNow }';
    </script>
<body ng-app="newsApp" ng-controller="newsController">
  <ul class="breadcrumb"><li>新闻详情({{languageName}})</li></ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form">
		<input type="hidden" id="uniqueCode" value="${uniqueCode }">
		<input type="hidden" id="id" value="${id }">
		<input type="hidden" ng-init="news.contentHtml=''">
		<input type="hidden" ng-init="languageNow ='${languageNow }'" ng-model="languageNow">
		<input type="hidden" ng-init="news.language=''">
		<div class="panel panel-default">
			<ul class="breadcrumb">
				<li>基本信息({{languageName}})</li>
			</ul>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">语言类型({{languageName}})：<font color="red">*</font></label> 
				<div class="col-sm-3"> 
					<select class="form-control" disabled ng-model="news.language" ng-change="changeLanguage(news.language)" ng-init="languageName='中文'">
						<option value="">--请选择--</option>
						<option ng-show="language.uneIdent !='all'" ng-selected ="languageNow==language.uneIdent" ng-model="languageName" ng-repeat="language in languageList" value="{{language.uneIdent}}">{{language.name}}</option>
					</select>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">标题({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3">
					<input type="text" disabled placeholder="标题({{languageName}})" class="form-control"
						required autofocus ng-model="news.title"
						maxlength="60" />
				</div>
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">所属栏目({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3">
					<select class="form-control" disabled ng-change="changeColumn(news.newColumn)" name="column" ng-model="news.newColumn">
						<option value="">--请选择--</option>
						<option ng-selected ="column.id==news.newColumn" ng-repeat="column in columnList" value="{{column.id}}">{{column.name}}</option>
					</select>
				</div>
				<label for="firstname" class="col-sm-2 control-label">所属类别({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3">
					<select class="form-control" disabled ng-model="news.category">
						<option value="">--请选择--</option>
						<option ng-selected ="category.id==news.category" ng-repeat="category in categoryList" value="{{category.id}}">{{category.name}}</option>
					</select>
				</div>
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">位置({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3" ng-init="news.location='common'">
					普通新闻&nbsp;<input disabled type="radio" name="location" autofocus ng-model="news.location"
						value="common" checked="{{news.location=='common'}}" />&nbsp;&nbsp;
					轮播图&nbsp;<input disabled type="radio" name="location" autofocus ng-model="news.location"
						value="carousel" checked="{{news.location=='carousel'}}" />
					头条&nbsp;<input type="radio" name="location"  ng-model="news.location"
					value="headlines" checked="{{news.location=='headlines'}}" />
				</div>
				<label for="firstname" class="col-sm-2 control-label">详情页跳转地址({{languageName}})：</label>
				<div class="col-sm-3">
					<input type="text" disabled placeholder="详情页跳转地址({{languageName}})" class="form-control"
						 ng-model="news.detailsUrl" maxlength="60" />
				</div>
  			</div>
  			<div class="form-group">
  				<label for="firstname" class="col-sm-2 control-label">简介({{languageName}})：<font
							color="red">*</font></label>
				<div class="col-sm-3">
					<textarea type="text" disabled placeholder="简介({{languageName}})" class="form-control"
								required autofocus ng-model="news.remark" maxlength="60" maxlength="1000" class="input-xlarge isSpace" rows="5" style="width:800px;resize:none;"></textarea>
				</div>
			</div>
			<div class="form-group">
			<label for="firstname" class="col-sm-2 control-label">排序({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" disabled class="form-control" placeholder="排序({{languageName}})" ng-model="news.sortNumber" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">缩略图({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
						</div>
			</div>
			<div class="form-group" ng-show="showDiv">
				<label for="firstname" class="col-sm-2 control-label">轮播图单独排序({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" disabled class="form-control" ng-model="news.vieverSort" placeholder="轮播图单独排序({{languageName}})"  />
						</div>
						<label for="firstname" class="col-sm-2 control-label">轮播图({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
						</div>
						
			</div>
				<div class="form-group" ng-if="showDivHeadlines">
				<label for="firstname" class="col-sm-2 control-label">头条单独排序({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" required disabled oninput="setCustomValidity('')" oninvalid="setCustomValidity('请输入正整数')" pattern="^[1-9]\d*$" class="form-control" ng-model="news.headlinesSort" placeholder="头条单独排序({{languageName}})"  />
						</div>
						<label for="firstname" class="col-sm-2 control-label">头条图片({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
						</div>
						
			</div>
			<div class="form-group">
						<label class="col-sm-2 control-label">新闻详情({{languageName}})：<font color="red">*</font></label>
						<!--  <textarea class="textarea-input"></textarea> -->
						<div class="col-sm-8" id="newsDetail">
							
						</div>
			</div>
			<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
				<div class="col-sm-offset-2 col-sm-10"> 
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
				</div>
			</div>
			</div>
			</form>
	</div>
	
</body>
</html>