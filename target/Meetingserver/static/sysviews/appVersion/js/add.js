var appVersionApp = angular.module('appVersionApp', []);
appVersionApp.controller("appVersionController", function($scope, $http) {
	$scope.changeAppVersionType = function(){
		var type = $scope.appVersion.type;
		var plistDiv = document.getElementById("plistDiv");
		var picOneDiv = document.getElementById("picOneDiv");
		var picTwoDiv = document.getElementById("picTwoDiv");
		if (null != type && type === "2") {
			plistDiv.style.display = "block";
			picOneDiv.style.display = "block";
			picTwoDiv.style.display = "block";
		} else {
			plistDiv.style.display = "none";
			picOneDiv.style.display = "none";
			picTwoDiv.style.display = "none";
		}
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/getMaxVersionNum";
		$http({
			method : 'POST',
			url : url,
			params : {type : type},
		}).success(function(resultMap) {
			$scope.appVersion.versionNum = resultMap.versionNum;
		}).error(function(error) {
			if ('403' == error.status) {
				alert('无权限');
			}
		});
	}
	
    $scope.appVersionAddSave = function() {
    	//获取到表单是否验证通过
        if(!$scope.form.$valid){
            alert('表单没有通过验证');
            return;
        }
        
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/appVersionAddSave";
		var fd = new FormData();
		var files = document.querySelectorAll('input[type=file]');
		var postData = {
				  type: $scope.appVersion.type,
				  versionName: $scope.appVersion.versionName,
				  versionNum: $scope.appVersion.versionNum,
				  remark: $scope.appVersion.remark,
				  path : files[0].files[0],
				  qrCode:files[1].files[0],
				  plist :files[2].files[0],
				  picOne: files[3].files[0],
				  picTwo:files[4].files[0]
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
			alert("success");
		}).error(function(error) {
			if ('403' == error.status) {
				alert('无权限');
			}
		});

		window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/index";
	}
    
    /*返回*/
    $scope.backPage = function() {
        window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/index";
    }
});
