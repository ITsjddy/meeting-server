//demo controlers

(function(){
	var applyCtrl = function($scope,$demo2List,$stateParams,$state){
		var _id = $stateParams.appId;
		
		var _obj = {}
		if(_id){
			_obj = angular.extend({},$demo2List.getById(_id))
		}
		$scope.apply = _obj;
		
		$scope.save = function(){
			console.log($scope.apply);
			$demo2List.edit(_id,$scope.apply,function(){
				$state.go("index.demo2");
			})
		}
		$scope.close = function(){
			$state.go("index.demo2");
		}
	}

	var listCtrl = function($scope,$demo2List){
		$scope.applys =[];

		$scope.del = function(id){//删除
			$demo2List.del(id,function(data){
				$scope.applys = angular.copy(data);
			})
		}
		var pg = 1;
		$scope.refresh = function(_pg){//刷新
			if(_pg){
				if(angular.isNumber(_pg)){
					pg = _pg
				}
				else if(_pg == 'next'){//next
					pg++;
				}
				else if(_pg == 'pre'){//previous
					pg--;
				}
				else {
					pg = 1;
				}
			}
			else{
				pg = 1;
				_pg = 1;
			}
			console.log("page: "+pg)
			$scope.pg = pg;
			$demo2List.getList(function(data){
					$scope.applys = angular.copy(data);
				},{
					pg:pg
				})
			
		}
		$scope.refresh(1);
	};

	var list2Ctrl = function($scope,$demo2List){
		$scope.applys =[];

		$scope.refresh = function(_pg){//刷新

			$demo2List.getList(function(data){
				console.log(data)
					$scope.applys = angular.copy(data);
				},{
					pg:_pg
				})
			
		}
		$scope.refresh(1);
	};
	
	angular
    .module('demo2')
    .controller('demo2.IndexCtrl',listCtrl)
    .controller('demo2.applyCtrl',applyCtrl)
    .controller('demo2.AutoTableCtrl',list2Ctrl);
})()