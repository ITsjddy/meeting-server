var sysRoleApp = angular.module('sysRoleApp', []);
sysRoleApp.controller("sysRoleController", function($scope,$http) {
	$scope.getAuthorizationById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/getRoleUser";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			var zhi = JSON.stringify(resultMap);
			var json = eval('(' + zhi + ')');
			json['plugins'] = [ "wholerow", "checkbox" ];
			$('#jstree2').jstree(json);
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
	
	$scope.getAuthorizationById();
});
