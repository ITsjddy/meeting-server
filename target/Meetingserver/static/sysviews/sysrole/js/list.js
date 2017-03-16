var sysRoleApp = angular.module('sysRoleApp', ['tm.pagination']);
pageService(sysRoleApp);//初始化分页service
sysRoleApp.controller("sysRoleController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/pageQuery");
	};
	$scope.sysRoleDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
});
