var newsApp = angular.module('newsApp', ['tm.pagination']);
pageService(newsApp);//初始化分页service
newsApp.controller("newsController", function($scope,$http,pageService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/news/list");
	};
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
	
	$scope.changeListType  = function(uneIdent){
		$scope.searchParam.languageNow = uneIdent;
		$scope.searchByParam();
	}
});
