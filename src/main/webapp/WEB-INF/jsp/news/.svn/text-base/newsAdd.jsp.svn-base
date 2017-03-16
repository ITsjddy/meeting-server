<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>新闻添加页面({{languageName}})</title>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/news/js/add.js"></script>
	 <!--  富文本编辑器 -->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
    	var myLanguage = '${languageNow }';
    </script>
</head>
<body  ng-app="newsApp" ng-controller="newsController">
 <ul class="breadcrumb"><li>新闻添加({{languageName}})</li></ul>
<div id="wrapper">
	<div id="page-wrapper">
		<form class="form-horizontal" role="form" id="newsForm" ng-submit="saveOrPublish()">
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
					<select class="form-control" ng-model="news.language" ng-change="changeLanguage(news.language)" ng-init="languageName='中文'">
						<option value="">--请选择--</option>
						<option ng-if="language.uneIdent !='all'" ng-selected ="languageNow==language.uneIdent" ng-model="languageName" ng-repeat="language in languageList" value="{{language.uneIdent}}">{{language.name}}</option>
					</select>
				</div> 
				<label for="firstname" class="col-sm-2 control-label">标题({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3">
					<input type="text" id="title" placeholder="标题({{languageName}})" class="form-control"
						required  ng-model="news.title"
						maxlength="60" />
				</div>
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">所属类别({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3">
					<select class="form-control" ng-change="changeCategory(news.category)" required ng-model="news.category">
						<option value="">--请选择--</option>
						<option ng-repeat="category in categoryList" value="{{category.id}}">{{category.name}}</option>
					</select>
				</div>
				<label for="firstname" class="col-sm-2 control-label">所属栏目({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3">
					<select class="form-control" required  name="column" ng-model="news.newColumn">
						<option value="">--请选择--</option>
						<option ng-repeat="column in columnList" value="{{column.id}}">{{column.name}}</option>
					</select>
				</div>
  			</div>
			<div class="form-group"> 
				<label for="firstname" class="col-sm-2 control-label">位置({{languageName}})：<font color="red">*</font></label>
				<div class="col-sm-3" ng-init="news.location='common'">
					普通新闻&nbsp;<input type="radio" name="location"  ng-model="news.location"
						value="common" checked="{{news.location=='common'}}" />&nbsp;&nbsp;
					轮播图&nbsp;<input type="radio" name="location"  ng-model="news.location"
						value="carousel" checked="{{news.location=='carousel'}}" />
					头条&nbsp;<input type="radio" name="location"  ng-model="news.location"
					value="headlines" checked="{{news.location=='headlines'}}" />
				</div>
				<label for="firstname" class="col-sm-2 control-label">详情页跳转地址({{languageName}})：</label>
				<div class="col-sm-3">
					<input type="text" placeholder="详情页跳转地址({{languageName}})" class="form-control"
					  ng-model="news.detailsUrl" maxlength="60" />
				</div>
  			</div>
  			<div class="form-group">
  				<label for="firstname" class="col-sm-2 control-label">简介({{languageName}})：<font
							color="red">*</font></label>
				<div class="col-sm-3">
					<textarea type="text" placeholder="简介({{languageName}})" class="form-control"
								required  ng-model="news.remark"  maxlength="1000" class="input-xlarge isSpace" rows="5" style="width:800px;resize:none;"></textarea>
				</div>
			</div>
			<div class="form-group">
			<label for="firstname" class="col-sm-2 control-label">排序({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" class="form-control" required oninput="setCustomValidity('')" oninvalid="setCustomValidity('请输入正整数')" pattern="^[1-9]\d*$" placeholder="排序({{languageName}})" ng-model="news.sortNumber" />
						</div>
						<label for="firstname" class="col-sm-2 control-label">缩略图({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="file" />
						</div>
			</div>
			<div class="form-group" ng-if="news.location=='carousel'">
				<label for="firstname" class="col-sm-2 control-label">轮播图单独排序({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" required oninput="setCustomValidity('')" oninvalid="setCustomValidity('请输入正整数')" pattern="^[1-9]\d*$" class="form-control" ng-model="news.vieverSort" placeholder="轮播图单独排序({{languageName}})"  />
						</div>
						<label for="firstname" class="col-sm-2 control-label">轮播图({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="file"/>
						</div>
						
			</div>
			<div class="form-group" ng-if="news.location=='headlines'">
				<label for="firstname" class="col-sm-2 control-label">头条单独排序({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="text" required oninput="setCustomValidity('')" oninvalid="setCustomValidity('请输入正整数')" pattern="^[1-9]\d*$" class="form-control" ng-model="news.headlinesSort" placeholder="头条单独排序({{languageName}})"  />
						</div>
						<label for="firstname" class="col-sm-2 control-label">头条图片({{languageName}})：<font
							color="red">*</font></label>
						<div class="col-sm-3">
							<input type="file"/>
						</div>
						
			</div>
			<div class="form-group">
						<label class="col-sm-2 control-label">新闻详情({{languageName}})：</label>
						<!--  <textarea class="textarea-input"></textarea> -->
						<div class="col-sm-4">
							<script id="editor" type="text/plain"style="height: 500px; width: 700px;"></script>
						</div>
			</div>
			<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
				<div class="col-sm-offset-2 col-sm-10"> 
				<input type="hidden" id="whichButton" name="whichButton" value="">
				<input id="btnSubmit" onclick="whichButton.value='btnSubmit'" class="btn btn-primary" type="submit"  value="保 存"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="btnPublish" onclick="whichButton.value='btnPublish'"  class="btn btn-primary" type="submit"  value="发布"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="" class="btn btn-default" type="button" ng-click="index()" value="返回"/>
				</div>
			</div>
			</div>
			</form>
	</div>
	
	<script type="text/javascript">
   	    var ue = UE.getEditor('editor');
   	 	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	   	UE.Editor.prototype.getActionUrl = function(action){
	   		if(action == 'uploadimage'){
	   			return CONTEXT_PATH+DISPATCHER_PATH+'/news/upload';
	   		}else if(action == 'uploadfile'){
	   			return CONTEXT_PATH+DISPATCHER_PATH+'/news/upload';
	   		}else if(action == 'uploadvideo'){
	   			return CONTEXT_PATH+DISPATCHER_PATH+'/news/upload';
	   		}else{
	   			return this._bkGetActionUrl.call(this,action);
	   		}
	   	}
    </script>
</body>
</html>