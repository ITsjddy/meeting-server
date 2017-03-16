var commonTaskApp = angular.module('commonTaskApp', []);
commonTaskApp.controller("commonTaskController", function($scope, $http) {
	$scope.commonTaskFindById = function() {
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/commonTask/commonTaskFindById";
		var id = $("#id").val();
		var param = {
			id : id
		};
		$http({
			method : 'POST',
			url : url,
			params : param,
		}).success(function(resultMap) {
			$scope.commonTask = resultMap;
		}).error(function(error) {
		});
	}

	$scope.commonTaskFindById();

	$scope.commonTaskEditSave = function() {
		//获取到表单是否验证通过
        if(!$scope.form.$valid){
            alert('表单没有通过验证');
            return;
        }
        
		var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/commonTask/commonTaskEditSave";
		$http({
			method : 'POST',
			url : url,
			params : $scope.commonTask,
		}).success(function(resultMap) {
			alert("success");
		}).error(function(error) {
			if ('403' == error.status) {
				alert('无权限');
			}
		});
		window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/commonTask/index";
	}
	
	/*返回*/
    $scope.backPage = function() {
        window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/commonTask/index";
    }
})
commonTaskApp.directive('vaildUniqueCode', function() {
	return {
		restrict : "EACM",
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/commonTask/findByUniqueCode";
				$http({
					method : 'POST',
					url : url,
					params : {uniqueCode:this.value},
				}).success(function(resultMap) {
					if (resultMap.count>0) {
						document.getElementById("uniqueCodeError").innerHTML="<span style='color: red;'>唯一标识 不能重复</span>";
						return;
					}
				}).error(function(error) {
				});
			}
			elements[0].onkeyup = function() {
				document.getElementById("uniqueCodeError").innerHTML="";
				if (this.value === '') { 
					return;
				}
				if(this.value.substring(0,1)==" " && this.value.substring(1,2) ===' '){
					return;
				}
				if(this.value.substring(0,1)==" "){
					document.getElementById("uniqueCodeError").innerHTML="<span style='color: red;'>开头不能为空</span>";
					return;
				}
				
				if (this.value && this.value.search(/^[a-z]+$/) != 0) {
					document.getElementById("uniqueCodeError").innerHTML="<span style='color: red;'>唯一标识只能是名称的汉语拼音</span>";
					return;
				}
			}
		}
	};
})
