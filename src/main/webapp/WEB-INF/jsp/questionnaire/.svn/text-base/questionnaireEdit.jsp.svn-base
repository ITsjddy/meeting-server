<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/inc/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>修改问卷</title>
<!-- 模块js -->
<script src="${ctx }/static/sysviews/questionnaire/js/edit.js"></script>
</head>
<body ng-app="questionnaireApp" ng-controller="questionnaireController" style="width: 100%; height: 100%; margin: 10px">
	<ul class="breadcrumb">
		<li>修改问卷</li>
	</ul>
	<div id="wrapper">
		<div id="page-wrapper">
			<form class="form-horizontal" role="form" 
				name="form" ng-submit="questionnaireEditSave()">
				<input type="hidden" value="${questionnaireId }"
					id="questionnaireId">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">标题</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" name="title" required
								ng-model="questionnaire.title" placeholder="" />
						</div>
					</div>
					<!-- 描述-->
					<div class="form-group">
						<label class="col-sm-2 control-label" for="ds_host">说明</label>
						<div class="col-sm-4">
							<input class="form-control" type="text" name="description"
								ng-model="questionnaire.description" placeholder="" />
						</div>
					</div>
					<!-- 题目 -->
					<div class="form-group">
						<label class="col-md-2 control-label">题目</label>
						<div class="col-md-10">
							<div class="form-group" ng-repeat="topic in fchat.topics">
								<div class="col-md-10">
									<div class="col-md-1">
										<label for="topic{{$index + 1}}">{{$index + 1}}.</label>
									</div>
									<!-- 题目外层循环索引保存 -->
									<div ng-bind="topicMark=$index" style="display: none"></div>

									<div class="col-md-2">
										<select class="form-control" id="topicType{{$index + 1}}"
											name="topicType{{$index + 1}}" ng-model="topic.topicType"
											ng-change="fchat.topicOnChange({{$index+1}})">
											<option value="">请选择</option>
											<option ng-repeat="type in topicTypesData"
												value="{{type.id}}"
												ng-selected="{{type.id==topic.topicType}}">{{type.content}}</option>
										</select>
									</div>
									<div class="col-md-5">
										<input type="text" class="form-control" ng-model="topic.topic"
											id="topicTitle{{$index + 1}}" name="topicTitle{{$index + 1}}" />
									</div>
									<div class="col-md-2 img-link">
										<a href="" ng-click="fchat.incrTopic()"> <img
											src="${ctx }/images/plus.png" alt="plus" width="30px"
											height="30px" />
										</a> <a href="" ng-click="fchat.decrTopic($index)"
											ng-show="fchat.canDescTopic"> <img
											src="${ctx }/images/minus.png" alt="minus" width="30px"
											height="30px" />
										</a> <img src="${ctx }/images/dis_minus.png" alt="minus"
											width="30px" height="30px" ng-show="!fchat.canDescTopic" />
									</div>
									<div id="optionsDiv{{topicMark+1}}" style="display: block;">
										<div class="form-group" ng-repeat="option in fchat.options[topicMark]">
											<div class="col-md-12">
												<div class="col-md-1">
													<label for="option{{$index + 1}}">{{$index + 1}}.选项</label>
												</div>
												<div class="col-md-5">
													<input type="text" class="form-control"
														ng-model="option[0].content"
														id="topic{{topicMark+1}}Option{{$index + 1}}"
														name="topic{{topicMark+1}}Option{{$index + 1}}" />
												</div>
												<div class="col-md-2 img-link">
													<a href="" ng-click="fchat.incrOption(topicMark,$index)">
														<img src="${ctx }/images/plus.png" alt="plus" width="30px"
														height="30px" />
													</a> <a href="" ng-click="fchat.decrOption(topicMark,$index)"
														ng-show="fchat.canDescOptions[topicMark][$index]"> <img
														src="${ctx }/images/minus.png" alt="minus" width="30px"
														height="30px" />
													</a> <img src="${ctx }/images/dis_minus.png" alt="minus"
														width="30px" height="30px"
														ng-show="!fchat.canDescOptions[topicMark][$index]" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<input type="hidden" id="topics" name="topics"
								value="{{fchat.combineTopics()}}" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<a href="javaScript:void(0)" class="btn btn-primary" ng-click="backPage()">返回</a>
							<input class="btn btn-primary" type="submit" value="保存" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>