var checkboxDeleteService = function(app){
	
    app.factory('checkboxDeleteService', [function($scope){
    	
    	/*var idsArr = [];
    	var ids = '';*/
    	
    	var itemsi = function($scope,items){
    		var i = 0;
	        angular.forEach(items, function(data,index,array){
	        	if(data.check == true){
	        		i ++;
	        	}
			});
	        return i;
    	};
    	var select = function($scope){
    		$scope.idsArr = [];
	        angular.forEach($scope.items, function(data,index,array){
	        	 $scope.idsArr[index] = ''+data.id+'';
	        	 data.check = true;
			});
	        $scope.ids = $scope.idsArr.join(',') + ',';
    	};
    	var selectCancel = function($scope){
    		$scope.idsArr = [];
    		$scope.ids = '';
	        angular.forEach($scope.items, function(data,index,array){
	        	data.check = false;
			});
    	};
    	
    	var checkAll = function($scope,c){
    		if(c == true){ //全选
	        	select($scope);
	        } else {  //取消全选
	        	selectCancel($scope);
	        }
    	};
    	
    	var idSelect = function($scope,id,select){
    		if($scope.ids == undefined){
    			$scope.ids = '';
    		}
    		var items = $scope.items;
	        if (select == true) {
	            $scope.ids = $scope.ids + id + ',';
	        } else {  
	            $scope.ids = $scope.ids.replace(id + ',', '');
	        }  
	        var idss = $scope.ids.substr(0, $scope.ids.length-1).split(',');
	        var i = itemsi($scope,items);
	        if(i == items.length){
	        	$scope.selectModel = true;
	        }else {
	        	$scope.selectModel = false;
	        }
	        $scope.idsArr = idss;
    	};
    	
    	var deletes = function($scope,$http,delurl,reurl) {
            if($scope.idsArr.length > 0){
    	   		var param = {ids:$scope.idsArr};
    	   		delnoty(delurl, reurl, param, "删除", $http);
            } else {
            	alertnoty('请至少选择一条数据！', 'error', 'center');
            } 
        };
    	
    	return {
	    	idSelect: function ($scope,id,select) {
                return idSelect($scope,id,select);
            },
            checkAll: function ($scope,c) {
                return checkAll($scope,c);
            },
            deletes: function ($scope,$http,delurl,reurl) {
                return deletes($scope,$http,delurl,reurl);
            }
        };
    	
    }]);
}
