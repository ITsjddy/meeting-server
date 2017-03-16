<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>tableTest</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/test/js/list.js"></script>
</head>
<body  ng-app="testApp" ng-controller="testController">
	<!-- <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
	</div> -->
	<div id="wrapper">
        <div id="page-wrapper">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>姓名:<input type="search"  ng-model="searchParam.name"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
										<a href="${ctx }/dispatcher/test/testAdd" class="btn btn-primary" >新建</a>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th width="5%"><input type="checkbox" ng-checked="checkAll" ng-click="checkAllItem($event)"></th>
                                       <!-- <th width="5%"><input type="checkbox" ng-click="checkAllItem($event)"></th> -->
                                       <th width="10%">序号</th>
									   <th width="20%">姓名</th>
									   <th width="20%">性别</th>
									   <th width="25%">唯一标示</th>
									   <th width="20%">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="x in items">
										<td><input type="checkbox" name="checkIt"  ng-checked="isSelected(x.id)" ng-click="updateSelection($event,x.id)"></td>
										<td>{{ $index + 1 }}</td>
										<td ng-bind="x.name"></td>
										<td ng-if="x.sex == 0">男</td>
										<td ng-if="x.sex == 1">女</td>
										<td ng-bind="x.unCode"></td>
										<td>
											<a href="${ctx }/dispatcher/test/todetail?id={{ x.id }}" class="btn btn-primary" >详情</a>
											<a href="${ctx }/dispatcher/test/testEdit?id={{ x.id }}" class="btn btn btn-info" >编辑</a>
											<a href="${ctx }/dispatcher/test/delete?id={{ x.id }}" class="btn btn-danger" >删除</a>
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
	<!-- <div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		<button type="button" class="btn btn-primary">提交更改</button>
	</div> -->
</body>
</html>