var newsColumnApp = angular.module('newsColumnApp', ['tm.pagination']);
pageService(newsColumnApp);//初始化分页service
newsColumnApp.controller("newsColumnController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/newsColumn/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/newsColumn/list");
	};
	$scope.deleteItem = function(uniqueCode){
		var param = {uniqueCode:uniqueCode};
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/newsColumn/deleteByUniqueCode";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/newsColumn/toList";
		delnoty(delurl, reurl,param,"删除",$http);
	}
});
