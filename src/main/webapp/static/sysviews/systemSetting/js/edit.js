var addSystemSetting = angular.module('addSystemSetting', ['ngMessages']);

addSystemSetting.controller("addSystemSettingCtlr", function($scope, $http) {

  var urlStr = CONTEXT_PATH + DISPATCHER_PATH + "/systemSetting/";
  $scope.statusList = [{id:'enabled',text:'启用'},{id:'disabled',text:'禁用'}];

  initDType();
  /**
	 * 添加
	 */
  $scope.addSystemSetting = function() {
    $http({
      method: 'POST',
      url: urlStr + "save",
      params: $scope.systemSetting,
    }).success(function(result) {
      if (result.success) {
        window.location.href = urlStr + "index";
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
    window.location.href = urlStr + "index";
  }

  /**
	 * 页面数据初始化
	 */
  function initDType() {
    var spValue = $("#spValue").val();
    $http.get(urlStr + "queryById?id=" + spValue).success(function(response) {
      $scope.systemSetting = response;
    });

  }

})
.directive('ngTlogos', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var pattern = /^[a-z0-9]+$/;
				if(!pattern.test(this.value)){
					uniqueId.setCustomValidity("唯一标识只能由数字、小写字母组成！");
					return;
				}
				var url = CONTEXT_PATH + DISPATCHER_PATH + "/systemSetting/checkOnlyLogo";
				$http({
					method : 'POST',
					url : url,
					params : {uniqueId:this.value},
				}).success(function(resultMap) {
					if(resultMap == 'false'){
						uniqueId.setCustomValidity("此唯一标识已存在，请重新输入！");
						return false;
					} else {
						return true;
					}
				}).error(function(error) {
					uniqueId.setCustomValidity("此唯一标识已存在，请重新输入！");
					return false;
				});
			}
		}
	};
});