var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope,$http) {
	$scope.guestQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/depttemplate/queryfields";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.guest = resultMap;
			console.log($scope.guest);
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.guestSave = function(){
		$scope.template.fieldName=$('#endepartname').val();
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/depttemplate/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/depttemplate/index";
		var param = $scope.template;
		console.log(param);
		delnoty(delurl, reurl,param,"保存",$http);
	}
	$scope.changeConHallId = function(){
		var tem = $('#conHallId').val();
		$('#endepartname').val(tem);
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/depttemplate/index";
	}
	$scope.guestQueryById();
});
