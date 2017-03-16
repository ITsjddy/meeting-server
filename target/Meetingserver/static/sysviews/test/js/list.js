//var app = angular.module('testApp', []);

var testApp = angular.module('testApp', ['tm.pagination']);
pageService(testApp);//初始化分页service
testApp.controller("testController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/test/testList");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/test/testList");
	};
	$scope.checkAll = false;
	$scope.checkAllItem = function($event){
		var checkBox = $event.target;
		if(checkBox.checked){
			angular.forEach($scope.items, function(data,index,array){
				//data等价于array[index]
				//console.log(data.a+'='+array[index].a);
				updateSelected("add",data.id);
			});
		}else{
			angular.forEach($scope.items, function(data,index,array){
				//data等价于array[index]
				//console.log(data.a+'='+array[index].a);
				updateSelected("remove",data.id);
			});
		}
	};
	$scope.selected = [];
	var updateSelected = function(action, id) {
		if (action == 'add' && $scope.selected.indexOf(id) == -1) {
			$scope.selected.push(id);
		}
		if (action == 'remove' && $scope.selected.indexOf(id) != -1) {
			var idx = $scope.selected.indexOf(id);
			$scope.selected.splice(idx, 1);
		}
		$('input[name="checkIt"]').each(function(){ 
			   if(!$(this).checked){
				   console.log($(this).checked);
				   $scope.checkAll = false;
			   }else{
				   $scope.checkAll = true;
			   }
	     });  
//		if($scope.selected.length==$scope.paginationConf.itemsPerPage){
//			$scope.checkAll = true;
//		}else{
//			$scope.checkAll = false;
//		}
	}
	$scope.updateSelection = function($event, id) {
		var checkbox = $event.target;
		var action = (checkbox.checked ? 'add' : 'remove');
		updateSelected(action, id);
	}
	$scope.isSelected = function(id) {
		return $scope.selected.indexOf(id) >= 0;
	}
});
