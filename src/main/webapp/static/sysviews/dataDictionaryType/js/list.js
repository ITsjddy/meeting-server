var dDtypeList = angular.module('dDtypeList', ['tm.pagination']);

pageService(dDtypeList);

checkboxDeleteService(dDtypeList);

dDtypeList.controller("dDtypeListCtlr", function($scope, $http, pageService, checkboxDeleteService) {
  var commUrl =  CONTEXT_PATH + DISPATCHER_PATH ;
  var urlStr = commUrl + "/dataDictionaryType/";
  /**
	 * 初始化页面数据
	 */
  pageService.queryForList($scope, $http, urlStr + "pageQuery");

  /**
	 * 查询
	 */
  $scope.searchByParam = function() {
    pageService.reGetDataForList($scope, $http, urlStr + "pageQuery");
  };

  /**
	 * 删除
	 */
  $scope.deleteDtye = function(dDType) {
    $http({
      method: 'POST',
      url: urlStr + "deleteById",
      params: dDType,
    }).success(function(result) {
      if (result.success) {
        window.location.href = urlStr + "index";
      } else {
        alert('删除失败！');
      }
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  };
  
  /**
	 * 删除
	 */
	$scope.toDataPage = function(dTypeName,dTypeLogo) {
		window.location.href = commUrl + "/dataDictionary/index?typeName="+dTypeName+"&dDTypeLogo="+dTypeLogo;
	};

  /** 多选删除 **/
  $scope.idSelect = function(id, select) {
    return checkboxDeleteService.idSelect($scope, id, select);
  };

  $scope.checkAll = function(c) {
    return checkboxDeleteService.checkAll($scope, c);
  };

  $scope.deletes = function() {
    var delurl = commUrl + "/dataDictionaryType/deleteList";
    var reurl = commUrl + "/dataDictionaryType/index";
    return checkboxDeleteService.deletes($scope, $http, delurl, reurl);
  };

});