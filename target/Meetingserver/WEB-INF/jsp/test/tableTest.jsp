<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>tableTest</title>
	<link rel="stylesheet" href="${ctx }/static/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="${ctx }/static/angular-1.5.9/angular.min.js"></script>
	<script src="${ctx }/static/jquery/jquery-1.11.3.min.js"></script>
	<script src="${ctx }/static/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body  ng-app="myNoteApp" ng-controller="myNoteCtrl">
<div id="wrapper">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">tableTest</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>Name:<input type="search"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
								</div>
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>Url:<input type="search"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>index</th>
									   <th ng-repeat="(x,y) in items[0]">{{ x }}</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="x in items">
										<td>{{ $index + 1 }}</td>
										<td ng-bind="x.Name"></td>
										<td ng-bind="x.Url"></td>
										<td ng-bind="x.Country"></td>
										<td ng-bind="x.type"></td>
									</tr>
								</tbody>
                            </table>
							<nav>
								<ul class="pagination">
									<li><a ng-click="Previous()"> <span>上一页</span>
									</a></li>
									<li ng-repeat="page in pageList"
										ng-class="{active: isActivePage(page)}"><a
										ng-click="selectPage(page)">{{ page }}</a></li>
									<li><a ng-click="Next()"> <span>下一页</span>
									</a></li>
								</ul>
							</nav>
							<!-- /.table-responsive -->
                            <div class="well">
                                <h4>tableTest</h4>
                                <p>测试列表页面</p>
                            </div>
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
    <script src="${ctx }/test/myNoteApp.js"></script>
<script src="${ctx }/test/myNoteCtrl.js"></script>
</body>
</html>