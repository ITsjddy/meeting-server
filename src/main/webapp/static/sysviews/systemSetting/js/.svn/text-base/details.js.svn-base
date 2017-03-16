var addSystemSetting = angular.module('addSystemSetting', ['ngMessages']);

addSystemSetting.controller("addSystemSettingCtlr", function($scope, $http) {

  var urlStr = CONTEXT_PATH + DISPATCHER_PATH + "/systemSetting/";
  $scope.statusList = [{id:'enabled',text:'启用'},{id:'disabled',text:'禁用'}];

  initDType();
 
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

});