var emergencyApp = angular.module('emergencyApp', []);
emergencyApp.controller("emergencyController", function($scope, $http) {
	$scope.emergencyTypesData = [ {
		id : "A",
		content : "A类"
	}, {
		id : "B",
		content : "B类"
	}, {
		id : "C",
		content : "C类"
	} ];

	 
	$scope.emergencyFindById = function() {
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/emergency/emergencyFindById";
		var id = $("#id").val();
		var param = {
			id : id
		};
		$http({
			method : 'POST',
			url : url,
			params : param,
		}).success(function(resultMap) {
			$scope.emergency = resultMap;
		}).error(function(error) {
		});
	}

	$scope.emergencyFindById();

	$scope.emergencyEditSave = function() {
		//获取到表单是否验证通过
        if(!$scope.form.$valid){
            alert('表单没有通过验证');
            return;
        }
        
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/emergency/emergencyEditSave";
		var fd = new FormData();
		var files = document.querySelectorAll('input[type=file]');
		var postData = {
			emergencyTel : $scope.emergency.emergencyTel,
			emergencyType : $scope.emergency.emergencyType,
			emergencyPic : files[0].files[0]
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
		window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/emergency/index";
	}
	
	/*返回*/
    $scope.backPage = function() {
        window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/emergency/index";
    }
});
