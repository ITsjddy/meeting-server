<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>新闻修改页面</title>
	<script src="${ctx }/static/sysviews/news/js/edit.js"></script>
	 <!--  富文本编辑器 -->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body ng-app="newsApp" ng-controller="newsController">
  <div id="wrapper">
        <div id="page-wrapper">
			<form class="form-horizontal" role="form">
			<input type="hidden" value="${id }" id="testid">
				<fieldset>
					<legend>详情/修改页面</legend>
					<fieldset>
					<legend>添加页面</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">姓名</label>
						<div class="col-sm-4">
							<input class="form-control" id="ds_host" type="text" name="name" value="" ng-model="testDemo.name"
								placeholder="" />
						</div>
					</div>
					<div class="form-group">
						<label for="sexRadios" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-2">
							<div class="radio">
								<label> 
									<input type="radio" name="sex" ng-model="testDemo.sex"id="optionsRadios1" value="0" checked="{{testDemo.sex=='0'}}" >男
								</label>
							</div>
							<div class="radio">
								<label> 
									<input type="radio" name="sex"  ng-model="testDemo.sex"id="optionsRadios1" value="1" checked="{{testDemo.sex=='0'}}">女
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">新闻详情：</label>
						<!--  <textarea class="textarea-input"></textarea> -->
						<div class="col-sm-4">
							<script id="editor" type="text/plain"style="height: 500px; width: 700px;"></script>
						</div>
					</div>
						<div class="form-group">
						<div class="col-sm-4">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="testEditSave()" >保存</a>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
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