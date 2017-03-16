<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>folderDetails</title>
<script src="${ctx }/static/sysviews/folder/js/detailFolder.js"></script>
</head>

<body ng-app="folderApp" ng-controller="folderController">
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form">
				<input type="hidden" value="${id }" id="folderid">
				<fieldset>
					<legend>文件详情</legend>
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileName">文件中文名</label>
							<div class="col-sm-6">
								<input class="form-control" id="fileNAme" type="text"
									name="fileName" disabled="true" value=""
									ng-model="folder.fileName" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="fileEnName">文件英文名</label>
							<div class="col-sm-6">
								<input class="form-control" id="fileEnName" type="text"
									name="fileEnName" disabled="true" value=""
									ng-model="folder.fileEnName" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="fileType" class="col-sm-2 control-label">文件类型</label>
							<div class="col-sm-6">
								<c:choose>
									<c:when test="${'1'==folder.fileType}">
										<input id="fileType1" name="fileType" type="text" value="大会资料"
											readonly="readonly"  disabled="true" class="form-control"/>
									</c:when>
									<c:otherwise>
										<input id="fileType2" name="fileType" type="text" value="媒体资料"
											readonly="readonly" disabled="true" class="form-control"/>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="explains">文件说明</label>
							<div class="col-sm-6">
								<input class="form-control" id="explains" type="text"
									name="explains" disabled="true" value=""
									ng-model="folder.explains" placeholder="" />
							</div>
						</div>
						<div>
							<a href="${ctx }/dispatcher/folder/index" class="btn btn-default" style="margin-left:250px; ">返回</a>
						</div>
					</fieldset>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>