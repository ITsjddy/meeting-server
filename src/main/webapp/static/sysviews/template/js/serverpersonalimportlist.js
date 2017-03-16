var memberListApp = angular.module('memberListApp', ['tm.pagination']);
pageService(memberListApp);//初始化分页service
memberListApp.controller("memberListController", function($scope,$http,pageService) {
	$scope.selected = [];
	$scope.selectedname = [];
	$scope.queryAllIds = function() {
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/serversersonalsemplate/queryAllIds";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.shuzu = resultMap;
			$scope.selected=$scope.shuzu.strArrayid;
			$scope.selectedname=$scope.shuzu.strArrayname;
			$scope.searchByParam();
		}).error(function(error) {
		});
	}
	$scope.queryAllIds();
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/serversersonalsemplate/pageQueryForCheck");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/serversersonalsemplate/pageQueryForCheck");
	};
	
	$scope.checkAll = false;
	$scope.checkAllItem = function($event){
		var checkBox = $event.target;
		if(checkBox.checked){
			angular.forEach($scope.items, function(data,index,array){
				updateSelected("add",data.id);
			});
		}else{
			angular.forEach($scope.items, function(data,index,array){
				updateSelected("remove",data.id);
			});
		}
	};
	var updateSelected = function(action, id , name) {
		if (action == 'add' && $scope.selected.indexOf(id) == -1) {
			$scope.selected.push(id);
			$scope.selectedname.push(name);
		}
		if (action == 'remove' && $scope.selected.indexOf(id) != -1) {
			var idx = $scope.selected.indexOf(id);
			var idxname = $scope.selectedname.indexOf(name);
			$scope.selected.splice(idx, 1);
			$scope.selectedname.splice(idxname, 1);
		}
		$('input[name="checkIt"]').each(function(){ 
			   if(!$(this).prop("checked")){
				   $scope.checkAll = false;
				   return false;
			   }else{
				   $scope.checkAll = true;
			   }
	     });
	}
	$scope.updateSelection = function($event, id,name) {
		var checkbox = $event.target;
		var action = (checkbox.checked ? 'add' : 'remove');
		updateSelected(action, id , name);
	}
	
	
	
	$scope.isSelected = function(id) {
		return $scope.selected.indexOf(id) >= 0;
	}
	
});