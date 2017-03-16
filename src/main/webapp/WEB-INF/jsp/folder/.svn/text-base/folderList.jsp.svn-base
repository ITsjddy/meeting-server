<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>文件资料</title>
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<%-- <script src="${ctx }/static/checkbox/js/deletes.js"></script> --%>
	<%-- <script src="${ctx }/static/checkbox/js/deletes.js"></script> --%>
	<script src="${ctx }/static/sysviews/folder/js/foldeList.js"></script>
</head>
<body ng-app="folderApp" ng-controller="folderController">
<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                   <!--  <h1 class="page-header">文件资料</h1> -->
                </div>
               <!--  /.col-lg-12 -->
            </div> 
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>文件名：<input type="search" ng-model="searchParam.fileName" class="form-control input-sm" placeholder="" aria-controls="dataTablies-example"></label>&nbsp;&nbsp;&nbsp;
									<label>文件英文名：<input type="search" ng-model="searchParam.fileEnName" class="form-control input-sm" placeholder="" aria-controls="dataTablies-example"></label>&nbsp;&nbsp;&nbsp;
									<label>文件类型:
										<select class="form-control input-sm" type="search" placeholder="" aria-controls="dataTables-example" ng-model="searchParam.fileType">
											<option value=""></option>
											<option value="1" <c:if test="${'1'==searchParam.fileType}">selected</c:if> >大会资料</option>
											<option value="2" <c:if test="${'2'==searchParam.fileType}">selected</c:if> >媒体资料</option>    
										</select>
									</label>&nbsp;&nbsp;&nbsp;
									<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()">查询</a>&nbsp;&nbsp;&nbsp;
									<%-- <security:authorize access="hasRole('ROLE_FOLDER_ADD')"> --%>
										<a href="${ctx }/dispatcher/folder/returnSave" class="btn btn-primary" >文件上传</a>
									<%-- </security:authorize>
									<security:authorize access="hasRole('ROLE_FOLDER_ADD')"> --%>
										<a href="javaScript:void(0);" ng-click="deleteAll()" class="btn btn-danger">删除</a>
									<%-- </security:authorize> --%>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example" width="100%">
                                <thead>
                                    <tr>
                                       <th><input type="checkbox" name="checkAll" id="checkAll"></th>
                                       <th>序号</th>
									   <th>文件名</th>
									   <th>文件英文名</th>
									   <th>文件类型</th>
									   <th>文件存储路径</th>
									 <!--   <th width="11%" style="text-align: center;">文件创建时间</th>  -->
									   <th>文件说明</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="file in items">
										<td><input type="checkbox" value="{{file.id}}" ng-bind="file.id" name="ids" id="ids" /></td>
										<td>{{ $index + 1 }}</td>
										<td ng-bind="file.fileName" ></td>
										<td ng-bind="file.fileEnName"></td>
										<td ng-if="file.fileType == 1">大会资料</td>
										<td ng-if="file.fileType == 2">媒体资料</td>
										<td ng-bind="file.filePath"></td>
									<!-- 	<td ng-bind="file.createTimes" style="text-align: center;"></td>  -->
										<td ng-bind="file.explains"7></td>
										<td>
											<div class="btn-group">
												<button data-toggle="dropdown" class="btn dropdown-toggle btn-primary">操作 <span class="caret"></span></button>
												<ul class="dropdown-menu">
													<li><a href="${ctx }${path}/folder/folderEdit?id={{ file.id }}" class="btn btn btn-info" >修改</a></li>
													<li class="divider"></li>
													<li><a href="javaScript:void(0);" ng-click="folderDelete(file.id)" class="btn btn-danger" >删除</a></li>
													<li class="divider"></li>
													<li><a href="${ctx }${path}/folder/returnDetail?id={{ file.id }}" class="btn btn btn-info">详情</a></li>
												</ul>
									    	</div>
										</td>
									</tr>
								</tbody>
                            </table>
                            <!-- 分页的标签 -->
							<nav>
								
								<tm-pagination conf="paginationConf"></tm-pagination>
							</nav>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
           
        </div>
        <!-- /#page-wrapper -->
    </div>
</body>
</html>