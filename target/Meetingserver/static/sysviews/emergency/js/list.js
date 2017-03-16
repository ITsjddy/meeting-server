var emergencyApp = angular.module('emergencyApp', ['tm.pagination']);
pageService(emergencyApp);//初始化分页service
emergencyApp.controller("emergencyController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/emergency/queryList");
	
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/emergency/queryList");
	};
});
