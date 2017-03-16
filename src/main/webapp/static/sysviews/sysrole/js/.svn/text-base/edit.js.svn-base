var sysRoleApp = angular.module('sysRoleApp', []);
sysRoleApp.controller("sysRoleController", function($scope,$http) {
	
	$scope.sysRoleSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
		var param = $scope.sysRole;
		delnoty(delurl, reurl,param,"修改",$http);
	}
	
	$scope.sysRoleQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/queryById";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.sysRole = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
	}
	$scope.sysRoleQueryById();
});
