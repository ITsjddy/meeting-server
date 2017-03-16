var issueApp = angular.module('issueApp', ['tm.pagination']);
pageService(issueApp);//初始化分页service
issueApp.controller("issueController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/list");
	};
	$scope.deleteItem = function(uniqueCode){
		var param = {uniqueCode:uniqueCode};
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/issue/deleteByUniqueCode";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/issue/toList";
		delnoty(delurl, reurl,param,"删除",$http);
	}
});
