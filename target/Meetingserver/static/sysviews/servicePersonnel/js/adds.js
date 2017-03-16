var guestApp = angular.module('servicePersonnelApp', []);
guestApp.controller("servicePersonnelController", function($scope,$http) {
	$scope.servicePersonnelSave = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/save";
		$http({
			method : 'POST',
			url : url,
//			async:false,  angularjs ����ṹ��֧���첽����
			params :$scope.guest,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
			}else{
				alert('��Ϣ�޸�ʧ�ܣ������ԣ�');
			}
		}).error(function(error) {
			alert(error.status);
			if('403' == error.status){
				alert('��Ȩ��');
			}
		});
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
	}
});
