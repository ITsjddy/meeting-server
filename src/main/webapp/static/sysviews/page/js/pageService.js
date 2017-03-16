var pageService = function(app){
    app.factory('pageService', [function(){
    	var queryForList = function ($scope,$http,url){
    		$scope.paginationConf = {
    				currentPage: 1,
    				totalItems: 1,
    				itemsPerPage: 15,
    				pagesLength: 15,
    				perPageOptions: [10,15, 20, 30, 40, 50],
    				onChange: function(){
    					if(!$scope.paginationConf.itemsPerPage){
    						$scope.paginationConf.itemsPerPage = 15;
    					}
    					if(!$scope.paginationConf.currentPage){
    						$scope.paginationConf.currentPage = 1;
    					}
    					var pageParam = {
    							pageNow : $scope.paginationConf.currentPage,
    							pageSize :$scope.paginationConf.itemsPerPage
    					};
    					$scope.searchParam = mergeJsonObject($scope.searchParam,pageParam);
    					//var jsonParam = eval('('+(JSON.stringify($scope.searchParam)+JSON.stringify(pageParam)).replace(/}{/,',')+')');
//    					$scope.searchParam.push({
//    						pageNow : $scope.paginationConf.currentPage,
//    						pageSize :$scope.paginationConf.itemsPerPage
//    					});
    					$http({
    						method : 'POST',
    						url : url,
//    						params : {
//    							pageNow : $scope.paginationConf.currentPage,
//    							pageSize :$scope.paginationConf.itemsPerPage
//    						},// params作为url的参数
    						//params :pageParam,
    						params :$scope.searchParam,
    						data : {
    							keyName : 'qubernet'
    						}//作为消息体参数
    					}).success(function(resultMap) {
    						$scope.selectModel = false;
    						$scope.ids = '';
    						$scope.idsArr = [];
    						//$scope.searchParam = null;
//    						pagesLength: 15,
    						//pageNow: 1pageSize: 15pageTotal: 7resultData: Array[15]totalCount: 100
    						if(!resultMap.totalCount){
    							$scope.paginationConf.totalItems =0;
    							$scope.items = [];
    						}else{
    							$scope.paginationConf.totalItems = resultMap.totalCount;
    							$scope.items = resultMap.resultData;
//    							if($scope.changeMyPage){
//    	    						$scope.changeMyPage();
//    	    	            	}
    						}
    					}).error(function(error) {
    					});
    				}
    		};
    	};
    	var reGetDataForList = function($scope,$http,url){
    		if(!$scope.paginationConf.itemsPerPage){
    			$scope.paginationConf.itemsPerPage = 15;
    		}
    		if(!$scope.paginationConf.currentPage){
    			$scope.paginationConf.currentPage = 1;
    		}
    		var pageParam = {
    				pageNow : $scope.paginationConf.currentPage,
    				pageSize :$scope.paginationConf.itemsPerPage
    		};
    		$scope.searchParam = mergeJsonObject($scope.searchParam,pageParam);
    		//var jsonParam = eval('('+(JSON.stringify($scope.searchParam)+JSON.stringify(pageParam)).replace(/}{/,',')+')');
//    		$scope.searchParam.push({
//    			pageNow : $scope.paginationConf.currentPage,
//    			pageSize :$scope.paginationConf.itemsPerPage
//    		});
    		$http({
    			method : 'POST',
    			url : url,
//    			params : {
//    				pageNow : $scope.paginationConf.currentPage,
//    				pageSize :$scope.paginationConf.itemsPerPage
//    			},// params作为url的参数
    			//params :pageParam,
    			params :$scope.searchParam,
    			data : {
    				keyName : 'qubernet'
    			}//作为消息体参数
    		}).success(function(resultMap) {
    			//$scope.searchParam = null;
//    			pagesLength: 15,
    			//pageNow: 1pageSize: 15pageTotal: 7resultData: Array[15]totalCount: 100
    			if(!resultMap.totalCount){
					$scope.paginationConf.totalItems =0;
					$scope.items = [];
				}else{
					$scope.paginationConf.totalItems = resultMap.totalCount;
					$scope.items = resultMap.resultData;
				}
    		}).error(function(error) {
    		});
    		// 配置分页基本参数
//    	    $scope.paginationConf = {
//    	        currentPage: 1,
//    	        itemsPerPage: 15
//    	    };
    		 $scope.paginationConf.currentPage=1;
    		 $scope.paginationConf.itemsPerPage=15;
    	    // 通过$watch currentPage和itemperPage 当他们一变化的时候，重新获取数据条目
    	    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', $scope.reGetDataForList);
    	};
    	//合并两个json对象
    	var mergeJsonObject = function(jsonbject1, jsonbject2) {
    		var resultJsonObject = {};
    		for ( var attr in jsonbject1) {
    			resultJsonObject[attr] = jsonbject1[attr];
    		}
    		for ( var attr in jsonbject2) {
    			resultJsonObject[attr] = jsonbject2[attr];
    		}
    		return resultJsonObject;
    	};
    	return {
    		queryForList: function ($scope,$http,url) {
                return queryForList($scope,$http,url);
            },
            reGetDataForList: function ($scope,$http,url) {
                return reGetDataForList($scope,$http,url);
            },
            mergeJsonObject: function (jsonbject1, jsonbject2) {
                return mergeJsonObject(jsonbject1, jsonbject2);
            }
          }
    }]);
}
