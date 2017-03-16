var appVersionApp = angular.module('appVersionApp', ['tm.pagination']);
pageService(appVersionApp);//初始化分页service
appVersionApp.controller("appVersionController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/appVersion/queryList");
	
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/appVersion/queryList");
	};
});
