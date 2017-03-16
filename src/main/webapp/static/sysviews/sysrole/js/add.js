var sysRoleApp = angular.module('sysRoleApp', []);
sysRoleApp.controller("sysRoleController", function($scope,$http) {
	
	$scope.sysRoleSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
		var param = $scope.sysRole;
		delnoty(delurl, reurl,param,"保存",$http);
	}
	
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
	}
});
