<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp" %>
<html lang="en">
<head>
	<meta charset="utf-8"> 
	<title>新闻列表页面</title>
	<!-- 分页样式 -->
	<link rel="stylesheet" href="${ctx }/static/sysviews/page/css/pagecss.css">
	<!-- 分页所需js -->
	<script src="${ctx }/static/sysviews/page/js/tm.pagination.js"></script>
	<script src="${ctx }/static/sysviews/page/js/pageService.js"></script>
	<!-- 模块js -->
	<script src="${ctx }/static/sysviews/news/js/list.js"></script>
</head>
<body  ng-app="newsApp" ng-controller="newsController">
<ul class="breadcrumb"><li>新闻列表</li></ul>
<input type="hidden" ng-init="searchParam.languageNow ='${languageNow }'" ng-model="searchParam.languageNow">
<ul class="nav nav-tabs">
		<!-- <li class="active"><a>基本信息</a></li> -->
		<li  ng-class="{active: searchParam.languageNow == language.uneIdent}" ng-repeat="language in languageList" ><a href="javascript:void(0);" ng-click=changeListType(language.uneIdent) >{{language.name}}列表</a></li>
</ul><br/>
<div id="wrapper">
        <div id="page-wrapper">
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
								<div id="dataTables-example_filter" class="dataTables_filter">
									<label>标题:<input type="search"  ng-model="searchParam.title"
										class="form-control input-sm" placeholder=""
										aria-controls="dataTables-example"></label>
										<a href="javaScript:void(0)" class="btn btn-primary" ng-click="searchByParam()" >查询</a>
										<a href="${ctx }/${path}/news/toAdd?languageNow={{searchParam.languageNow}}" class="btn btn-primary" >新建</a>
								</div>
						</div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                       <th>序号</th>
									   <th>缩略图</th>
									   <th>语种</th>
									   <th>标题</th>
									   <th>简介</th>
									   <th>分类</th>
									   <th>位置</th>
									   <th>创建时间</th>
									   <th>排序</th>
									   <th>状态</th>
									   <th>移动</th>
									   <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									<tr ng-repeat="news in items">
										<td><span class="badge">{{ $index + 1 }}</span></td>
										<td ng-bind="news.zoomPic"></td>
										<td ng-bind="news.languageName"></td>
										<td ng-bind="news.title"></td>
										<td ng-bind="news.remark"></td>
										<td ng-bind="news.categoryName"></td>
										<td ng-if="news.location == 'carousel'">轮播图</td>
										<td ng-if="news.location == 'common'">普通新闻</td>
										<td ng-bind="news.newsCreateTime"></td>
										<td ng-bind="news.sortNumber"></td>
										<td ng-if="news.newStatus == 'publish'">已发布</td>
										<td ng-if="news.newStatus == 'save'">已保存</td>
										<td ng-if="news.newStatus == 'withdraw'">已撤回</td>
										<td>
											<a href="javascript:void(0);" class="btn btn-primary" ng-click="moveIt('up',news.id)">上移</a>
											<a href="javascript:void(0);" class="btn btn-primary" ng-click="moveIt('down',news.id)">下移</a>
										</td>
										<td>
											<a href="${ctx }/${path}/news/toDetail?uniqueCode={{ news.uniqueCode }}&id={{news.id}}" class="btn btn-primary" >详情</a>
											<a href="${ctx }/${path}/news/toEdit?uniqueCode={{ news.uniqueCode }}&id={{news.id}}" class="btn btn btn-info" >编辑</a>
											<a href="javascript:void(0);" ng-click="deleteItem(news.uniqueCode)" class="btn btn-danger" >删除</a>
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