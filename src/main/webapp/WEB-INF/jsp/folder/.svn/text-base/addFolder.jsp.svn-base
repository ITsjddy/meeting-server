<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
<meta charset="utf-8">
<title>FileAdd</title>
<script src="${ctx }/static/sysviews/folder/js/addFolder.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body ng-app="folderApp" ng-controller="folderController">
<ul class="breadcrumb"><li>文件添加</li></ul>
	<div id="wrapper">
		<div id="page-wrapper">
		<form class="form-horizontal" role="form">
			<input type="hidden" ng-init="folder.contentHtml=''">
				<div class="panel panel-default">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileName">文件中文名</label>
							<div class="col-sm-6">
								<input class="form-control"id="fileName" type="text"
									name="fileName" ng-model="folder.fileName" placeholder="请输入文件名" required/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileEnName">文件英文名</label>
							<div class="col-sm-6">
								<input class="form-control" id="fileEnName" type="text"
									name="fileEnName" ng-model="folder.fileEnName" placeholder="请输入文件英文名" required/>
	 						</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileEnName">文件类型</label>
							<div class="col-sm-6">
								<select class="form-control" name="fileType" id="fileType" ng-model="folder.fileType">
									<option value="1" <c:if test="${'1'==folder.fileType}">selected</c:if> >大会资料</option>   
			  						<option value="2" <c:if test="${'2'==folder.fileType}">selected</c:if> >媒体资料</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="folder.filePath">文件上传
							</label>
								<input type="file">
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="explains">文件说明</label>
							<div class="col-sm-6">
								<input class="form-control" id="explains" type="text"
									name="explains" ng-model="folder.explains" placeholder="" />
							</div>
							<div class="col-sm-6" style="display:none">
								<script id="editor" type="text/plain" style="height: 100px; width: 50px;" ></script>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="form-group breadcrumb" style="width:100%;float:left;margin:10px;">
				<div class="col-sm-offset-2 col-sm-10"> 
					<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/> -->
					<!-- <a href="javaScript:void(0)" class="btn btn-default" ng-click="folderSave()">保存</a> -->
					<input id="btnSubmit" class="btn btn-primary" type="button" ng-click="folderSave()" value="保 存"/>&nbsp;&nbsp;&nbsp;
					<a href="${ctx }/dispatcher/folder/index" class="btn btn-default">返回</a>
				</div>
			</div>
		</div>
	<script type="text/javascript">
   	    var ue = UE.getEditor('editor');
   	 	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	   	UE.Editor.prototype.getActionUrl = function(action){
	   		if(action == 'uploadimage'){ 
	   			return CONTEXT_PATH+DISPATCHER_PATH+'/folder/upload';
	   		}else if(action == 'uploadfile'){
	   			return CONTEXT_PATH+DISPATCHER_PATH+'/folder/upload';
	   		}else if(action == 'uploadvideo'){
	   			return CONTEXT_PATH+DISPATCHER_PATH+'/folder/upload';
	   		}else{
	   			return this._bkGetActionUrl.call(this,action);
	   		}
	   	}
    </script>
</body>
</html>