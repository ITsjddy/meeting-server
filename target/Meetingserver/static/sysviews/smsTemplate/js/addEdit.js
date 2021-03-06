var addEditPage = angular.module('addEditPage', []);

addEditPage.controller("sptAECtlr", function($scope, $http) {

	var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/smsPushTemplate/";
	
	
	
	initSavorPoint();
	/**
	 * 添加
	 */
	$scope.addEditPage = function() {
		$http({
			method : 'POST',
			url : urlStr + "save",
			params : $scope.spTemplate,
		}).success(function(result) {
			if (result.success) {
				window.location.href = urlStr + "openPage";
			} else {
				alert('页面加载失败！');
			}
		}).error(function(error) {
			if ('403' == error.status) {
				alert('无权限');
			}
		});
	};

	/**
	 * 返回
	 */
	$scope.backPage = function() {
		window.location.href = urlStr + "openPage";
	}
	
	/**
	 * 页面数据初始化
	 */
	function initSavorPoint() {
		var spValue = $("#spValue").val();
		$http.get(urlStr + "queryById?id=" + spValue).success(
				function(response) {
					$scope.spTemplate = response;
				});

	}

});
