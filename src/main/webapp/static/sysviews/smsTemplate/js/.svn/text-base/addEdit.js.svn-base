var addEditPage = angular.module('addEditPage', ['ngMessages']);

addEditPage.controller("sptAECtlr", function($scope, $http) {

  var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/smsPushTemplate/";

  initSavorPoint();
  /**
	 * 添加
	 */
  $scope.addEditPage = function() {
    $http({
      method: 'POST',
      url: urlStr + "save",
      params: $scope.spTemplate,
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
    $http.get(urlStr + "queryById?id=" + spValue).success(function(response) {
      $scope.spTemplate = response;
    });

  }
}).directive('ngTsms', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var pattern = /^[1-9]{1,9}$/;
				if(!pattern.test(this.value)){
					sptNo.setCustomValidity("模版编号只能为数字");
					return false;
				} else {
					return true;
				}
			}
		}
	};
});