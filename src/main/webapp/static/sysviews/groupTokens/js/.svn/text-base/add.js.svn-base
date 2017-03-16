var groupTokensApp = angular.module('groupTokensApp', []);
groupTokensApp.controller("groupTokensController", function($scope,$http) {
	$scope.addSave = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/addSave";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs 本身结构不支持异步请求
			params :$scope.testDemo,
		}).success(function(resultMap) {
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
		window.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/toList";
	}
	$scope.getEntityById = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/getEntityById";
		var id = $("#testid").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.testDemo = resultMap;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getEntityById();
});
