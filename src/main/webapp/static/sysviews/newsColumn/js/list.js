var newsColumnApp = angular.module('newsColumnApp', ['tm.pagination']);
pageService(newsColumnApp);//初始化分页service
checkboxDeleteService(newsColumnApp);//初始化多选删除service
newsColumnApp.controller("newsColumnController", function($scope,$http,pageService,checkboxDeleteService) {
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/newsColumn/multiDelete";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/newsColumn/toList";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
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
