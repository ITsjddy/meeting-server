var newsApp = angular.module('newsApp', []);
newsApp.controller("newsController", function($scope,$http) {
	//获取所有语言数据
	$scope.getLanguage = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getLanguage";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.languageList = resultMap.data;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getLanguage();
	//获取栏目数据
	$scope.getColumnData = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getColumnData";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.columnList = resultMap.data;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getColumnData();
	//改变column
	$scope.changeColumn =function(columnId){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getColumnData";
		var param = {columnId:columnId};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.categoryList = resultMap.data;
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
	$scope.addSave = function (){
		var contentHtml = UE.getEditor('editor').getContent();
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/addSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/news/toList";
		$scope.news.language = $scope.languageNow;
		$scope.news.contentHtml = contentHtml;
		var param = {news:$scope.news};
		delnoty(delurl, reurl,param,"新增",$http);
	}
});
