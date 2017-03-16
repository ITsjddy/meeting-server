<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>folderEdit</title>
<script src="${ctx }/static/sysviews/folder/js/editFolder.js"></script>
</head>

<body ng-app="folderApp" ng-controller="folderController" >
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<input type="hidden" value="${id }" id="folderid">
				<fieldset>
					<legend>文件修改</legend>
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileName">文件中文名</label>
							<div class="col-sm-6">
								<input class="form-control" id="fileName" type="text"
									name="fileName" value="" ng-model="folder.fileName"
									placeholder="" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileEnName">文件英文名</label>
							<div class="col-sm-6">
								<input class="form-control" id="fileEnName" type="text"
									name="fileEnName" value="" ng-model="folder.fileEnName"
									placeholder="" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileType">文件类型</label>
							<div class="col-sm-6">
								<select class="form-control" name="fileType" id="fileType"
									ng-model="folder.fileType" value="">
									<option value="1"
										<c:if test="${'1'==folder.fileType}">selected</c:if>>大会资料</option>
									<option value="2"
										<c:if test="${'2'==folder.fileType}">selected</c:if>>媒体资料</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="filePath">文件上传
								<c:if test="${empty folder.filePath}"></c:if>
							</label>
							<c:choose>
								<c:when test="${not empty folder.filePath}">
									<div class="controls">
										<input class="checkPic" id="filePath" name="filePath"
											type="file" value="${folder.filePath}" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="controls">
										<input class="required checkPic" id="filePath" name="filePath"
											type="file" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="explains">文件说明</label>
							<div class="col-sm-6">
								<input class="form-control" id="ds_host" type="text"
									name="explains" value=" " ng-model="folder.explains"
									placeholder="" />
							</div>
						</div>
						<div class="col-sm-offset-2 col-sm-10">
							<div style="margin-left: 100px;">
								<a href="javaScript:void(0)" class="btn btn-default"
									ng-click="editSave()" >保存</a> 
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${ctx }/dispatcher/folder/index" class="btn btn-default"
									>返回</a>
							</div>
						</div>
					</fieldset>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>