var newsApp = angular.module('newsApp', []);
newsApp.controller("newsController", function($scope,$http) {
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+$scope.languageNow;
	}
	$scope.getNewsData = function(){
		var id = $("#id").val();
		var language ="";
		if(!$scope.languageNow){
			language =myLanguage;
		}else{
			language = $scope.languageNow;
		}
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/getNewsData";
		var param = {id:id,language:language};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.news = resultMap.data.news;
			$scope.categoryList = resultMap.data.categoryList;
			if($scope.news.location == 'carousel'){
				$scope.showDiv = true;
			}else if($scope.news.location == 'headlines'){
				$scope.showDivHeadlines = true;
			}else{
				$scope.showDiv = false;
				$scope.showDivHeadlines = false;
			}
			$("#newsDetail").html($scope.news.contentHtml);
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
	//保存函数
	$scope.editSave = function (){
		var contentHtml = UE.getEditor('editor').getContent();
		var delurl = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/editSave";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/news/toList?language="+$scope.languageNow;
		$scope.news.language = $scope.languageNow;
		$scope.news.contentHtml = contentHtml;
		var param = {news:$scope.news};
		delnoty(delurl, reurl,param,"新增",$http);
	}
});
