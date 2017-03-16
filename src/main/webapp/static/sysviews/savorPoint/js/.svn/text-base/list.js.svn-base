var savorPointList = angular.module('savorPointList', [ 'tm.pagination' ]);

pageService(savorPointList);

checkboxDeleteService(savorPointList);

savorPointList.controller("spCtlr", function($scope, $http, pageService, checkboxDeleteService) {
	
	var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/savorPoint/";
	
	/**
	 * 初始化页面数据
	 */
	pageService.queryForList($scope, $http, urlStr + "queryAll");
	
	/**
	 * 查询
	 */
	$scope.searchByParam = function() {
		pageService.reGetDataForList($scope, $http, urlStr + "queryAll" );
	};
	
	/**
	 * 删除
	 */
	$scope.deleteSavorPoint = function(savorPoint){
		console.info(savorPoint);
		$http({
			method : 'POST',
			url : urlStr + "deleteById",
			params : savorPoint,
		}).success(function(result) {
			if (result.success) {
				window.location.href = urlStr + "openPage";
			} else {
				alert('删除失败！');
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	};
	
	/**
	 * 
	 *  
	 *  多选删除
	 *  
	 **/
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
				$scope.idsArr[index] = ''+data.spUniteId+'';
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
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH + DISPATCHER_PATH + "/savorPoint/deleteAll";
   		var reurl = CONTEXT_PATH + DISPATCHER_PATH + "/savorPoint/openPage";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
	
});
