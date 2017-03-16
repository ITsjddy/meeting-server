var newsApp = angular.module('newsApp', []);
newsApp.controller("newsController", function($scope,$http) {
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+$scope.languageNow;
	}
	$scope.getNewsData = function(){
		var language ="";
		if(!$scope.languageNow){
			language =myLanguage;
		}else{
			language = $scope.languageNow;
		}
		var id = $("#id").val();
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getNewsData";
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.news = resultMap.data.news;
			$scope.categoryList = resultMap.data.categoryList;
			$scope.columnList = resultMap.data.columnList;
			//UE.getEditor('editor').setContent($scope.news.contentHtml);
			UE.getEditor('editor').ready(function() {
				UE.getEditor('editor').setContent($scope.news.contentHtml);
			});
//			UE.getEditor('editor').addListener("ready", function () {
//				UE.getEditor('editor').setContent($scope.news.contentHtml);
//			});
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getNewsData();
	//获取所有语言数据
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getLanguage";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.languageList = resultMap.data;
			angular.forEach($scope.languageList, function(data,index,array){
				if(data.uneIdent == $scope.languageNow){
					$scope.languageName = data.name;
					$scope.languageNow = data.uneIdent;
				}
			});
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getLanguage();
	$scope.changeCategory =function(categoryId){
		$scope.news.newColumn = '';
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getColumnData";
		if(!categoryId||categoryId==''){
			$scope.columnList = [];
			return;
		}
		var param = {categoryId:categoryId};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.columnList = resultMap.data;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	//改变语言函数
	$scope.changeLanguage = function(language){
		angular.forEach($scope.languageList, function(data,index,array){
			if(data.uneIdent == language){
				$scope.languageName = data.name;
				$scope.languageNow = data.uneIdent;
			}
		});
	}
	//保存函数
	$scope.editSave = function (){
		var contentHtml = UE.getEditor('editor').getContent();
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/editSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+$scope.languageNow;
		$scope.news.language = $scope.languageNow;
		$scope.news.contentHtml = contentHtml;
		var param = {news:$scope.news};
		delnoty(delurl, reurl,param,"修改",$http);
	}
	
	//发布新闻函数
	$scope.publish = function (){
		var contentHtml = UE.getEditor('editor').getContent();
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/publish";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+$scope.languageNow;
		$scope.news.language = $scope.languageNow;
		$scope.news.contentHtml = contentHtml;
		var param = {news:$scope.news};
		delnoty(delurl, reurl,param,"发布",$http);
	}
	/**
	 * 判断点击的是发布还是保存
	 */
	$scope.saveOrPublish  = function(){
		if(!$("#whichButton").val()){
			alert("错误");
			return;
		}else if($("#whichButton").val() == 'btnSubmit'){
			$scope.editSave();
		}else if($("#whichButton").val() == 'btnPublish'){
			$scope.publish();
		}
	}
});
