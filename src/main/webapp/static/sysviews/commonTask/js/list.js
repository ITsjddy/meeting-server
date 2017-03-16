var commonTaskApp = angular.module('commonTaskApp', ['tm.pagination']);
pageService(commonTaskApp);//初始化分页service
commonTaskApp.controller("commonTaskController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/commonTask/queryList");
	
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/commonTask/queryList");
	};
});
