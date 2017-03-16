var sysGroupApp = angular.module('sysGroupApp', []);
sysGroupApp.controller("sysGroupController", function($scope,$http) {
	$scope.getAuthorizationById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/getGroupUser";
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
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
	}
	
	$scope.getAuthorizationById();
});
