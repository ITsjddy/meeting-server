var guestApp = angular.module('servicePersonnelApp', []);
guestApp.controller("servicePersonnelController", function($scope,$http) {
	$scope.guestQueryById = function(){
//		alert(1);
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/queryById";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.service = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.guestSave = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/update";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs ����ṹ��֧���첽����
			params :$scope.service,
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
	$scope.guestQueryById();
});
