var sysGroupApp = angular.module('sysGroupApp', []);
sysGroupApp.controller("sysGroupController", function($scope,$http) {
	$scope.sysRoleQueryAll = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/sysRoleQueryAll";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.lsysRole = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.sysGroupSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
		var param = $scope.sysGroup;
		delnoty(delurl, reurl,param,"保存",$http);
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
	}
	$scope.sysRoleQueryAll();
});
