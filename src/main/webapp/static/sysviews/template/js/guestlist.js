var guestApp = angular.module('guestApp', ['tm.pagination']);
pageService(guestApp);//初始化分页service
guestApp.controller("guestController", function($scope,$http,pageService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/guesttemplate/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/guesttemplate/pageQuery");
	};
	$scope.guestDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/guesttemplate/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/guesttemplate/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
});