var guestApp = angular.module('servicePersonnelApp', []);
guestApp.controller("servicePersonnelController", function($scope,$http) {
	$scope.servicePersonnelSave = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/save";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :$scope.guest,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
			}else{
				alert('信息修改失败，请重试！');
			}
		}).error(function(error) {
			alert(error.status);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
	}
});
