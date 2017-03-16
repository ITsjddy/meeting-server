var smsTemplateList = angular.module('smsTemplateList', ['tm.pagination']);

pageService(smsTemplateList);

checkboxDeleteService(smsTemplateList);

smsTemplateList.controller("smsTempCtlr", function($scope, $http, pageService, checkboxDeleteService) {

  var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/smsPushTemplate/";
  /**
	 * 初始化页面数据
	 */
  pageService.queryForList($scope, $http, urlStr + "queryAll");

  /**
	 * 查询
	 */
  $scope.searchByParam = function() {
    pageService.reGetDataForList($scope, $http, urlStr + "queryAll");
  };

  /**
	 * 删除
	 */
  $scope.deleteSpTemplate = function(spTemplate) {
    $http({
      method: 'POST',
      url: urlStr + "deleteById",
      params: spTemplate,
    }).success(function(result) {
      if (result.success) {
        window.location.href = urlStr + "openPage";
      } else {
        alert('删除失败！');
      }
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  };

  /** 多选删除 **/
  $scope.idSelect = function(id, select) {
    return checkboxDeleteService.idSelect($scope, id, select);
  };

  $scope.checkAll = function(c) {
    return checkboxDeleteService.checkAll($scope, c);
  };

  $scope.deletes = function() {
    var delurl = CONTEXT_PATH + DISPATCHER_PATH + "/smsPushTemplate/deleteList";
    var reurl = CONTEXT_PATH + DISPATCHER_PATH + "/smsPushTemplate/openPage";
    return checkboxDeleteService.deletes($scope, $http, delurl, reurl);
  };

});