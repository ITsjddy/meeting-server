var dataDictionaryList = angular.module('dataDictionaryList', ['tm.pagination']);

pageService(dataDictionaryList);

checkboxDeleteService(dataDictionaryList);

dataDictionaryList.controller("smsTempCtlr", function($scope, $http, pageService, checkboxDeleteService) {

  var urlStr = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionary/";
  
  var typeName = $('#typeName').val();
  var dDTypeLogo = $('#dDTypeLogo').val();
  /**
	 * 初始化页面数据
	 */
  pageService.queryForList($scope, $http, urlStr + "pageQuery?dDTypeLogo="+dDTypeLogo);

  /**
	 * 查询
	 */
  $scope.searchByParam = function() {
    pageService.reGetDataForList($scope, $http, urlStr + "pageQuery?dDTypeLogo="+dDTypeLogo);
  };
  
  /**
   * 返回
   */
  $scope.pageBack = function() {
	  window.location.href = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionaryType/index";
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
    	  window.location.href = urlStr + "index?typeName="+typeName+"&dDTypeLogo=" + dDTypeLogo;
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
    return checkAll($scope, c);
  };
 function select($scope){
		$scope.idsArr = [];
		angular.forEach($scope.items, function(data,index,array){
			$scope.idsArr[index] = ''+data.dDcommId+'';
			data.check = true;
		});
		$scope.ids = $scope.idsArr.join(',') + ',';
	};
	function selectCancel($scope){
		$scope.idsArr = [];
		$scope.ids = '';
        angular.forEach($scope.items, function(data,index,array){
        	data.check = false;
		});
	};
	
 function checkAll($scope,c){
		if(c == true){ //全选
        	select($scope);
        } else {  //取消全选
        	selectCancel($scope);
        }
	};

  $scope.deletes = function() {
    var delurl = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionary/deleteList";
    var reurl = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionary/index?typeName="+typeName+"&dDTypeLogo=" + dDTypeLogo;
    return checkboxDeleteService.deletes($scope, $http, delurl, reurl);
  };

});