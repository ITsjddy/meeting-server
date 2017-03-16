//var app = angular.module('testApp', []);

var testApp = angular.module('testApp', []);
testApp.controller("testController", function($scope,$http) {
	$scope.testAddSave = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/test/testAddSave";
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
		window.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/test/test";
	}
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
});
