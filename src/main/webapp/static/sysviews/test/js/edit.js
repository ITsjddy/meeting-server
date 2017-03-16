//var app = angular.module('testApp', []);

var testApp = angular.module('testApp', []);
testApp.controller("testController", function($scope,$http) {
	$scope.testGetTestDemoById = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/test/testGetTestDemoById";
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
	$scope.testGetTestDemoById();
	$scope.testEditSave = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/test/testEditSave";
		var param = $scope.testDemo;
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
		window.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/test/test";
	}
});
