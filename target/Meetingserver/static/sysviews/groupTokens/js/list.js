var groupTokensApp = angular.module('groupTokensApp', ['tm.pagination']);
pageService(groupTokensApp);//初始化分页service
groupTokensApp.controller("groupTokensController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/list");
	};
});
