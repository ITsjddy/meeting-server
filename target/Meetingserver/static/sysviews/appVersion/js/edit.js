var appVersionApp = angular.module('appVersionApp', []);
appVersionApp.controller("appVersionController", function($scope, $http) {
	$scope.appVersionFindById = function() {
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/appVersionFindById";
		var id = $("#id").val();
		var param = {
			id : id
		};
		$http({
			method : 'POST',
			url : url,
			params : param,
		}).success(function(resultMap) {
			$scope.appVersion = resultMap;
		}).error(function(error) {
		});
	}

	$scope.appVersionFindById();

	$scope.appVersionEditSave = function() {
		//获取到表单是否验证通过
        if(!$scope.form.$valid){
            alert('表单没有通过验证');
            return;
        }
        
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/appVersionEditSave";
		var fd = new FormData();
		var files = document.querySelectorAll('input[type=file]');
		var postData = {
			appVersionTel : $scope.appVersion.appVersionTel,
			appVersionType : $scope.appVersion.appVersionType,
			appVersionPic : files[0].files[0]
		};
		angular.forEach(postData, function(val, key) {
			fd.append(key, val);
		});
		$http({
			method : 'POST',
			url : url,
			data : fd,
			headers : {
				'Content-Type' : undefined
			},
			transformRequest : angular.identity
		}).success(function(resultMap) {
		}).error(function(error) {
		});
		window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/index";
	}
	
	/*返回*/
    $scope.backPage = function() {
        window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/index";
    }
});
